package bo;

import java.time.LocalDate;

public class ArticleVendu {
	
	private String nom_article;                   
	private String description;                  
	private LocalDate date_debut_enchere;        
	private LocalDate date_fin_enchere;              
	private int prix_initial;                 
	private int prix_vente;                    
	private int no_utilisateur;                
	private int no_categorie;                  
	private char etat_vente;					  
	private String image;
	
	/**
	 * CONSTRUCTEUR PAR DEFAULT
	 */
	public ArticleVendu() {
		super();
	}

	/** CONSTRUCTEUR SURCHARGE
	 * @param nom_article
	 * @param description
	 * @param date_debut_enchere
	 * @param date_fin_enchere
	 * @param prix_initial
	 * @param prix_vente
	 * @param no_utilisateur
	 * @param no_categorie
	 * @param etat_vente
	 * @param image
	 */
	public ArticleVendu(String nom_article, String description, LocalDate date_debut_enchere,
			LocalDate date_fin_enchere, int prix_initial, int prix_vente, int no_utilisateur, int no_categorie,
			char etat_vente, String image) {
		super();
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_enchere = date_debut_enchere;
		this.date_fin_enchere = date_fin_enchere;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.etat_vente = etat_vente;
		this.image = image;
	}

/* **** GETTER AND SETTER**** */	
//NOM ARTICLE	
	/** @return the nom_article*/
	public String getNom_article() {
		return nom_article;
	}
	/**@param nom_article the nom_article to set*/
	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}
	
//DESCRIPTION
	/** @return the description*/
	public String getDescription() {
		return description;
	}
	/** @param description the description to set*/
	public void setDescription(String description) {
		this.description = description;
	}

//DATE DEBUT ENCHERES
	/** @return the date_debut_enchere*/
	public LocalDate getDate_debut_enchere() {
		return date_debut_enchere;
	}
	/** @param date_debut_enchere the date_debut_enchere to set*/
	public void setDate_debut_enchere(LocalDate date_debut_enchere) {
		this.date_debut_enchere = date_debut_enchere;
	}

//DATE FIN ENCHERES	
	/** @return the date_fin_enchere*/
	public LocalDate getDate_fin_enchere() {
		return date_fin_enchere;
	}
	/** @param date_fin_enchere the date_fin_enchere to set*/
	public void setDate_fin_enchere(LocalDate date_fin_enchere) {
		this.date_fin_enchere = date_fin_enchere;
	}

//PRIX INITIAL
	/** @return the prix_initial*/
	public int getPrix_initial() {
		return prix_initial;
	}
	/** @param prix_initial the prix_initial to set*/
	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}

//PRIX DE VENTE
	/** @return the prix_vente*/
	public int getPrix_vente() {
		return prix_vente;
	}
	/** @param prix_vente the prix_vente to set*/
	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}
	
//N° UTILISATEUR
	/** @return the no_utilisateur*/
	public int getNo_utilisateur() {
		return no_utilisateur;
	}
	/** @param no_utilisateur the no_utilisateur to set*/
	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

//N° CATEGORIE
	/**@return the no_categorie*/
	public int getNo_categorie() {
		return no_categorie;
	}
	/**@param no_categorie the no_categorie to set**/
	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}

//ETAT DE VENTE
	/**@return the etat_vente*/
	public char getEtat_vente() {
		return etat_vente;
	}
	/**@param etat_vente the etat_vente to set*/
	public void setEtat_vente(char etat_vente) {
		this.etat_vente = etat_vente;
	}

//IMAGE
	/**@return the image*/
	public String getImage() {
		return image;
	}
	/**@param image the image to set*/
	public void setImage(String image) {
		this.image = image;
	}

	
	
	
	
}
