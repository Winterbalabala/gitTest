package cn.mvc.session.filter;

import cn.mvc.session.utils.CookieUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AutoLoginFilter extends HttpFilter {
    @Override
    /**
     * 自动登录思路：拦截登陆页面检查cookie是否存在，是否登陆成功
     */
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Cookie[] cookies = req.getCookies();
        if (cookies!=null&&cookies.length>0){
            String username = null;
            String ssid = null;

            for (Cookie cookie:cookies){
                if (cookie.getName().equals("userkey")){
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("ssid")){
                    ssid = cookie.getValue();
                }
            }
            if (username!=null&&ssid!=null&&ssid.equals(CookieUtils.md5Encrypt(username))){//登陆过而且选择了记住我
                System.out.println(username);
                System.out.println(ssid);
                System.out.println(ssid.equals(CookieUtils.md5Encrypt(username)));

                HttpSession session = req.getSession();
                session.setAttribute("user",username);
                resp.sendRedirect(req.getContextPath() + "/index.jsp");//实现了自动登录
            }else {
                chain.doFilter(req,resp);
            }
        }else {
            chain.doFilter(req,resp);
        }
    }
}
