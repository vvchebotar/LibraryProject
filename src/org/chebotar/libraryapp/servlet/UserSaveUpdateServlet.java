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
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.StringJoiner;


@WebServlet(urlPatterns = {"/updateUser", "/addUser"})
public class UserSaveUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Connection conn = MyUtils.getStoredConnection(request);

        String id = request.getParameter("id");
        String errorString = null;
        User user = null;

        if (isNull(id)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/userAddUpdate.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            user = DBUtils.findUser(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        request.getSession().setAttribute("errorString", errorString);
        request.setAttribute("userForShow", user);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/userAddUpdate.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String strDay = request.getParameter("day");
        String strMonth = request.getParameter("month");
        String strYear = request.getParameter("year");
        String gender = request.getParameter("gender").toUpperCase();

        StringJoiner errorString = new StringJoiner(", ");

        Integer intDay = checkAndParse(strDay, errorString);
        Integer intMonth = checkAndParse(strMonth, errorString);
        Integer intYear = checkAndParse(strYear, errorString);
        checkUserNamePassword(userName, password, errorString);
        checkGender(gender, errorString);

        User user = createUser(userName, password, name, lastName, gender, intDay, intYear, intMonth, errorString);
        tryToSaveOrUpdateUser(conn, user, errorString);
        setSessionAttributes(request, errorString);
        response.sendRedirect("users");
    }

    private boolean isNull(String id) {
        return id == null;
    }

    private Integer checkAndParse(String strMonth, StringJoiner errorString) {
        Integer value = 0;
        try {
            value = Integer.parseInt(strMonth);
        } catch (NumberFormatException e) {
            errorString.add("неправильно введена дата!");
        }
        return value;
    }

    private void checkUserNamePassword(String userName, String password, StringJoiner errorString) {
        String regex = "\\w+";
        if (isNull(userName) || !userName.matches(regex)) {
            errorString.add("имя может содержать только буквы или цифры!");
        }
        if (isNull(password) || !password.matches(regex)) {
            errorString.add("пароль может содержать только буквы или цифры!");
        }
    }

    private void checkGender(String gender, StringJoiner stringJoiner) {
        if (!gender.equals(User.GENDER_MALE) && !gender.equals(User.GENDER_FEMALE)) {
            stringJoiner.add(String.format("пол может содержать только буквы %s или %s!", User.GENDER_MALE, User.GENDER_FEMALE));
        }
    }

    private User createUser(String userName, String password, String name, String lastName, String gender, int intDay, int intYear, int intMonth, StringJoiner errorString) {
        User user = null;
        try {
            user = new User(userName, password, name, lastName, intDay, intMonth, intYear, gender);
        } catch (DateTimeException e) {
            errorString.add("неправильно введена дата!");
        }
        return user;
    }

    private void tryToSaveOrUpdateUser(Connection conn, User user, StringJoiner errorString) {
        if (errorString.toString().isEmpty() && !isNull(user)) {
            try {
                DBUtils.saveOrUpdateUser(conn, user);
            } catch (SQLException e) {
                errorString.add(e.getMessage());
            }
        }
    }

    private void setSessionAttributes(HttpServletRequest request, StringJoiner errorString) {
        request.getSession().removeAttribute("messageString");
        request.getSession().removeAttribute("errorString");
        if (errorString.toString().isEmpty()) {
            request.getSession().setAttribute("messageString", "Пользователь успешно обновлен");
        } else {
            request.getSession().setAttribute("errorString", errorString.toString());
        }
    }

    private boolean isNull(User user) {
        return user == null;
    }
}
