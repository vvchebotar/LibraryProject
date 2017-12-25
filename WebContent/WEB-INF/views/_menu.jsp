<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<div style="padding: 5px;">
	<c:if test="${loginedUser ne null}">	
   		<c:if test="${loginedUserIsAdministrator eq 'Y' }">
   			<a href="${pageContext.request.contextPath}/booksList">БД книг</a>    
     		|
   			<a href="${pageContext.request.contextPath}/userList">БД пользователей</a>
   			|     
   		</c:if>
   
   		<c:if test="${loginedUserIsAdministrator eq 'N' }">
   			<a href="${pageContext.request.contextPath}/booksList">База данных книг</a>
   			| 
   		</c:if>
   		<a href="${pageContext.request.contextPath}/userInfo">Мой профиль</a>
   		|   
   </c:if>
   
   <!--  --> 
   <a href="${pageContext.request.contextPath}/login">Авторизация</a> 
        
       
</div>  