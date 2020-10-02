package cn.mvc.session.controller;

import cn.mvc.session.model.User;
import cn.mvc.session.service.FactoryService;
import cn.mvc.session.service.UserService;
import cn.mvc.session.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserService userService = FactoryService.getUserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String mn = req.getServletPath();
        mn = mn.substring(1);
        mn = mn.substring(0,mn.length()-4);
        try {
            Method method = this.getClass().getDeclaredMethod(mn,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPasword(req.getParameter("pasword"));

        int rows = userService.save(user);
        if (rows>0){
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }else {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

    /**
     * 实现首页的模糊查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("username");

        username = username==null?"":username.replaceAll("[`~&!#$%^*()+=|{}':;',\\\\[\\\\]<>~！@#￥%……&*（）——+|{}【】‘；：\\\"\\'”“’。，、？]","");

        List<User> list = userService.query(username);

        req.setAttribute("userList",list);//把结果集放进req属性空间
        req.getRequestDispatcher("index.jsp").forward(req,resp);//把req，resp注入到jsp页面
        System.out.println(list);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        int rows =userService.deleteUserById(id);
        if (rows>0){
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }else {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userService.get(id);
        req.setAttribute("user",user);
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }

    private void updatedo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        //通过id获取原来的用户信息
        User user = userService.get(id);
        String yname =user.getUsername();
        String xname =req.getParameter("username");
        long cout = userService.getCountByUsername(xname);
        if (!xname.equals(yname) && cout>0){
            req.setAttribute("note",xname+",被占用");
            req.getRequestDispatcher("/updatedo.udo?id=?"+id).forward(req,resp);
            return;
        }
        user.setUsername(xname);
        user.setPasword(req.getParameter("pasword"));

        int rows = userService.updateUserById(user);
        if (rows>0){
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }else {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //拿到登陆的三参数
        String username = req.getParameter("username");
        String pasword = req.getParameter("pasword");
        String expiredays = req.getParameter("expiredays");

        Cookie[] cookies =req.getCookies();

        boolean login = false;//登陆状态/是否登录
        String account = null;//登陆账号
        String ssid = null;//标记，通过cookie拿到，判断是否能登陆

        if (cookies !=null && cookies.length>0){
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("userkey")){
                    account = cookie.getValue();
                }
                if (cookie.getName().equals("ssid")){
                    ssid = cookie.getValue();
                }
            }
        }
        if (account != null && ssid !=null){
            login = ssid.equals(CookieUtils.md5Encrypt(username));
        }

        System.out.println("login:" + login);

        if (!login){//用户未登录
            User user = userService.login(username,pasword);//通过访问数据库判断用户名密码
            if (user != null){
                expiredays = expiredays == null?"":expiredays;
                switch (expiredays){
                    case "7"://7天
                        CookieUtils.createCookies(username,req,resp,7*24*60*60);;
                        break;
                    case "30"://30天
                        CookieUtils.createCookies(username,req,resp,30*24*60*60);
                        break;
                    case "100"://永久
                        CookieUtils.createCookies(username,req,resp,Integer.MAX_VALUE);
                        break;
                    default:
                        CookieUtils.createCookies(username,req,resp,-1);
                        break;
                }
                //登陆成功，可进入main.jsp

                req.getSession().setAttribute("user",user.getUsername());
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }else {
                req.setAttribute("note","密码或者用户名错误！");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }else {
            req.getSession().setAttribute("user",username);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }
    //使用session实现登出，注销用户的功能。
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //记录登陆状态的cookies删除
        Cookie[] cookies =req.getCookies();
        if (cookies!=null && cookies.length>0){
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("userkey")){
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
                if (cookie.getName().equals("ssid")){
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
        //记录登陆状态的session删除
        HttpSession session = req.getSession();
        if(session!=null){
            session.removeAttribute("user");
        }
        //退出登陆之后跳转到login页面
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }
}
