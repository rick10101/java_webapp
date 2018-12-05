<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
<div class="wrapper">
<header class="header">
<h1>Вход в систему</h1>
</header>
<main class="content">
<c:out value="${alert}" />
<form method="post" action="userController">
		<input type="hidden" name="page" value="login">
		Логин  <input type="text" name="Login" required><Br><p>
		Пароль <input type="Password" name="Password" required><Br>
   		<p><input type="submit" value="Войти">
   		<input type="reset" value="Очистить"></p>
</form>
<br><a href="Welcome.jsp">Назад</a>
</main>
</div>

<footer class="footer">
</footer>
</body>
</html>