package bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Encheres {
	private LocalDateTime dateEnchere;
	private int montantEnchère;
	private Utilisateur noUtilisateur;
	private ArticleVendu noArticle;
	/**
	 * 
	 */
	public Encheres() {
		super();
	}
	/**
	 * @param dateEnchere
	 * @param montantEnchère
	 */
	public Encheres(LocalDateTime dateEnchere, int montantEnchère) {
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
	public Encheres(LocalDateTime dateEnchere, int montantEnchère, Utilisateur noUtilisateur, ArticleVendu noArticle) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchère = montantEnchère;
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
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
	public Utilisateur getNoUtilisateur() {
		return noUtilisateur;
	}
	/**
	 * @param noUtilisateur the noUtilisateur to set
	 */
	public void setNoUtilisateur(Utilisateur noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	/**
	 * @return the noArticle
	 */
	public ArticleVendu getNoArticle() {
		return noArticle;
	}
	/**
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(ArticleVendu noArticle) {
		this.noArticle = noArticle;
	}
	
	
	

}
