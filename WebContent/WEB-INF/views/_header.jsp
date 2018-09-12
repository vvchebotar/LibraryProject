<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="background: #e0e0e0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1>
            <a href="home">Library Phoenix</a>
        </h1>
    </div>
    <div style="float: right; padding: 10px; text-align: right;">
        <c:if test="${loginedUser ne null}">
            <b>Привет ${loginedUser.name}</b><br/><br/>
            <form method=GET action="search">
                Поиск книги:<input type="text" name="search">
            </form>
        </c:if>
    </div>
</div>