<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="padding: 5px;">
    <c:if test="${loginedUser ne null}">
        <c:if test="${loginedUser.administrator eq 'Y' }">
            <a href="books">БД книг</a>
            |
            <a href="users">БД пользователей</a>
            |
        </c:if>
        <c:if test="${loginedUser.administrator eq 'N' }">
            <a href="books">База данных книг</a>
            |
        </c:if>
        <a href="userInfo">Мой профиль</a>
        |
    </c:if>
    <a href="login">Авторизация</a>
</div>  