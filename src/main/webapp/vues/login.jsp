<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Se connecter ESSAT 2023</h1>
<c:if test="${not empty errmsg}">
	<p>${errmsg }</p>
</c:if>
<form method="POST" action="login">
<p>Username : <input type="text" name="username"></p>
<p>Mot de passe : <input type="password" name="password"></p>
<p><input type="submit"></p>
</form>
</body>
</html>