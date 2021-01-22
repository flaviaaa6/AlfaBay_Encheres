package bo;

import java.util.List;

public class Categorie {
	
	private int noCategorie;
	private String libelle;
	
	
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
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		
	}
	/**
	 * @param libelle
	 */
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}
	
	
	

}
