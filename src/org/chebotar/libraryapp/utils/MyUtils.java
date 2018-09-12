package org.chebotar.libraryapp.utils;

import org.chebotar.libraryapp.beans.Book;
import org.chebotar.libraryapp.beans.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MyUtils {
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
    public static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";


    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }

    // Get the Connection object has been stored in one attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        return (Connection) request.getAttribute(ATT_NAME_CONNECTION);
    }

    // Store user info in Session.
    public static void storeLoggedUser(HttpSession session, User loginedUser) {

        // On the JSP can access ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
        session.setAttribute("loginedUserIsAdministrator", loginedUser.getAdministrator());
        session.setAttribute("userIslogined", "Y");
    }

    // Get the user information stored in the session.
    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute("loginedUser");
    }

    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, User user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());

        // 1 day (Convert to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);

        // 0 seconds (Expires immediately)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

    public static List<Book> getFavoriteBookList(List<Book> bookList, List<String> favoriteBookIdList) {
        List<Book> resultBookFavoriteList = new ArrayList<>();
        for (String id : favoriteBookIdList) {
            for (Book book : bookList) {
                if (book.getId().equals(id)) {
                    resultBookFavoriteList.add(book);
                }
            }
        }
        return resultBookFavoriteList;
    }
}