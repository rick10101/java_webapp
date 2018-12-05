<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserPage</title>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
<div class="main">
<div class="header">
Профиль пользователя <c:out value="${user.login}" /><p>
</div>
<div class="content">
E-mail: <c:out value="${user.email}" /><p>
Количество статей: <c:out value="${user.posts}" /><p>
<a href="PostsController?action=refresh">Назад</a>
</div>
</div>
</body>
</html>