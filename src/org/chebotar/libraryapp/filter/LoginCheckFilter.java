package org.chebotar.libraryapp.filter;

import org.chebotar.libraryapp.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "loginCheckFilter", urlPatterns = {"/*"})
public class LoginCheckFilter implements Filter {

    public LoginCheckFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");
        String servletPath = req.getServletPath();
        boolean isLogin = false;

        if (servletPath.equals("/login") || servletPath.equals("/doLogin")) {
            isLogin = true;
        }

        if (loginedUser == null && !isLogin) {
            System.out.println("User not logined");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }

    }

}