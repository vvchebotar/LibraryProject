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
import javax.servlet.http.HttpSession;

import org.chebotar.libraryapp.beans.Book;
import org.chebotar.libraryapp.utils.DBUtils;
import org.chebotar.libraryapp.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/booksUserList" })
public class BookUserFavoriteListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public BookUserFavoriteListServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        Connection conn = MyUtils.getStoredConnection(request);
 
        String errorString = null;
        List<Book> bookList = null;
        List<String> favoriteBookIdList = null;
        
        try {
        	bookList = DBUtils.queryBookList(conn);
            
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        try {
        	favoriteBookIdList = DBUtils.queryFavoriteBookIdList(conn, request);
            
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        List<Book> favoriteBookList = MyUtils.getFavoriteBookList(bookList, favoriteBookIdList);
        
        request.setAttribute("errorString", errorString);        
        request.setAttribute("favoriteBookIdList", favoriteBookIdList);
        request.setAttribute("favoriteBookList", favoriteBookList);
        
        HttpSession session = request.getSession();        
        session.setAttribute("returnPath", request.getContextPath() + "/booksUserList");
     
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/bookFavoriteListView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
