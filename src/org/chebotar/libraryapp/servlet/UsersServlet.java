package org.chebotar.libraryapp.servlet;

import org.chebotar.libraryapp.beans.User;
import org.chebotar.libraryapp.utils.DBUtils;
import org.chebotar.libraryapp.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/users"})
public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();

        String errorString = (String) session.getAttribute("errorString");
        List<User> userList = null;

        try {
            userList = DBUtils.queryUserList(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = errorString + ", " + e.getMessage();
        }

        session.setAttribute("errorString", errorString);
        request.setAttribute("userList", userList);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/users.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
