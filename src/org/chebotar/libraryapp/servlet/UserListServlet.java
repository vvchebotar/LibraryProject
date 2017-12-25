package org.chebotar.libraryapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.chebotar.libraryapp.beans.UserAccount;
import org.chebotar.libraryapp.utils.DBUtils;
import org.chebotar.libraryapp.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/userList" })
public class UserListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public UserListServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String errorString = (String) request.getAttribute("errorString");
        List<UserAccount> userList = null;
        
        try {
        	userList = DBUtils.queryUserList(conn);
            
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        request.setAttribute("errorString", errorString);
        request.setAttribute("userList", userList);
     
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/userListView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    } 
}
