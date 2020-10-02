package cn.mvc.session.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CookieUtils {

    private  static final  String KEY = ":Winter@12345";
    /**
     * 浏览器创建cookie文件的方法
     * @param username 放到cookies的信息，用户名
     * @param req
     * @param resp 调用addCookies方法的response对象
     * @param sec 设置Cookies的失效时间，单位是秒
     */
    public  static  void createCookies(String username, HttpServletRequest req, HttpServletResponse resp, int sec){
        Cookie userCookie = new Cookie("userkey",username);
        Cookie ssidCookie = new Cookie("ssid",md5Encrypt(username));

        userCookie.setMaxAge(sec);
        ssidCookie.setMaxAge(sec);
        resp.addCookie(userCookie);
        resp.addCookie(ssidCookie);
    }

    /**
     * 具体的加密算法方法
     * @param ss
     * @return
     */
    public static String md5Encrypt(String ss){
        ss = ss==null?"":ss + KEY;
        char[] md5Digist = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};//字典
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] ssarr = ss.getBytes();

            md.update(ssarr);//把明文放到加密类MessageDigest的对象实例出去，更新数据,通过使用 update 方法处理数据,使指定的 byte数组更新摘要
            byte[] mssarr = md.digest();//进行加密,获得密文完成哈希计算,产生128 位的长整数

            int len = mssarr.length;
            char[] str = new char[len*2];
            int k = 0;

            for (int i=0; i<len; i++){
                byte b = mssarr[i];
                str[k++] = md5Digist[b >>> 4 & 0xf];// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
                str[k++] = md5Digist[b & 0xf];
            }
            System.out.println(new String(str));
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
