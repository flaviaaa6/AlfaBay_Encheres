package manager;

import java.sql.SQLException;
import java.time.LocalDateTime;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Utilisateur;
import dal.ArticleVenduDAO;
import exceptions.BuisnessException;

public class ArticleVenduManager {
	BuisnessException exception = new BuisnessException();
	
	//methode pour inserer un article
	public ArticleVendu insereArticle(String nom, String description, LocalDateTime dateDebut, LocalDateTime dateFin, int prixInit, Categorie categorie,Utilisateur utilisateur ) throws SQLException {
		
		//creation de l'objet article:
		ArticleVendu article = new ArticleVendu(nom, description,  dateDebut, dateFin, prixInit, utilisateur, categorie);
		
		//envoi a la dal:
		ArticleVenduDAO dao = new ArticleVenduDAO();
		
		
		article = dao.insert(article);
		
			
		return article;
	
	}

}
