<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>База данных пользователей</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>База данных пользователей</h3>
 	
    <p style="color: red;">${errorString}</p>
    <p style="color: green;">${messageString}</p>
 
    <table border="1" >
       <tr>
          <th>ID</th>
          <th>Логин</th>
          <th>Пароль</th>
          <th>Дата регистрации</th>
          <th>Имя</th>
          <th>Фамилия</th>
          <th>Дата рождения</th>
          <th>Пол</th>
       </tr>
       <c:forEach items="${userList}" var="user" >
          <tr>
             <td>${user.id}</td>
             <td><a href="showUser?id=${user.id}" >${user.userName}</a></td>
             <td>${user.password}</td>
             <td>${user.registrationDate}</td>
             <td>${user.name}</td>
             <td>${user.lastName}</td>
             <td>${user.birthday}</td>
             <td>${user.gender}</td>
          </tr>
       </c:forEach>
    </table>
    <br>
    <a href="addUser" >Добавить пользователя</a>     
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>