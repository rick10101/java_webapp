<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
<div class="main">
<div class="header">
<h2><c:out value="${post.theme}" /></h2><p>
<h4><c:out value="${post.tags}" /></h4>
</div>
<div class="content">
<h4><c:out value="${post.text}" /></h4>
</div>
<div class="footer">
<h3>Добавьте комментарий</h3>
	<form method="post" action="PostsController">
 		<input type="hidden" name="id" value="${post.id}">
		<input type="hidden" name="page" value="comment">
  	 	Автор<input type="text" name="author" maxlength="50" required><p>
   		E-mail<input type="text" name="email" maxlength="50">
   		<p>Комментарий<Br/>
  		<textarea maxlength="200" name="text" cols="40" rows="3" required/></textarea>
  		<p><input type="submit" value="Добавить">
  		<input type="reset" value="Очистить">
  	</form>
</div>
	<div class="footer">
	<p>_______________________________________________________________
<div class="table">
	<div class="theader">
		<div class="cell">Автор</div>
		<div class="cell">Email</div>
		<div class="cell">Комментарий</div>
		<div class="cell">Действие</div>
	</div>
	
	<c:forEach items="${comm}" var="comms">
	<div class="rowGroup">
	<div class="row">    
		<div class="cell"><c:out value="${comms.author}" /></div>
		<div class="cell"><c:out value="${comms.email}" /></div>
        <div class="cell"><c:out value="${comms.text}" /></div>
        
        <div class="cell">
        <c:choose>
  		<c:when test="${post.user==User_login}">
    	<a href="PostsController?action=deletecomm&comid=<c:out value="${comms.id}"/>&id=<c:out value="${post.id}"/>">Удалить</a>
    	</c:when>
  		</c:choose>
  	</div>
  	
  	</div>
  	</div>
</c:forEach>
</div>   
    
<br><a href="PostsController?action=refresh">Назад</a>
</div>
</div>
</body>
</html>