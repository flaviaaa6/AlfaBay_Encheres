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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<!-- Personnal CSS-->
<link rel="stylesheet" href="css/style.css">

<!--icons-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<meta charset="UTF-8">
<title></title>
</head>
<body>

<%-- -----------------VERSION PLEIN ECRAN ORDINATEUR----------------- --%>

<div class="container-fluid">


<header>
<!-- VERSION GRAND ECRAN-->
    <nav class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
		   <!-- LOGO -->
		   <a class="navbar-brand" href="${pageContext.request.contextPath}/accueil">
		       <img class="small-icon" src="images/trocenchere.png" alt="Accueil AlphaBay_Encheres">
		       <strong>AlphaBay_Encheres</strong>
		   </a>
		
		
<!-- ENTETE POUR PETIT ECRAN -->		
	<ul class="navbar-nav ml-auto">
	    <li class="nav-item dropdown d-lg-none">
	    
	    	<!-- LOGO -->	
	        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	            <img class="small-icon" src="images/menu.png" alt="Menu AlphaBay_Encheres">
	        </a>
	        
	        <div class="dropdown-menu">
	        
	            <c:if test="${!empty sessionScope.utilisateurCnx.pseudo }">
			       	<img class="small-icon" src="images/user.png">
			       	<span class="align-middle text-muted">BIENVENU(E) ${utilisateurCnx.pseudo}, 0 crédit(s)</span>
			       	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			       	<a class="dropdown-item" href="#">Administrer</a> 
	            	<a class="dropdown-item" href="${pageContext.request.contextPath}/vente/add">Vendre un article</a>
			       	<a class="dropdown-item" href="${pageContext.request.contextPath}/affichage" title="Gérer mon profil">Gérer mon profil</a>
			       	<a class="dropdown-item" href="${pageContext.request.contextPath}/deconnect">Se déconnecter</a>
				</c:if>
				
	            <c:if test="${empty sessionScope.utilisateurCnx.pseudo }">
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/inscription">M'inscrire</a>
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/connect">Me connecter</a>
		            
	            </c:if>
	            
	        </div>
	    </li>  
	    
<!-- ENTETE POUR ECRAN MOYEN-->	

		<c:if test="${!empty sessionScope.utilisateurCnx.pseudo }">
       		<img class="small-icon" src="images/user.png">
       		<span class="align-middle text-muted">BIENVENU(E) ${utilisateurCnx.pseudo}, 0 crédit(s)</span>
       		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
       		<li class="nav-item d-none d-lg-block"><a class="nav-link" href="#">Administrer</a></li> 
       		<li class="nav-item d-none d-lg-block"><a class="nav-link" href="${pageContext.request.contextPath}/vente/add">Vendre un article</a></li>
	  		<a class="nav-link" href="${pageContext.request.contextPath}/affichage">Gérer mon profil</a>
	  		<a class="nav-link" href="${pageContext.request.contextPath}/deconnect">Se déconnecter</a>
	     </c:if> 
	     
      	<c:if test="${empty sessionScope.utilisateurCnx.pseudo }">
	       <li class="nav-item d-none d-lg-block"><a class="nav-link" href="${pageContext.request.contextPath}/inscription">M'inscrire</a></li>
	       <li class="nav-item d-none d-lg-block"><a class="nav-link" href="${pageContext.request.contextPath}/connect">Me connecter</a></li>
       </c:if>
	       	

	</ul>
    </nav>
</header>   
        
        <%-- -----------------CORPS DE LA PAGE D'ACCUEIL----------------- --%>
        <main>
            <!--title-->
            <div class="mx-auto text-center"><h1>Enchères</h1></div>
            <!--erreur-->
            <div class="d-flex alert-danger">
                <div class="col-3 p-2">
                    <img class="small-icon" src="images/error.png">
                </div>
            
                <ul class="col-9 list-unstyled p-2">
					<div>
						<c:if test="${!empty message }">
							<p class="col-9 list-unstyled p-2">${message};</p>
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
            <!--filtre-->
            <form class="form-filter border mb-3" action="#" method="">
                <div class="row">
                    <!--Partie gauche-->
                    <div class="col-md-6 mb-3">
                        <div class="form-group">
                                <label for="filter-input">Filtre</label>
                                <input type="text" class="form-control" id="filter-input" name="q" placeholder="Rechercher un article">
                        </div>
                        <div class="form-group">
                            <label for="categories-select">Catégories</label>
                            <select class="form-control" id="categories-select" name="categorie">
                                <option selected>Toutes</option>
                                <option name="categorie" value="">Informatique</option>
                                <option name="categorie" value="">Ameublement</option>
                                <option name="categorie" value="">Vêtement</option>
                                <option name="categorie" value="">Sport & Loisirs</option>
                            </select>
                        </div>
                    </div>
                    <!--Partie droite-->
                    <c:if test="${!empty sessionScope.utilisateurCnx.pseudo }">
                    <div class="col-md-6 mb-3">  	
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" checked name="type-encheres" value="achats" id="achats">Achats
                            </label>
                        </div>
                        <div class="form-group">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="checkbox" class="form-check-input" checked name="encheres" value="ouvertes" id="ouvertes">Enchères ouvertes
                                </label>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="checkbox" class="form-check-input" name="encheres" value="encours" id="encours">Mes enchères en cours
                                </label>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="checkbox" class="form-check-input" name="encheres" value="remportees" id="remportees">Mes enchères remportées
                                </label>
                            </div>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="type-encheres" value="ventes" id="ventes">Ventes
                            </label>
                        </div>
                        <div class="form-group">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="checkbox" class="form-check-input" name="ventes" value="venteencours" id="venteencours">Mes ventes en cours
                                </label>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="checkbox" class="form-check-input" name="ventes" value="nondebutees" id="nondebutees">Mes ventes non débutées
                                </label>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="checkbox" class="form-check-input" name="ventes" value="terminees" id="terminees">Mes ventes terminées
                                </label>
                            </div>
                        </div>

                    </div>
                  </c:if>
                </div>
                <div class="d-grid gap-2 d-md-block">
                <button class="btn btn-primary btn-lg" type="submit">
                	<img class="small-icon" src="images/search.png" alt="AlphaBay_Encheres">
                </button>
                </div>
            </form>

			<!--enchères-->
			<c:if test="${!empty listeArticlesVendus }">
				<c:forEach items="${listeArticlesVendus}" var="articleVendu">
					<div class="row justify-content-center border-top card-deck">
						<div class="col-12 col-sm-6 p-2">
							<div class="card">
								<div class="card-header text-center">

									<h4 class="my-0 font-weight-normal">
										<a href="${pageContext.request.contextPath }/detail/vente?nomArticle=${articleVendu.nomArticle}"
										class="btn btn-primary btn-lg btn-block"> Article : ${articleVendu.nomArticle}</a>
									</h4>
								</div>
								<div class="d-flex">
									<div class="col-3 p-2">
										<img class="img-fluid img-thumbnail" src="images/photo.png"
											alt="pas de photo" />
									</div>
									<ul class="col-9 list-unstyled p-2">
										<li>Prix :"${articleVendu.miseAPrix}"</li>
										<li>Meilleure enchère : 0 point(s)</li>
										<li>Fin de l'enchère :"${articleVendu.dateFinEnchere}"</li>
										<%--<li>$articleVendu.pseudo</li> --%>
									</ul>
								</div>
								<a class="mt-3 btn btn-lg btn-block btn-primary" href="#"
									title="faire une enchère"> <img class="small-icon"
									src="images/bid.png">
								</a>

							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
			
			
			<%-- <div class="col-12 col-sm-6 p-2" >
                    <div class="card">
                        <div class="card-header text-center">
                            <h4 class="my-0 font-weight-normal">Article 2</h4>
                        </div>
                        <div class="d-flex">
                            <div class="col-3 p-2">
                                <img class="img-fluid img-thumbnail" src="images/photo.png" alt="pas de photo" />
                            </div>
                            <ul class="col-9 list-unstyled p-2">
                                <li>Prix : 0 point(s)</li>
                                <li>Meilleure enchère : 0 point(s)</li>
                                <li>Fin de l'enchère : dd-MM-yyyy HH:mm</li>
                                <li>Vendeur : xxxxxxxxx</li>
                            </ul>
                        </div>
                        <a class="mt-3 btn btn-lg btn-block btn-primary" href="#" title="faire une enchère">
                            <img class="small-icon" src="images/bid.png">
                        </a>
                    </div>
                </div>
                <div class="col-12 col-sm-6 p-2" >
                    <div class="card">
                    <div class="card-header text-center">
                        <h4 class="my-0 font-weight-normal">Article 3</h4>
                    </div>
                    <div class="d-flex">
                        <div class="col-3 p-2">
                            <img class="img-fluid img-thumbnail" src="images/photo.png" alt="pas de photo" />
                        </div>
                        <ul class="col-9 list-unstyled p-2">
                            <li>Prix : 0 point(s)</li>
                            <li>Meilleure enchère : 0 point(s)</li>
                            <li>Fin de l'enchère : dd-MM-yyyy HH:mm</li>
                            <li>Vendeur : xxxxxxxxx</li>
                        </ul>
                    </div>
                    <a class="mt-3 btn btn-lg btn-block btn-primary" href="#" title="faire une enchère">
                        <img class="small-icon" src="images/bid.png">
                    </a>
                </div>--%>
	        </div>
        </main>

        <!--footer-->
        <footer class="border-top text-center align-bottom">
            <div class="mt-3">
                <img class="small-icon" src="images/ateni.png" alt="Eni Ecole">
                <small class="d-block text-muted">&copy; ENI Ecole 2020</small>
       		 </div>
   		 </footer>
    
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
            'use strict';
    
            window.addEventListener('load', function() {
            	checkAchats();
            	checkVentes();
                achats.addEventListener('change', function(event) {
                	checkAchats();
                }, false);
                ventes.addEventListener('change', function(event) {
                	checkVentes();
                }, false);
                
                function checkAchats() {
                	//id radio button achats
                	var achats = document.getElementById('achats');
                    if (achats.checked){
                    	//id des checkbox
                        document.getElementById('venteencours').disabled = true;
                        document.getElementById('nondebutees').disabled = true;
                        document.getElementById('terminees').disabled = true;
                        document.getElementById('encours').disabled = false;
                        document.getElementById('ouvertes').disabled = false;
                        document.getElementById('remportees').disabled = false;
                    }
                }
                function checkVentes(){
                	//id radio button ventes
                	var ventes = document.getElementById('ventes');
                    if (ventes.checked){
                    	//id des checkbox
                        document.getElementById('venteencours').disabled = false;
                        document.getElementById('nondebutees').disabled = false;
                        document.getElementById('terminees').disabled = false;
                        document.getElementById('encours').disabled = true;
                        document.getElementById('ouvertes').disabled = true;
                        document.getElementById('remportees').disabled = true;
                    }
                }
            }, false);
        })();
    </script>
</body>
	
	
</body>
</html>