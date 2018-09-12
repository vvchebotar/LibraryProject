package org.chebotar.libraryapp.servlet;

import org.chebotar.libraryapp.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/userInfo"})
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("user", MyUtils.getLoginedUser(request.getSession()));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/userInfo.jsp");
        dispatcher.forward(request, response);
    }
}
