package bo;

public class Retrait {
	
	private int id_article;
	
	private String rue;
	private String codePostal;
	private String ville;
	
	
	/**
	 * @return the id_article
	 */
	public int getId_article() {
		return id_article;
	}
	/**
	 * @param id_article the id_article to set
	 */
	public void setId_article(int id_article) {
		this.id_article = id_article;
	}
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}
	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
	
	/**
	 * constructeur par defaut
	 */
	public Retrait() {
		super();
	}
	/**
	 * constructeur surchargé
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
}
