package bo;

import java.util.List;

public class Categorie {
	
	private int noCategorie;
	private String libelle;
	private List<ArticleVendu> listeArticles;
	/**
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}
	/**
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return the listeArticles
	 */
	public List<ArticleVendu> getListeArticles() {
		return listeArticles;
	}
	/**
	 * @param listeArticles the listeArticles to set
	 */
	public void setListeArticles(List<ArticleVendu> listeArticles) {
		this.listeArticles = listeArticles;
	}
	
	/**
	 * 
	 */
	public Categorie() {
		super();
	}
	
	/**
	 * @param noCategorie
	 * @param libelle
	 * @param listeArticles
	 */
	public Categorie(int noCategorie, String libelle, List<ArticleVendu> listeArticles) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.listeArticles = listeArticles;
	}
	/**
	 * @param libelle
	 */
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}
	
	
	

}
