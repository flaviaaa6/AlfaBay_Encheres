package manager;

import java.time.LocalDateTime;

import bo.ArticleVendu;
import bo.Categorie;
import dal.ArticleVenduDAO;

public class ArticleVenduManager {
	
	
	//methode pour inserer un article
	public void insereArticle(String nom, String description, String categorie, LocalDateTime dateDebut, LocalDateTime dateFin, int prixInit ) {
		
		//creation de l'objet:
		
		
		Categorie maCategorie = new Categorie();
		maCategorie.setLibelle(categorie);
		ArticleVendu article = new ArticleVendu(nom, description,  dateDebut, dateFin, prixInit, maCategorie);
		
		ArticleVenduDAO dao = new ArticleVenduDAO();
	//	dao.insert(article);
	}

}
