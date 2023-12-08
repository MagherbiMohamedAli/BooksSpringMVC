<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<spring:url value="files/bootstrap-5.0.2-dist" var="bt"/>
<link href="${bt}/css/bootstrap.min.css" rel="stylesheet">

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="container text-center">

 

<div class="shadow-lg p-3 mb-5 bg-body rounded">
<div class="position-relative m-4">

<sec:authorize access="isAuthenticated()">

	<h2 class="text-start">Bienvenue <sec:authentication property="name"/></h2>
	 <a  class="position-absolute top-50 start-100 translate-middle" href="<c:url value="/logout" />">(Logout)</a>

</sec:authorize> 
</div>
</div>

<h1 >Gestion des Livres </h1>

<table class="table table-striped" border="1">
<tr  class="table-info">
<td>Id</td>
<td>Categorie</td>
<td>Liste</td>
</tr>
<c:forEach items="${cats}" var="c">
	<tr align="center">
		<td>${c.id}</td>
		<td>${c.titre}</td>
		<td><a href="livres/${c.id }">Liste des livres</a></td>
	</tr>
</c:forEach>
</table>
 <sec:authorize access="hasRole('ADMIN')">
 
<a href="addLivre" tabindex="-1" role="button" aria-disabled="true">Ajouter un Livre</a>

 </sec:authorize>
 

</body>
</html>