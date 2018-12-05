<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Регистрация</title>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
<div class="main">
<div class="header">
<h2>Регистрация</h2>
</div>
<div class="content">
<c:out value="${alert}" />
<form method="post" action="userController">
	<input type="hidden" name="page" value="sign">
	Логин<input type="text" name="Login" maxlength="50" required><br><p>
	Пароль<input type="Password" name="Password" maxlength="30" required><br><p>
	E-mail<input type="text" name="Email" maxlength="50" disabled><br><p>
	<input type="checkbox" name="Eml" onclick="checkMl(this.form)">
	<p><input type="submit" value="Отправить">
	<input type="reset" value="Очистить"></p>
</form>
<br><a href="Welcome.jsp">Назад</a>
<script>
   //изменение статуса ввода E-mail
   function checkMl(f) {
       if (f.Eml.checked) f.Email.disabled = 0 
       else f.Email.disabled = 1
  }
</script>
 </div>
 </div>
</body>
</html>