package org.chebotar.libraryapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chebotar.libraryapp.beans.UserAccount;
import org.chebotar.libraryapp.utils.DBUtils;
import org.chebotar.libraryapp.utils.MyUtils;

 
@WebServlet(urlPatterns = { "/doAddUser" })
public class DoUserAddServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public DoUserAddServlet() {
       super();
   }
   
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html; charset=UTF-8");
       req.setCharacterEncoding("UTF-8");
       super.service(req, resp);
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       Connection conn = MyUtils.getStoredConnection(request);
       
       String userName = (String) request.getParameter("userName");
       String password = (String) request.getParameter("password");
       String name = (String) request.getParameter("name");
       String lastName = (String) request.getParameter("lastName");
       String strDay = (String) request.getParameter("day");
       String strMonth = (String) request.getParameter("month");
       String strYear = (String) request.getParameter("year");
       String gender = (String) request.getParameter("gender");    
       int intDay = 0;
       int intMonth = 0;
       int intYear =-10000;
              
       String errorString = null;
       
       //errorString = MyUtils.checkUser()
       
       try {
    	   intDay = Integer.parseInt(strDay);
    	   intMonth = Integer.parseInt(strMonth);
    	   intYear = Integer.parseInt(strYear);
       } catch (Exception e) {
    	   //e.printStackTrace();
    	   errorString = "Дата рождения пользователя введена неверно!";    	   
       }       
              
       String regex = "\\w+";
       
       if (userName == null || !userName.matches(regex)) {
           errorString = "Логин пользователя введен неверно!";
       }
       if (password == null || !password.matches(regex)) {
           errorString = "Пароль пользователя введен неверно!";
       }
       
       if (gender.equals("M") || gender.equals("F")) {          
       } else {
    	   errorString = "Пол пользователя введен неверно!";
       }
       
       UserAccount user = null;
       
       try {
    	   user = new UserAccount(userName, password, name, lastName, intDay, intMonth, intYear, gender);
    	   
       } catch (DateTimeException e) {
           errorString = "Дата рождения введена неверно";
       }
       
       //______________________________________________________________//
       
       if (errorString == null) {
           try {
        	   DBUtils.addUser(conn, user);
        	   
           } catch (SQLException e) {
               //e.printStackTrace();
               errorString = e.getMessage();
           }
       }
       
       request.setAttribute("errorString", errorString);
       
       if (errorString == null) {
    	   request.setAttribute("messageString", "пользователь успешно добавлен");   
       }
              
       RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/userList");
       dispatcher.forward(request, response);
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
}
