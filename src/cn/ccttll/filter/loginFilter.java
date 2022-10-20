package cn.ccttll.filter;
import cn.ccttll.bean.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class loginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("username");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (user!=null) {
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect(request.getContextPath()+"/WEB-INF/views/biz/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}