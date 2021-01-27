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
<link type="text/css" rel="stylesheet" href="css/style.css" />

<title>Profil utilisateur</title>
</head>
<body>

	<!--emptyHeader-->
	<header>
		<nav
			class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
			<!-- Brand/logo -->
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/accueil"> <img
				class="small-icon" src="images/trocenchere.png"
				alt="Accueil AlfaBay-Encheres"> <strong>AlfaBay_Encheres</strong>
			</a> <a class="navbar-brand" href="#" alt="Gérer mon profil"
				title="Gérer mon profil"> <img class="small-icon"
				src="images/user.png" alt=""> <span
				class="align-middle text-muted">XXXXX xxx, 0 crédit(s)</span>
			</a>
		</nav>
	</header>
	<br><br><br><br>
	<div class="container">

		<main style="text-align: center">
			
			<h2>
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
				<a href="${pageContext.request.contextPath}/profil">
					<input type="submit" value="Modifer" />
				</a>
			</c:if>

			
		</main>
	</div>
</body>
</html>