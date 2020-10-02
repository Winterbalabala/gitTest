package cn.mvc.session.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class IsLoginFilter extends HttpFilter {
    @Override
    /**
     * 实现未登录进行页面拦截
     */
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String path = req.getServletPath().substring(1);//获取请求地址

        String author = getFilterConfig().getInitParameter("author");
        String noauthor = getFilterConfig().getInitParameter("noauthor");

        String[] strArr = author.split(",");
        String[] nostrArr = noauthor.split(",");

        HttpSession session = req.getSession();
        for (String str:strArr){
            if (str.equals(path)){
                System.out.println(strArr);
                String username = (String)session.getAttribute("user");
                if(username!=null){
                    chain.doFilter(req,resp);
                }else {
                    resp.sendRedirect(req.getContextPath()+"/login.jsp");
                }
            }
        }

        for (String str:nostrArr){
            System.out.println(nostrArr);
            if(path.equals(str)){
                chain.doFilter(req,resp);
            }
        }
    }
}
