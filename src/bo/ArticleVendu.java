package bo;

import java.time.LocalDateTime;


public class ArticleVendu {
	
	private int noArticle;
	private String nomArticle;                   
	private String description;                  
	private LocalDateTime dateDebutEnchere;        
	private LocalDateTime dateFinEnchere;              
	private int miseAPrix;                 
	private int prixVente;   
	private String etatVente;
	private Utilisateur noUtilisateur;                
	private Categorie noCategorie;
	
	
	/**
	 * Constructeur par défaut
	 */
	public ArticleVendu() {
		super();
	}
	



	/**
	 * Constructeur surchargé
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEnchere
	 * @param dateFinEnchere
	 * @param miseAPrixl
	 * @param prixVente
	 * @param etatVente
	 * @param noUtilisateur
	 * @param noCategorie
	 */
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEnchere,
			LocalDateTime dateFinEnchere, int miseAPrix, int prixVente, String etatVente, Utilisateur noUtilisateur,
			Categorie noCategorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}



	/**
	 * @return the noArticle
	 */
	public int getNoArticle() {
		return noArticle;
	}



	/**
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}



	/**
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}



	/**
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}



	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return the dateDebutEnchere
	 */
	public LocalDateTime getDateDebutEnchere() {
		return dateDebutEnchere;
	}



	/**
	 * @param dateDebutEnchere the dateDebutEnchere to set
	 */
	public void setDateDebutEnchere(LocalDateTime dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}



	/**
	 * @return the dateFinEnchere
	 */
	public LocalDateTime getDateFinEnchere() {
		return dateFinEnchere;
	}



	/**
	 * @param dateFinEnchere the dateFinEnchere to set
	 */
	public void setDateFinEnchere(LocalDateTime dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}



	/**
	 * @return the miseAPrixl
	 */
	public int getMiseAPrix() {
		return miseAPrix;
	}



	/**
	 * @param miseAPrixl the miseAPrixl to set
	 */
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}



	/**
	 * @return the prixVente
	 */
	public int getPrixVente() {
		return prixVente;
	}



	/**
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}



	/**
	 * @return the etatVente
	 */
	public String getEtatVente() {
		return etatVente;
	}



	/**
	 * @param etatVente the etatVente to set
	 */
	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
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
	 * @return the noCategorie
	 */
	public Categorie getNoCategorie() {
		return noCategorie;
	}



	/**
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(Categorie noCategorie) {
		this.noCategorie = noCategorie;
	}                  
						  
	
	
	
	
}
