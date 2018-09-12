package org.chebotar.libraryapp.servlet;

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

//import javax.servlet.RequestDispatcher;

@WebServlet(urlPatterns = {"/deleteUser"})
public class UserDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserDeleteServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userNameForDeleting");

        if (userName == null) {
            session.setAttribute("userNameForDeleting", request.getParameter("userName"));
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userDeletingConfirmation.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Connection conn = MyUtils.getStoredConnection(request);
        String errorString = null;

        try {
            DBUtils.deleteUser(conn, userName);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        session.removeAttribute("userNameForDeleting");
        session.setAttribute("errorString", errorString);

        if (errorString == null) {
            session.setAttribute("messageString", "пользователь успешно удален");
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/users");
        dispatcher.forward(request, response);


    }
}
