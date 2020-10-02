package cn.mvc.session.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpFilter implements Filter {
    private FilterConfig filterConfig;

    public FilterConfig getFilterConfig(){
        return this.filterConfig;
    }
    @Override
    public void init(FilterConfig Config) throws ServletException {
        this.filterConfig = Config;
        init();
    }
    protected void init(){}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest)req,(HttpServletResponse)resp,chain);
    }

    protected void doFilter(HttpServletRequest req,HttpServletResponse resp,FilterChain chain) throws IOException, ServletException {
        doFilter(req,resp,chain);
    }

    @Override
    public void destroy() {

    }
}
