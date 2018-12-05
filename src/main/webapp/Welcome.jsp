<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>

<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>

</head>

<body>
<div class="wrapper">
<header class="header">
<br>
<H1 align="center">Добро пожаловать!</H1>
<% 
String nm=null;
nm = (String)session.getAttribute("User_login");
if (!(nm==null)) {out.print("Вы вошли как " + nm); 
	//response.sendRedirect("PostsController?action=refresh");
%>
<br><a href="PostsController?action=refresh">Проследовать на страницу публикаций</a> 
<%
}
%>

</header>
<main class="content">
<h2>Войдите либо зарегистрируйтесь:</h2><br>
      <a href="Login.jsp" class="">Вход</a><br>
      <a href="Sign.jsp" class="">Регистрация</a>
</main>
</div>

<footer class="footer">
</footer>


</body>
</html>