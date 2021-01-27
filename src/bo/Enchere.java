package bo;

import java.time.LocalDateTime;

public class Enchere {
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	private Utilisateur utilisateur;
	private ArticleVendu articleVendu;
	/**
	 * 
	 */
	public Enchere() {
		super();
	}
	/**
	 * @param dateEnchere
	 * @param montantEnchère
	 */
	public Enchere(LocalDateTime dateEnchere, int montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	/**
	 * @param dateEnchere
	 * @param montantEnchère
	 * @param noUtilisateur
	 * @param noArticle
	 */
	public Enchere(LocalDateTime dateEnchere, int montantEnchere, Utilisateur utilisateur, ArticleVendu noArticle) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateur = utilisateur;
		this.articleVendu = noArticle;
	}
	
	/**
	 * @return the dateEnchere
	 */
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}
	/**
	 * @param localDateTime the dateEnchere to set
	 */
	public void setDateEnchere(LocalDateTime localDateTime) {
		this.dateEnchere = localDateTime;
	}
	/**
	 * @return the montantEnchère
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}
	/**
	 * @param montantEnchère the montantEnchère to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	/**
	 * @return the noUtilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	/**
	 * @param noUtilisateur the noUtilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	/**
	 * @return the noArticle
	 */
	public ArticleVendu getArticle() {
		return articleVendu;
	}
	/**
	 * @param noArticle the noArticle to set
	 */
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	
	
	

}
