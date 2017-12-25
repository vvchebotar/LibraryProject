<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>База данных избранных книг</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>База данных избранных книг</h3>
 	
    <p style="color: red;">${errorString}</p>
 <%-- бордер было ноль, исправил на 1 --%>
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>Название</th>
          <th>Автор</th>
          <th>Дата публикации</th>
          <th>Дата получения</th>
          <th>Краткое описание</th>
          <th>Кол-во стр.</th>
          <th>Избранное</th>
       </tr>
       <c:forEach items="${favoriteBookList}" var="book" >
          <tr>
             <td>${book.id}</td>
             <td>${book.name}</td>
             <td>${book.author}</td>
             <td>${book.publicationDate}</td>
             <td>${book.dateOfReceipt}</td>
             <td>${book.shortDescription}</td>
             <td>${book.numberOfPages}</td>
                 			 
   			 <c:set scope="request" var="isBookFavorite" value="false" />
   			  
             <c:forEach items="${favoriteBookIdList}" var="id" >
             	<c:if test="${id eq book.id }">
   				 	<td>
   				 		<a href="removeBookFromFavorites?id=${book.id}">Удалить</a>
                	 	<c:set scope="request" var="isBookFavorite" value="true" />
             	 	</td>             	 	
   			 	</c:if>   			 	 
             </c:forEach>            
             
             <c:if test="${isBookFavorite eq 'false' }">
   				 	<td>
   				 		<a href="addBookToFavorites?id=${book.id}">Добавить</a>                 	 	
             	 	</td>             	 	
   			 </c:if>
   			 
   			 <c:set scope="request" var="isBookFavorite" value="false" /> 
   			 
          </tr>
       </c:forEach>
    </table>     
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>