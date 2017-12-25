package org.chebotar.libraryapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet(urlPatterns = { "/logoutUser"})
public class DoUserLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DoUserLogoutServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	session.setAttribute("loginedUser", null);
         
        
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/home");         
        dispatcher.forward(request, response);         
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
