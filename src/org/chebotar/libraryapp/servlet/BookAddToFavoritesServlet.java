package org.chebotar.libraryapp.servlet;

import org.chebotar.libraryapp.utils.DBUtils;
import org.chebotar.libraryapp.utils.MyUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/addBookToFavorites"})
public class BookAddToFavoritesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String id = request.getParameter("id");
        String errorString = null;

        try {
            DBUtils.addToFavorites(conn, id, request);

        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }



        HttpSession session = request.getSession();
        session.setAttribute("errorString", errorString);
        response.sendRedirect((String) session.getAttribute("returnPath"));
    }
}