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

<!-- Personnal CSS-->
<link rel="stylesheet" href="../css/style.css">

<!--icons-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">

<title>Se connecter</title>
</head>

<body>
	<%-- 
<div class="container-fluid">
	<form class="form-login" action="${pageContext.request.contextPath}/connect" method="post">
		<label for="inputIdentifiant" class="sr-only">Identifiant</label> <input
			type="text" id="inputIdentifiant" class="form-control" name="pseudo"
			placeholder="Pseudo" required autofocus> <label
			for="inputPassword" class="sr-only">Password</label> <input
			type="password" id="inputPassword" class="form-control"
			name="password" placeholder="Mot de passe" required>
		<div class="checkbox mb-3">
			<label> <input type="checkbox" name="remember"
				value="remember"> Se souvenir de moi
			</label>
		</div>
		
		<button class="btn btn-lg btn-primary btn-block" type="submit" title="Me connecter">
		<img class="small-icon" src="images/connect.png" height="50" width="50" alt="Me connecter">
		</button>
		<a href="#">Mot de passe oublié</a>
	</form>
	</div>
--%>
	<div class="container-fluid">
		<!--emptyHeader-->
		<header>
			<nav
				class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
				<!-- Brand/logo -->
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/accueil"> <img
					class="small-icon" src="images/trocenchere.png" height="50"
					width="50" alt="Accueil AlphaBay_Encheres"> <strong>AlphaBay_Encheres</strong>
				</a>
			</nav>
		</header>

		<!--main bloc-->
		<main>
			<!--title-->
			<div class="mx-auto text-center">
				<h1>Connexion</h1>
				<img class="mb-4 large-icon rounded-circle" src="images/user.png"
					alt="" height="50" width="50">
			</div>
			<!--erreur-->
			<div class="d-flex alert-danger">
				<div class="col-3 p-2">
					<img class="small-icon" src="images/error.png" height="50"
						width="50">
				</div>

				<ul class="col-9 list-unstyled p-2">
					<div>
		             <c:if test="${!empty message }">
					 	<p class="col-9 list-unstyled p-2">${message};
					 	</p>
					</c:if>
		            <c:if test="${!empty erreurs }">
					 	<ul class="col-9 list-unstyled p-2">
					 		<c:forEach items="${erreurs }" var="erreur">
					 			<li>${erreur }</li>
					 		</c:forEach>
					 	</ul>
					</c:if>
               </div> 
				</ul>
			</div>
			<!--formulaire-->
			<div class="col-4" style="text-align:center"> 
				<form class="form-login"
					action="${pageContext.request.contextPath}/connect" method="post">
					<label for="inputIdentifiant" class="sr-only">Identifiant</label> <input
						type="text" id="inputIdentifiant" class="form-control"
						name="pseudo" placeholder="Pseudo" required autofocus> <label
						for="inputPassword" class="sr-only">Mot de passe</label> <input
						type="password" id="inputPassword" class="form-control"
						name="password" placeholder="Mot de passe" required>
					<div class="checkbox mb-3">
						<label> <input type="checkbox" name="remember"
							value="remember"> Se souvenir de moi
						</label>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit"
						title="Me connecter">
						<img class="small-icon" src="images/connect.png" height="50"
							width="50" alt="Me connecter">
					</button>
					<a href="#">Mot de passe oublié</a>

				</form>
			</div>
		</main>
		<!--footer-->
		<footer class="border-top text-center align-bottom">
			<div class="mt-3">
				<img class="small-icon" src="images/ateni.png" height="50"
					width="50" alt="Eni Ecole"> <small class="d-block text-muted">&copy;
					ENI Ecole 2020</small>
			</div>
		</footer>
	</div>



</body>
</html>