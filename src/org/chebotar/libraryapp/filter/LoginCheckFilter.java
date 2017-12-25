package org.chebotar.libraryapp.filter;
 
import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.chebotar.libraryapp.beans.UserAccount;

 
@WebFilter(filterName = "loginCheckFilter", urlPatterns = { "/*" })
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

	   HttpServletRequest req =  (HttpServletRequest) request;
       HttpSession session = req.getSession();
       UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
       String servletPath = req.getServletPath();
       boolean isLogin = false;
       
       if(servletPath.equals("/login") || servletPath.equals("/doLogin")){
    	   isLogin = true;
       }
       
       if (loginedUser == null && !isLogin) {

           System.out.println("User not logined");           
           
           RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
           dispatcher.forward(request, response);
       } else {          
           chain.doFilter(request, response);
       }
 
   }
 
}