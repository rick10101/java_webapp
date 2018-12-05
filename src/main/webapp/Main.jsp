<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Публикации</title>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>


</head>
<body>
<div class="wrapper">
<header class="header">

<h2>Публикации</h2>
<% 
//response.sendRedirect("PostsController?action=refresh");  
%>

<br><a href="PostsController?action=newpost">Новая публикация</a>
<br><a href="PostsController?action=refresh">Обновить</a>
</header>

<div class="content">

<div class="table">
	<div class="theader">
		<div class="cell">Тема</div>
		<div class="cell">Автор</div>
		<div class="cell">Действия</div>
	</div>
		<c:forEach items="${post}" var="posts"> 
	<div class="rowGroup">
		<div class="row">
		<div class="cell"><a href="PostsController?action=postpage&postId=<c:out value="${posts.id}"/>"><c:out value="${posts.theme}"/></a></div>
		<div class="cell"><a href="userController?action=userpage&username=<c:out value="${posts.user}"/>"><c:out value="${posts.user}"/></a></div>
	<div class="cell">
		<c:choose>
		<c:when test="${posts.user==User_login}">
			<a href="PostsController?action=edit&postId=<c:out value="${posts.id}"/>">Редактировать</a><br/>
			<a href="PostsController?action=delete&postId=<c:out value="${posts.id}"/>">Удалить</a>
		</c:when>
		</c:choose>
	</div>
  </div>
</div>
   </c:forEach>
</div>

</main>        
        
<footer class="footer">

<h6>Вы вошли как <c:out value="${User_login}"/></h6>
 <a href="userController?action=exit">Выход</a>
</footer>     
</div>  

</body>
</html>