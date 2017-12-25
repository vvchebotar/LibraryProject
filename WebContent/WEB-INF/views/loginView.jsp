<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Login</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Login Page</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <form method="POST" action="doLogin">
    <%-- бордер было ноль, исправил на 1 (((${user.password})))--%>
       <table border="1"> 
          <tr>
             <td>Логин</td>
             <td><input type="text" name="userName" value= "${user.userName}" /> </td>
          </tr>
          <tr>
             <td>Пароль</td>
             <td><input type="password" name="password" value= "" /> </td>
          </tr>
       <%--   <tr>
             <td>Запомнить меня</td>
             <td ><input type="checkbox" name="rememberMe" value= "Y" /> </td>
          </tr>--%>
          <tr>
             <td colspan ="2">
                <input type="submit" value= "Войти" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
             </td>
          </tr>
       </table>
    </form>
 
    <p style="color:blue;">User Name: sam, password: sam1234 or tom/tom1234 or clark/clark1234 or administrator/1234</p>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>