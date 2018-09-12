<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить/изменить пользователя</title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<h3>Добавить/изменить пользователя</h3>

<p style="color: red;">${sessionScope.get(errorString)}</p>

<form method="POST" action="addUser">
    <table border="1">
        <tr>
            <td>Логин</td>
            <td><input type="text" name="userName" value="${userForShow.userName}"/></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="text" name="password" value="${userForShow.password}"/></td>
        </tr>
        <tr>
            <td>Имя</td>
            <td><input type="text" name="name" value="${userForShow.name}"/></td>
        </tr>
        <tr>
            <td>Фамилия</td>
            <td><input type="text" name="lastName" value="${userForShow.lastName}"/></td>
        </tr>
        <tr>
            <td colspan="2">Дата рождения:</td>
        </tr>
        <tr>
            <td>День</td>
            <td><input type="text" name="day" value="${userForShow.birthday.getDayOfMonth()}"/></td>
        </tr>
        <tr>
            <td>Месяц</td>
            <td><input type="text" name="month" value="${userForShow.birthday.getMonthValue()}"/></td>
        </tr>
        <tr>
            <td>Год</td>
            <td><input type="text" name="year" value="${userForShow.birthday.getYear()}"/></td>
        </tr>
        <tr>
            <td>Пол (F/M)</td>
            <td><input type="text" name="gender" value="${userForShow.gender}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Подтвердить"/>
                <a href="users">Cancel</a>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <a style="color: red;" href="deleteUser?userName=${userForShow.userName}">Удалить пользователя</a>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="_footer.jsp"/>

</body>
</html>