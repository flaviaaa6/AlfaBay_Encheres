<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link type="text/css" rel="stylesheet" href="css/normal.css" />

<title>Profil utilisateur</title>
</head>
<body>

	<div class="container" style="text-align: center">
<%-- 
		<c:if test="${empty sessionScope.utilisateurCnx.pseudo }">
			<p style="color: red">Connectez-vous d'abord avant d'utiliser
				cette page</p>
			<a href="${pageContext.request.contextPath}/connect"> <input
				type="button" value="Se connecter" />
			</a>
		</c:if>
--%>
		<fieldset>
			<legend>Informations sur l'utilisateur</legend>
			<h2 style="text-align: center">
				<span>Pseudo : </span> ${utilisateurCnx.pseudo }
			</h2>

			<p>
				<span>Nom : </span> ${utilisateurCnx.nom }
			</p>
			<p>
				<span>Prénom : </span> ${utilisateurCnx.prenom }
			</p>
			<p>
				<span>Émail : </span> ${utilisateurCnx.email }
			</p>
			<p>
				<span>Téléphone : </span> ${utilisateurCnx.telephone }
			</p>
			<p>
				<span>Rue : </span> ${utilisateurCnx.rue }
			</p>
			<p>
				<span>Code postal : </span> ${utilisateurCnx.codePostal }
			</p>
			<p>
				<span>Ville : </span> ${utilisateurCnx.ville }
			</p>

			<c:if test="${!empty sessionScope.utilisateurCnx.pseudo }">
				<a href="${pageContext.request.contextPath}/profil"> <input
					type="submit" value="Modifer" />
				</a>
			</c:if>

		</fieldset>

	</div>
</body>
</html>