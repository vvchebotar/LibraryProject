package org.chebotar.libraryapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chebotar.libraryapp.beans.UserAccount;
import org.chebotar.libraryapp.utils.DBUtils;
import org.chebotar.libraryapp.utils.MyUtils;

 
@WebServlet(urlPatterns = { "/showUser" })
public class DoUserShowServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public DoUserShowServlet() {
       super();
   }
      
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
	   
       Connection conn = MyUtils.getStoredConnection(request);
       
       String id = (String) request.getParameter("id");
       String errorString = null;
       UserAccount user = null;
       
       try {
    	   user = DBUtils.findUser(conn, id);
    	   
       } catch (SQLException e) {
           e.printStackTrace();
           errorString = e.getMessage();
       }
       
       request.setAttribute("errorString", errorString);
       request.setAttribute("userForShow", user);
             
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/addUserView.jsp");
       dispatcher.forward(request, response);
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
}
