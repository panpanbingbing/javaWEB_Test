package Listener;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ServletListener implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest)servletRequest;
        HttpServletResponse response= (HttpServletResponse)servletResponse;
                HttpSession session = request.getSession(false);
        if ( session !=null &&session.getAttribute("username")!=null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    public void destroy() {

    }
}
