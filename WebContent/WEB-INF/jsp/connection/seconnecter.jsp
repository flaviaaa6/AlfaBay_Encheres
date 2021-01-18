<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
   integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">

 
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
   
<title>Se connecter</title>
</head>
<body>

	<h1>ALFA-bay Enchères</h1>
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
		<button class="btn btn-lg btn-primary btn-block" type="submit"
			title="Me connecter">
			<img class="small-icon" src="images/connect.svg" alt="Me connecter">
		</button>
		<a href="#">Mot de passe oublié</a>
	</form>
	</div>

</body>
</html>