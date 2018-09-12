<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<h3>Hello: ${user.userName}</h3>

Имя: <b>${user.name}</b><br/>
Фамилия: <b>${user.lastName}</b><br/>
Возраст: <b>${user.birthday}</b><br/>
Sex: ${user.getGender() } <br/>
Дата регистрации: <b>${user.registrationDate}</b><br/>
Ваши избранные книги: <a href="booksFavorite">здесь</a><br/>
<br/>
<a href="logout">Выйти из библиотеки</a>

<jsp:include page="_footer.jsp"/>

</body>
</html>