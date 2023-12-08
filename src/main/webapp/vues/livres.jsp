<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<spring:url value="/files/bootstrap-5.0.2-dist" var="bt"/>
<link href="${bt}/css/bootstrap.min.css" rel="stylesheet">

		<c:set var="cxt" value="${pageContext.request.contextPath}"/>
</head>
<body>
<h1>Gestion des Livres </h1>
<h3>categorie: ${c.titre }</h3>

<table border="1">
<c:forEach items="${liv}" var="c">
	<tr>
		<td>${c.id}</td>
		<td>${c.titre}</td>
		<sec:authorize access="hasRole('ADMIN')">
			<td><a href="${cxt }/delete/${c.id}" class="btn btn-danger btn-lg" role="button" >supprimer</a></td>
</sec:authorize>
			
	
	</tr>
</c:forEach>
</table>
</body>
</html>