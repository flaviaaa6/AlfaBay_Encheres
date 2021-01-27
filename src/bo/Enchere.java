package bo;

import java.time.LocalDateTime;

public class Enchere {
	private LocalDateTime dateEnchere;
	private int montantEnchère;
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
	public Enchere(LocalDateTime dateEnchere, int montantEnchère) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchère = montantEnchère;
	}
	
	/**
	 * @param dateEnchere
	 * @param montantEnchère
	 * @param noUtilisateur
	 * @param noArticle
	 */
	public Enchere(LocalDateTime dateEnchere, int montantEnchère, Utilisateur utilisateur, ArticleVendu noArticle) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchère = montantEnchère;
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
	public int getMontantEnchère() {
		return montantEnchère;
	}
	/**
	 * @param montantEnchère the montantEnchère to set
	 */
	public void setMontantEnchère(int montantEnchère) {
		this.montantEnchère = montantEnchère;
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
