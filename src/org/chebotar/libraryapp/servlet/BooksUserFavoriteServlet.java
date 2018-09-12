package org.chebotar.libraryapp.servlet;

import org.chebotar.libraryapp.beans.Book;
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

@WebServlet("/booksFavorite")
public class BooksUserFavoriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(request);

        String errorString = null;
        List<Book> bookList = null;

        try {
            bookList = DBUtils.queryBookList(conn);

        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        List<String> favoriteBookIdList = null;
        try {
            favoriteBookIdList = DBUtils.queryFavoriteBookIdList(conn, request);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        List<Book> favoriteBookList = null;
        if (favoriteBookIdList != null) {
            favoriteBookList = MyUtils.getFavoriteBookList(bookList, favoriteBookIdList);
        }

        request.setAttribute("favoriteBookIdList", favoriteBookIdList);
        request.setAttribute("favoriteBookList", favoriteBookList);

        HttpSession session = request.getSession();
        session.setAttribute("returnPath", "booksFavorite");
        session.setAttribute("errorString", errorString);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/booksFavorite.jsp");
        dispatcher.forward(request, response);
    }
}
