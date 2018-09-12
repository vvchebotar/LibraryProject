package org.chebotar.libraryapp.utils;

import org.chebotar.libraryapp.beans.Book;
import org.chebotar.libraryapp.beans.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public static List<User> queryUserList(Connection conn) throws SQLException {
        String sql = "SELECT id, login, password, administrator, registration_date, name, last_name, age, gender, favorite_books_bdname FROM db_users";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        return getUsersListByRs(rs);
    }

    public static User findUser(Connection conn, String userName, String password) throws SQLException {
        String sql = "SELECT id, login, password, administrator, "
                + "registration_date, name, last_name, age,  gender,  favorite_books_bdname FROM db_users "
                + "WHERE login = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return getUserByRs(rs, userName, password);
    }

    public static User findUser(Connection conn, String id) throws SQLException {
        String sql = "SELECT id, login, password, administrator, registration_date, name, last_name, age,  gender,  favorite_books_bdname " +
                "FROM db_users WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(id));
        ResultSet rs = ps.executeQuery();
        return getUserByRs(rs);
    }

    public static void saveOrUpdateUser(Connection conn, User user) throws SQLException {

        String sql = "SELECT login FROM db_users WHERE login = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUserName());
        ResultSet rs = ps.executeQuery();
        saveOrUpdateUser(conn, user, rs);
    }

    private static void saveOrUpdateUser(Connection conn, User user, ResultSet rs) throws SQLException {
        String sql;
        PreparedStatement ps;
        boolean isUserExist = rs.next();

        if (isUserExist) {
            updateUser(conn, user);
        } else {
            saveUser(conn, user);
            createTableForFavoriteBooks(conn, user);
        }
    }

    public static void deleteUser(Connection conn, String userName) throws SQLException {
        String sql = "DELETE FROM db_users WHERE login=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userName);
        ps.executeUpdate();
    }

    public static List<Book> queryBookList(Connection conn) throws SQLException {
        String sql = "SELECT id, name, author, publication_date, date_of_receipt, short_description, number_of_pages FROM db_books";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return getBooksListByRs(rs);
    }

    public static List<String> queryFavoriteBookIdList(Connection conn, HttpServletRequest request) throws SQLException {
        User user = (User) request.getSession().getAttribute("loginedUser");
        String favoriteBookListName = "db_" + user.getId() + user.getUserName().toLowerCase();
        String sql = "SELECT id FROM " + favoriteBookListName + " ORDER BY id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return getIdsListByRs(rs);
    }

    public static void addToFavorites(Connection conn, String id, HttpServletRequest request) throws SQLException {
        User user = (User) request.getSession().getAttribute("loginedUser");
        String favoriteBookListName = "db_" + user.getId() + user.getUserName().toLowerCase();
        String sql = "INSERT INTO " + favoriteBookListName + " (id) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public static void removeFromFavorites(Connection conn, String id, HttpServletRequest request) throws SQLException {
        User user = (User) request.getSession().getAttribute("loginedUser");
        String favoriteBookListName = "db_" + user.getId() + user.getUserName().toLowerCase();
        String sql = "DELETE FROM " + favoriteBookListName + " WHERE id= ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public static List<Book> searchInBookList(Connection conn, String searchingPhrase) throws SQLException {
        String sql = "Select id, name, author, publication_date, date_of_receipt, short_description, number_of_pages from db_books "
                + "WHERE name ILIKE '%" + searchingPhrase + "%' OR author ILIKE '%" + searchingPhrase +
                "%' OR short_description ILIKE '%" + searchingPhrase + "%' OR publication_date::VARCHAR ILIKE '%" + searchingPhrase + "%'";//
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return getBooksListByRs(rs);
    }

    private static User getUserByRs(ResultSet rs, String userName, String password) throws SQLException {
        if (rs.next()) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            return fillUserCommonFieldsByRs(rs, user);
        }
        return null;
    }

    private static User getUserByRs(ResultSet rs) throws SQLException {
        if (rs.next()) {
            User user = new User();
            user.setUserName(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            return fillUserCommonFieldsByRs(rs, user);
        }
        return null;
    }

    private static User fillUserCommonFieldsByRs(ResultSet rs, User user) throws SQLException {
        user.setId(String.valueOf(rs.getInt("id")));
        user.setAdministrator(rs.getBoolean("administrator") ? "Y" : "N");
        user.setRegistrationDate(rs.getInt("registration_date"));
        user.setName(rs.getString("name"));
        user.setLastName(rs.getString("last_name"));
        user.setBirthday(rs.getLong("age"));
        user.setGender(rs.getString("gender"));
        user.setFavoriteBooksBdName(rs.getString("favorite_books_bdname"));
        return user;
    }

    private static List<User> getUsersListByRs(ResultSet rs) throws SQLException {
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(String.valueOf(rs.getInt("id")));
            user.setUserName(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setAdministrator(rs.getBoolean("administrator") ? "Y" : "N");
            user.setRegistrationDate(rs.getLong("registration_date"));
            user.setName(rs.getString("name"));
            user.setLastName(rs.getString("last_name"));
            user.setBirthday(rs.getLong("age"));
            user.setGender(rs.getString("gender"));
            list.add(user);
        }
        return list;
    }

    private static void saveUser(Connection conn, User user) throws SQLException {
        String sql;
        PreparedStatement ps;
        sql = "INSERT INTO  db_users  (login, password, administrator, registration_date, name, last_name, age, gender) VALUES (?, ?, FALSE , ?, ?, ?, ?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setLong(3, user.getRegistrationDate().toEpochDay());
        ps.setString(4, user.getName());
        ps.setString(5, user.getLastName());
        ps.setLong(6, user.getBirthday().toEpochDay());
        ps.setString(7, user.getGender());
        ps.executeUpdate();
    }

    private static void updateUser(Connection conn, User user) throws SQLException {
        String sql;
        PreparedStatement ps;
        sql = "UPDATE db_users SET password=?, name=?, last_name=?, age=?, gender=? WHERE login=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getPassword());
        ps.setString(2, user.getName());
        ps.setString(3, user.getLastName());
        ps.setLong(4, user.getBirthday().toEpochDay());
        ps.setString(5, user.getGender());
        ps.setString(6, user.getUserName());
        ps.executeUpdate();
    }

    private static void createTableForFavoriteBooks(Connection conn, User user) throws SQLException {
        String sql;
        PreparedStatement ps;
        ResultSet rs;
        sql = "SELECT id, login FROM db_users WHERE login=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUserName());
        rs = ps.executeQuery();

        if (rs.next()) {
            String favoriteBookListName = "db_" + rs.getString("id") + rs.getString("login");
            sql = "CREATE TABLE " + favoriteBookListName + " (id VARCHAR(13))";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }
    }

    private static List<Book> getBooksListByRs(ResultSet rs) throws SQLException {
        List<Book> list = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book();
            book.setId(String.valueOf(rs.getInt("id")));
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setPublicationDate(rs.getInt("publication_date"));
            book.setDateOfReceipt(rs.getLong("date_of_receipt"));
            book.setShortDescription(rs.getString("short_description"));
            book.setNumberOfPages(rs.getInt("number_of_pages"));
            list.add(book);
        }
        return list;
    }

    private static List<String> getIdsListByRs(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("id"));
        }
        return list;
    }
}