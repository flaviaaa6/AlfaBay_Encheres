<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!-- Personnal CSS-->
<link rel="stylesheet" href="css/style.css">

<!--icons-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">

<title>AlfaBay_Encheres : Détail vente</title>
</head>
<body>
	<div class="container-fluid">
		<!--emptyHeader-->
		<header>
			<nav
				class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
				<!-- Brand/logo -->
				<a class="navbar-brand" href="${pageContext.request.contextPath}/accueil"> <img
					class="small-icon" src="../images/trocenchere.png"
					alt="Accueil ENI-Encheres" height="50" width="50"> <strong>Alfa-Bay
						Encheres</strong>
				</a> <a class="navbar-brand" href="${pageContext.request.contextPath}/affichage" alt="Gérer mon profil"
					title="Gérer mon profil"> <img class="small-icon"
					src="../images/user.png" height="50" width="50"> <span class="align-middle text-muted">${utilisateurCnx.pseudo },
						crédit : ${utilisateurCnx.credit } </span>
				</a>
			</nav>
		</header>

		<!--main bloc-->
		<main>
			<!--title-->
			<div class="mx-auto text-center">
				<h1>Détails Vente</h1>
				<img class="mb-4 large-icon rounded-circle" src="images/user.png"
					alt="">
			</div>
			<!--erreur-->
			<c:if test="${!empty message }">
				<div class="d-flex alert-danger">
					<div class="col-3 p-2">
						<img class="small-icon" src="images/error.png">
						<p class="text-center">${message}</p>
					</div>
					<c:if test="${!empty erreurs }">
						<ul class="col-9 list-unstyled p-2">
							<c:forEach items="${erreurs }" var="erreur">
								<li>${erreur }</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</c:if>

			<div class="row">
				<div class="col-md-4 mb-3">
					<p>image</p>
				</div>

				<div class="col-md-8 mb-3">
					<!--formulaire-->




					<div class="col-md-9 mb-3">
						<h2>${article.nomArticle }</h2>

					</div>

					<div class="row">
						<div class="col-md-4 mb-3">
							<p>Description :</p>
						</div>
						<div class="col-md-8 mb-3">
							<p>${article.description }</p>
						</div>
					</div>

					<div class="row">
						<div class="col-md-4 mb-3">
							<p>Categorie</p>
						</div>
						<div class="col-md-8 mb-3">
							<p>${article.categorie.libelle }</p>

						</div>
					</div>

					<div class="row">
						<div class="col-md-4 mb-3">
							<p>Meilleure offre :</p>
						</div>
						<div class="col-md-4 mb-3">
							<p>${article.enchere.montantEnchere }</p>
							
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 mb-3">
							<p>Mise a prix :</p>
						</div>
						<div class="col-md-4 mb-3">
							<p>${article.miseAPrix }</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 mb-3">
							<p>Fin de l'enchère :</p>
						</div>
						<div class="col-md-4 mb-3">
							<p>${article.dateFinEnchere }</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 mb-3">
							<p>Retrait :</p>
						</div>
						<div class="col-md-4 mb-3">
							<p>${article.retrait.rue }</p>
							<p>${article.retrait.codePostal }</p>
							<p>${article.retrait.ville }</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 mb-3">
							<p>Vendeur :</p>
						</div>
						<div class="col-md-4 mb-3">
							<p>${article.utilisateur.pseudo }</p>
						</div>
					</div>
					<form action="${pageContext.request.contextPath}/detail/vente" method="post">
						<div class="row">
							<div class="col-md-4 mb-3">
								<label for="proposition">Ma proposition</label>
							</div>
							<div class="col-md-3 mb-3">
								<input type="number" class="form-control" id="proposition"
									name="proposition" min="${article.enchere.montantEnchere +1}" placeholder = "${article.miseAPrix }" value="${article.enchere.montantEnchere +1 }" required>
							</div>
							<div class="col-md-4 mb-3">
									<input type="submit" class="btn btn-primary btn-lg btn-block" value ="Enchérir">
							</div>
							
						</div>
					</form>


				</div>
			</div>
		</main>
		<!--footer-->
		<footer class="border-top text-center align-bottom">
			<div class="mt-3">
				<img class="small-icon" src="images/ateni.svg" alt="Eni Ecole">
				<small class="d-block text-muted">&copy; ENI Ecole 2020</small>
				<div>
					Icons made by <a href="https://www.flaticon.com/authors/freepik"
						title="Freepik">Freepik</a> from <a
						href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>
				</div>
			</div>
		</footer>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

</body>

</html>