package manager;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Utilisateur;
import dal.ArticleVenduDAO;
import exceptions.BuisnessException;

public class ArticleVenduManager {
	BuisnessException exception = new BuisnessException();
	private ArticleVenduDAO dao = new ArticleVenduDAO();
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


	public List<ArticleVendu> select() throws SQLException{
		List<ArticleVendu> listeArticlesVendus = new ArrayList<ArticleVendu>();
		listeArticlesVendus = dao.select();
			return listeArticlesVendus;
		}
	
	
	
	
	public ArticleVendu selectByName(String nomArticle) throws Exception {
		ArticleVendu detailArticle = new ArticleVendu();
		detailArticle = dao.selectByName(nomArticle);
		
		return detailArticle;
	}
	
	public List<ArticleVendu> SelectMyEncours(Utilisateur pseudo) throws SQLException{
		List<ArticleVendu> vEncours = new ArrayList<ArticleVendu>();
		vEncours =dao.SelectMyEncours(pseudo);
		
		return vEncours;
	}
	
	public List<ArticleVendu> SelectNoBegin(Utilisateur pseudo) throws SQLException{
		List<ArticleVendu> vNoBegin = new ArrayList<ArticleVendu>();
		vNoBegin=dao.selectNoBegin(pseudo);
		
		return vNoBegin;
	}
	
	public List<ArticleVendu>  SelectFinish(Utilisateur pseudo) throws SQLException{
		List<ArticleVendu> vFinish = new ArrayList<ArticleVendu>();
		vFinish=dao.selectFinish(pseudo);
		
		return vFinish;	
	}
	
	public List<ArticleVendu> SelectOpen() throws SQLException{
		List<ArticleVendu>  enchereAll = new ArrayList<ArticleVendu>();
		enchereAll =dao.SelectOpen();
		
		return enchereAll;
		
	}
	
	public List<ArticleVendu> Select_By_Pseudo(Utilisateur pseudo) throws SQLException{
		List<ArticleVendu> enchereByPseudo = new ArrayList<ArticleVendu>();
		enchereByPseudo=dao.selectByPseudo(pseudo);
		
		return enchereByPseudo;
	}
	
	public List<ArticleVendu> Select_Win(Utilisateur pseudo) throws SQLException{
		List<ArticleVendu> enchereWin = new ArrayList<ArticleVendu>();
		enchereWin=dao.select_Win(pseudo);
		
		return enchereWin;	
	}

}
