package org.chebotar.libraryapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.chebotar.libraryapp.utils.DBUtils;
import org.chebotar.libraryapp.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/addBookToFavorites" })
public class DoBookAddToFavoritesServlet extends HttpServlet {
	
   private static final long serialVersionUID = 1L;
 
   public DoBookAddToFavoritesServlet() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
	   Connection conn = MyUtils.getStoredConnection(request);
 
	   String id = (String) request.getParameter("id");
	   String errorString = null;
       
	   try {
		   DBUtils.addToFavorites(conn, id, request);
    	   
	   } catch (SQLException e) {
		   e.printStackTrace();
		   errorString = e.getMessage();
	   }
       
	   request.setAttribute("errorString", errorString);
       
	   HttpSession session = request.getSession();
	   response.sendRedirect((String)session.getAttribute("returnPath"));
   		}
 
   		@Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
}