package manager;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Retrait;
import bo.Utilisateur;
import dal.ArticleVenduDAO;
import exceptions.BuisnessException;

public class ArticleVenduManager {
	BuisnessException exception = new BuisnessException();
	private ArticleVenduDAO dao;
	List<ArticleVendu> listearticle = new ArrayList<ArticleVendu>();
	
	
	//methode pour inserer un article
	public ArticleVendu insereArticle(String nom, String description, LocalDateTime dateDebut, LocalDateTime dateFin, 
			int prixInit, Categorie categorie,Utilisateur utilisateur ) throws SQLException {
		
		//creation de l'objet article:
		ArticleVendu article = new ArticleVendu(nom, description,  dateDebut, dateFin, prixInit, utilisateur, categorie);
		
		//envoi a la dal:
		dao = new ArticleVenduDAO();
		
		
		article = dao.insert(article);
		
			
		return article;
	
	}

	

	public List<ArticleVendu> select() throws Exception
	{
	listearticle = dao.select();
	
		return listearticle;
	}
	
	
	
	public ArticleVendu selectById(int id) throws Exception {
		
		ArticleVendu detailArticle = new ArticleVendu();
		detailArticle = dao.selectById(id);
		
		
		return detailArticle;
		
	}

}
