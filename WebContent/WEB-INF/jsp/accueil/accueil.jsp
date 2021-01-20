<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<!-- Personnal CSS-->
<link rel="stylesheet" href="css/style.css">

<!--icons-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>

	<h1>AlfaBay ENCHERES</h1>
	<h2>Liste des enchères</h2>

	<c:if test="">
	
	</c:if>
	<a href="${pageContext.request.contextPath }/connect">${empty sessionScope.utilisateurCnx.pseudo ? "Se connecter" : "Se déconnecter"} </a>
	
	<c:if test="${!empty sessionScope.utilisateurCnx.pseudo }">
		<p>Bienvenue ${utilisateurCnx.pseudo}</p>
	</c:if>
	
	<a href="${pageContext.request.contextPath }/inscription">S'inscrire</a>
	
</body>
</html>