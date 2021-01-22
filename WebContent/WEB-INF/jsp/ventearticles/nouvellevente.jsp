<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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

<title>AlfaBay_Encheres : Nouvelle Vente</title>
</head>
<body>
	<div class="container-fluid">
		<!--emptyHeader-->
		<header>
			<nav
				class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
				<!-- Brand/logo -->
				<a class="navbar-brand" href="index.html"> <img
					class="small-icon" src="images/trocenchere.svg"
					alt="Accueil ENI-Encheres"> <strong>Alfa-Bay
						Encheres</strong>
				</a> <a class="navbar-brand" href="#" alt="Gérer mon profil"
					title="Gérer mon profil"> <img class="small-icon"
					src="images/user.svg"> <span class="align-middle text-muted">${utilisateurCnx.pseudo },
						crédit : ${utilisateurCnx.credit } </span>
				</a>
			</nav>
		</header>

		<!--main bloc-->
		<main>
			<!--title-->
			<div class="mx-auto text-center">
				<h1>Nouvelle Vente</h1>
				<img class="mb-4 large-icon rounded-circle" src="images/user.svg"
					alt="">
			</div>
			<!--erreur-->
			<c:if test="${!empty message }">
				<div class="d-flex alert-danger">
					<div class="col-3 p-2">
						<img class="small-icon" src="images/error.svg">
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

				<div class="col-md-6 mb-3">
					<!--formulaire-->
					<form class="form-register needs-validation"
						action="${pageContext.request.contextPath}/vente/add"
						method="post" novalidate>

						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="article">Article :</label>
							</div>
							<div class="col-md-9 mb-3">
								<input type="text" class="form-control" id="article"
									name="article" placeholder="" maxlength="30" required value="">
								<div class="invalid-feedback">Ce champ est invalide !</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="description">Description</label>
							</div>
							<div class="col-md-9 mb-3">
								<textarea class="form-control" id="description"
									name="description" placeholder="" value="" maxlength="300"
									required></textarea>
								<div class="invalid-feedback">Ce champ est invalide !</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="categorie">Categorie</label>
							</div>
							<div class="col-md-9 mb-3">
								<select name="categorie" id="categorie">
									<c:if test="${!empty categories }">
									 		<c:forEach items="${categories }" var="categ">
									 			<option value="${ categ.noCategorie}; ${ categ.libelle}">${ categ.libelle}</option>
									 		</c:forEach>									 	
									</c:if>
								</select>
								<div class="invalid-feedback">Ce champ est invalide !</div>
							</div>
						</div>


						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="maPhoto">Photo de l'article :</label>
							</div>
							<div class="col-md-9 mb-3">
								<input type="file" id="maPhoto" name="maPhoto">
							</div>
						</div>


						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="prixDepart">Mise à prix :</label>
							</div>
							<div class="col-md-4 mb-3">
								<input type="number" class="form-control" id="prixDepart"
									name="prixDepart" placeholder="" value="" min="1">
							</div>
						</div>

						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="dateDebutEnchere">Début de l'enchère :</label>
							</div>
							<div class="col-md-9 mb-3">
								<input type="date" class="form-control" id="dateDebutEnchere"
									name="dateDebutEnchere" placeholder="" value="" required>
								<div class="invalid-feedback">Ce champ est invalide !</div>
							</div>
						</div>


						<div class="row">
							<div class="col-md-3 mb-3">

								<label for="dateFinEnchere">Fin de l'enchère :</label>
							</div>
							<div class="col-md-9 mb-3">
								<input type="date" class="form-control" id="dateFinEnchere"
									name="dateFinEnchere" placeholder="" value="" required>
								<div class="invalid-feedback">Ce champ est invalide !</div>

							</div>
						</div>
						<hr class="mb-12">

						<p class = "text-center">Retrait :</p>
						<div class="row">
							<div class="col-md-3 mb-3">
								
								<label for="rue">Rue :</label>
							</div>
							<div class="col-md-9 mb-3">
								<input type="text" class="form-control" id="rue" name="rue"
									placeholder="" maxlength="100" value="" required>
								<div class="invalid-feedback">Ce champ est invalide !</div>


							</div>
						</div>


						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="codePostal">Code Postal : </label>
							</div>
							<div class="col-md-9 mb-3">
								<input type="text" class="form-control" id="codePostal"
									name="codePostal" placeholder="" required>
								<div class="invalid-feedback">Ce champ est invalide ou les
									mots de passe sont différents !</div>


							</div>
						</div>


						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="ville">Ville :</label>
							</div>
							<div class="col-md-9 mb-3">
								<input type="text" class="form-control" id="ville" name="ville"
									placeholder="" required>
								<div class="invalid-feedback">Ce champ est invalide ou les
									mots de passe sont différents !</div>
							</div>
						</div>
						<hr class="mb-12">

						<div class=row>
							<div class="col-md-4 mb-3">
								<button class="btn btn-primary btn-lg btn-block" type="submit">enregistrer</button>
							</div>
							<div class="col-md-4 mb-3">
								<a href="${pageContext.request.contextPath }/accueil"><input
									type="button" class="btn btn-primary btn-lg btn-block"
									value="Annuler" /></a>
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
	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict';

			window
					.addEventListener(
							'load',
							function() {
								// Fetch all the forms we want to apply custom Bootstrap validation styles to
								var forms = document
										.getElementsByClassName('needs-validation');

								// Loop over them and prevent submission
								var validation = Array.prototype.filter
										.call(
												forms,
												function(form) {
													form
															.addEventListener(
																	'submit',
																	function(
																			event) {
																		//validation du mot de passe
																		var password = document
																				.getElementById("password"), confirm_password = document
																				.getElementById("confirm_password");
																		if (password.value != confirm_password.value) {
																			confirm_password
																					.setCustomValidity("Les mots de passe sont différents");
																			event
																					.preventDefault();
																			event
																					.stopPropagation();
																		} else {
																			confirm_password
																					.setCustomValidity('');
																		}
																		//validations des saisies obligatoires
																		if (form
																				.checkValidity() === false) {
																			event
																					.preventDefault();
																			event
																					.stopPropagation();
																		}
																		form.classList
																				.add('was-validated');
																	}, false);
												});
							}, false);
		})();
	</script>
</body>

</html>