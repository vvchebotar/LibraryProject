<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<h3>Login Page</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="login">
    <table border="1">
        <tr>
            <td>Логин</td>
            <td><input type="text" name="userName" value="${user.userName}"/></td>
        </tr>
        <tr>
            <td>Пароль</td>
            <td><input type="password" name="password" value=""/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Войти"/>
                <a href="home">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<p style="color:blue;">User Name: Victor, password: 1234 or Maksim/1234</p>

<jsp:include page="_footer.jsp"/>

</body>
</html>