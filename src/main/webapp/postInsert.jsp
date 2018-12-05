<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Редактирование публикации</title>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
<div class="main">
<div class="header">
<h2>Отредактируйте публикацию или напишите новую</h2>
</div>
<div class="content">

 <form method="POST" action='PostsController'>
        <input type="hidden" name="id" value="${post.id}">
        <input type="hidden" name="page" value="addpost">
        <input type="hidden" name="user" value="${User_login}">
        
        Автор : <c:out value="${User_login}" /> <br/> 
        Тема :  <input size="60" type="text" name="theme" value="<c:out value="${post.theme}"/>" required maxlength="100"/> <p/> 
         Текст :<textarea name="text" cols="120" rows="30" maxlength="15000" required> <c:out value="${post.text}"/></textarea><br/><p/> 
         Tags : <input size="60" type="text" name="tags" value="<c:out value="${post.tags}"/>" maxlength="100"/> <br/> <p/> 
         <input type="submit" value="Опубликовать" /> 
</form>
         <br><a href="PostsController?action=refresh">Назад</a>         
</div>
</div>
</body>
</html>