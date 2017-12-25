package org.chebotar.libraryapp.utils;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.chebotar.libraryapp.beans.Book;
import org.chebotar.libraryapp.beans.UserAccount;
 
public class DBUtils {
	
	public static List<UserAccount> queryUserList(Connection conn) throws SQLException {
	      String sql = "Select a.id, a.Login, a.Password, a.Administrator, a.Registration_date, a.Name, a.Last_name, a.Age, a.Gender, a.Favorite_books_bdname from user_table a ";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	 
	      ResultSet rs = pstm.executeQuery();
	      List<UserAccount> list = new ArrayList<UserAccount>();
	      while (rs.next()) {

	    	  UserAccount user = new UserAccount();
	    	  
	    	  user.setId(rs.getString("id"));
	    	  user.setUserName(rs.getString("Login"));
	    	  user.setPassword(rs.getString("Password"));
	    	  user.setAdministrator(rs.getString("Administrator"));    	  
	    	  user.setRegistrationDate(rs.getLong("Registration_date"));
	    	  user.setName(rs.getString("Name"));
	    	  user.setLastName(rs.getString("Last_name"));
	    	  user.setBirthday(rs.getLong("Age"));
	    	  user.setGender(rs.getString("Gender"));
	    	            
	          list.add(user);
	      }
	      return list;
	}
	
	public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {
 
      String sql = "Select a.id, a.Login, a.Password, a.Administrator, "
      		+ "a.Registration_date, a.Name, a.Last_name, a.Age,  a.Gender,  a.Favorite_books_bdname from user_table a "
      		+ "where a.Login = ? and a.Password= ?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, userName);
      pstm.setString(2, password);
      ResultSet rs = pstm.executeQuery();
 
      if (rs.next()) {
    	  String id = rs.getString("id");
    	  String administrator = rs.getString("Administrator");    	  
    	  int registrationDate = rs.getInt("Registration_date");
    	  String name = rs.getString("Name");
    	  String lastName = rs.getString("Last_name");
    	  long birthday = rs.getLong("Age");
          String gender = rs.getString("Gender");
          String favoriteBooksBdName = rs.getString("Favorite_books_bdname");          
          
          UserAccount user = new UserAccount();
          user.setId(id);
          user.setUserName(userName);
          user.setPassword(password);          
          user.setAdministrator(administrator);
          user.setRegistrationDate(registrationDate);
          user.setName(name);
          user.setLastName(lastName);
          user.setBirthday(birthday);
          user.setGender(gender);
          user.setFavoriteBooksBdName(favoriteBooksBdName);
          
          return user;
      }
      return null;
  }
	
	public static UserAccount findUser(Connection conn, String id) throws SQLException {
	  
      String sql = "Select a.Login, a.Password, a.Administrator, "
        		+ "a.Registration_date, a.Name, a.Last_name, a.Age,  a.Gender,  a.Favorite_books_bdname from user_table a "
          		+ "where a.id = ?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, id);
      ResultSet rs = pstm.executeQuery();
 
      if (rs.next()) {    	  
    	  String userName = rs.getString("Login");
    	  String password = rs.getString("Password");
    	  String administrator = rs.getString("Administrator");    	  
    	  int registrationDate = rs.getInt("Registration_date");
    	  String name = rs.getString("Name");
    	  String lastName = rs.getString("Last_name");
    	  long birthday = rs.getLong("Age");
          String gender = rs.getString("Gender");
          String favoriteBooksBdName = rs.getString("Favorite_books_bdname");
          
          
          UserAccount user = new UserAccount();
          user.setId(id);
          user.setUserName(userName);
          user.setPassword(password);          
          user.setAdministrator(administrator);
          user.setRegistrationDate(registrationDate);
          user.setName(name);
          user.setLastName(lastName);
          user.setBirthday(birthday);
          user.setGender(gender);
          user.setFavoriteBooksBdName(favoriteBooksBdName);
          
          return user;
      }
      return null;
  }
  
	public static void addUser(Connection conn, UserAccount user) throws SQLException {
		  
		  String sql = "Select a.Login from user_table a where a.Login=?";		  
		  PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setString(1, user.getUserName());	      
	      ResultSet rs = pstm.executeQuery();
	      
	      boolean isUserExist = false;
	      
	      if (rs.next()) {
	    	  isUserExist = true;	  
	      }
	      
	      if(isUserExist){
	    	  sql = "Update user_table a set a.Password=?, a.Name=?, a.Last_name=?, a.Age=?, a.Gender=? where a.Login=?";
	    	  pstm = conn.prepareStatement(sql);
			  pstm.setString(1, user.getPassword());
			  pstm.setString(2, user.getName());
			  pstm.setString(3, user.getLastName());
			  pstm.setString(4, String.valueOf(user.getBirthday().toEpochDay()));
			  pstm.setString(5, user.getGender());
		      pstm.setString(6, user.getUserName());
		      pstm.executeUpdate();
		      
	      } else {
	    	  pstm.close();
	    	  sql = "Insert into  user_table  (Login, Password, Administrator, Registration_date, Name, Last_name, Age, Gender) values (?, ?, 'N', ?, ?, ?, ?, ?)";
			  pstm = conn.prepareStatement(sql);	      
		      pstm.setString(1, user.getUserName());
		      pstm.setString(2, user.getPassword());
		      pstm.setString(3, String.valueOf(user.getRegistrationDate().toEpochDay()));
		      pstm.setString(4, user.getName());
		      pstm.setString(5, user.getLastName());
		      pstm.setString(6, String.valueOf(user.getBirthday().toEpochDay()));
		      pstm.setString(7, user.getGender());      
		      pstm.executeUpdate();
		      
		      sql = "Select a.id, a.Login from user_table a where a.Login=?";   
		      pstm = conn.prepareStatement(sql);
		      pstm.setString(1, user.getUserName());
		      rs = pstm.executeQuery();
		      
		      String favoriteBookListName = null;
		      
		      if (rs.next()) {
		    	  favoriteBookListName = rs.getString("id") + rs.getString("Login");	  
		      }
			  
			  if (!favoriteBookListName.isEmpty()) {
				  sql = "CREATE TABLE " + favoriteBookListName + "(id VARCHAR(13))";      
				  pstm = conn.prepareStatement(sql); 
				  pstm.executeUpdate();
			  }  
		  }
	}
	  
	public static void deleteUser(Connection conn, String userName) throws SQLException {
		
		  String sql = "Delete From `bdlibraryphoenix`.`user_table` WHERE `Login`=?";	 
		  PreparedStatement pstm = conn.prepareStatement(sql);
		  pstm.setString(1, userName);
		  pstm.executeUpdate();	  
	}
	
	public static List<Book> queryBookList(Connection conn) throws SQLException {
      String sql = "Select a.ID, a.Name, a.Author, a.Publication_date, a.Date_of_receipt, a.Short_description, a.Number_of_pages from books_table a ";
 
      PreparedStatement pstm = conn.prepareStatement(sql); 
      ResultSet rs = pstm.executeQuery();
      
      List<Book> list = new ArrayList<Book>();
      
      while (rs.next()) {

          Book book = new Book();
          book.setId(rs.getString("ID"));
          book.setName(rs.getString("Name"));
          book.setAuthor(rs.getString("Author"));
          book.setPublicationDate(rs.getInt("Publication_date"));
          book.setDateOfReceipt(rs.getLong("Date_of_receipt"));
          book.setShortDescription(rs.getString("Short_description"));
          book.setNumberOfPages(rs.getInt("Number_of_pages"));
          
          list.add(book);
      }
      return list;
  }
	
	public static List<String> queryFavoriteBookIdList(Connection conn, HttpServletRequest request) throws SQLException {
	  
		List<String> list = new ArrayList<String>();
	  
		HttpSession session = request.getSession();
		UserAccount user = (UserAccount) session.getAttribute("loginedUser");		
		String favoriteBookListName = user.getId() + user.getUserName();
		
		String sql = "Select a.id from " + favoriteBookListName + " a  ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		while (rs.next()) {
			list.add(rs.getString("id"));
		}
	  
		return list;
	}
	
	public static void addToFavorites(Connection conn, String id, HttpServletRequest request) throws SQLException {
		
	  // имя избранной бд книг = id юзера + логин юзера
	  HttpSession session = request.getSession();
	  UserAccount user = (UserAccount) session.getAttribute("loginedUser");
	  String favoriteBookListName = user.getId() + user.getUserName();
	        
      String sql = "Insert into " + favoriteBookListName + " (id) values (?)";
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, id);
      pstm.executeUpdate();      
	}
  
	public static void removeFromFavorites(Connection conn, String id, HttpServletRequest request) throws SQLException {
		
	  // имя избранной бд книг = id юзера + логин юзера
	  HttpSession session = request.getSession();
	  UserAccount user = (UserAccount) session.getAttribute("loginedUser");
	  String favoriteBookListName = user.getId() + user.getUserName();
	  
	  String sql = "Delete From " + favoriteBookListName + " where id= ?";      
      PreparedStatement pstm = conn.prepareStatement(sql); 
      pstm.setString(1, id);
      pstm.executeUpdate();
  }
	
	public static List<Book> searchInBookList(Connection conn, String searchingPhrase) throws SQLException {
      String sql = "Select a.ID, a.Name, a.Author, a.Publication_date, a.Date_of_receipt, a.Short_description, a.Number_of_pages from books_table a "
      		+ "WHERE a.Name LIKE \"%" + searchingPhrase + "%\" OR a.Author LIKE \"%" + searchingPhrase + "%\" OR a.Publication_date LIKE \"%" + searchingPhrase + "%\" OR a.Short_description LIKE \"%" + searchingPhrase + "%\" ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);      
      ResultSet rs = pstm.executeQuery();
      List<Book> list = new ArrayList<Book>();
      while (rs.next()) {

          Book book = new Book();
          book.setId(rs.getString("ID"));
          book.setName(rs.getString("Name"));
          book.setAuthor(rs.getString("Author"));
          book.setPublicationDate(rs.getInt("Publication_date"));
          book.setDateOfReceipt(rs.getLong("Date_of_receipt"));
          book.setShortDescription(rs.getString("Short_description"));
          book.setNumberOfPages(rs.getInt("Number_of_pages"));
          
          list.add(book);
      }
      return list;
  }
}