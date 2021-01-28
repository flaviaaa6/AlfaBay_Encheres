package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Enchere;
import bo.Retrait;
import bo.Utilisateur;
import exceptions.BuisnessException;

public class ArticleVenduDAO {
	
	private static final String INSERT = " INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_enchere,"
			+ " date_fin_enchere, prix_initial, no_categorie, no_utilisateur, etat_vente)" +
			" VALUES (?,?,?,?,?,?,?,?)";
	
	private  final  static  String  SELECT =	"select * from ARTICLES_VENDUS a "
			+	"INNER JOIN CATEGORIES c ON c.no_categorie=a.no_categorie "
			+	"INNER JOIN UTILISATEURS u ON  u.no_utilisateur=a.no_utilisateur "
			+   "LEFT OUTER JOIN ENCHERES e ON e.no_article=a.no_article "
			+	"where etat_vente = 'EC'"; 
	
	private static final String SELECTBYNAME =" SELECT * from ARTICLES_VENDUS a " 
			+ " LEFT OUTER JOIN ENCHERES e ON e.no_article=a.no_article " 
			+ "	INNER JOIN UTILISATEURS u ON u.no_utilisateur=a.no_utilisateur "
			+ " LEFT OUTER JOIN RETRAITS r ON r.no_article=a.no_article "
			+ " INNER JOIN CATEGORIES c ON c.no_categorie=a.no_categorie "
			+ " WHERE nom_article = ? "
			+ " ORDER BY date_enchere DESC";

	private  final  static  String SELECT_MY_ENCOURS =	"select * from ARTICLES_VENDUS a "
			+	"RIGHT OUTER JOIN UTILISATEURS u ON  u.no_utilisateur=a.no_utilisateur "
			+ 	"WHERE etat_vente = 'EC' "
			+ 	"ORDER BY date_fin_enchere DESC ";		

	private static final String SELECT_NO_BEGIN = "select * from ARTICLES_VENDUS a "
			+	"RIGHT OUTER JOIN UTILISATEURS u ON  u.no_utilisateur=a.no_utilisateur "
			+ 	"WHERE GETDATE() < date_debut_enchere "
			+ 	"ORDER BY date_debut_enchere DESC " ; 

	private static final String SELECT_FINISH = 	"select * from ARTICLES_VENDUS a "
			+	"RIGHT OUTER JOIN UTILISATEURS u ON  u.no_utilisateur=e.no_utilisateur "
			+ 	"WHERE GETDATE() > date_fin_enchere "
			+ 	"ORDER BY date_fin_enchere DESC "; 


	private  final  static  String ENCHERES_OUVERTES =	"select * from ENCHERES e "
			+	"LEFT OUTER JOIN  ARTICLES_VENDUS a ON a.no_article=e.no_article "
			+	"RIGHT JOIN UTILISATEURS u ON  u.no_utilisateur=e.no_utilisateur "
			+ 	"ORDER BY date_debut_enchere DESC "
			+ 	"WHERE (GETDATE() BETWEEN date_debut_enchere AND date_fin_enchere)";

	private static final String SELECT_BY_PSEUDO = "select * from ENCHERES e "
			+	"LEFT OUTER JOIN  ARTICLES_VENDUS a ON a.no_article=e.no_article "
			+	"INNER JOIN UTILISATEURS u ON  u.no_utilisateur=e.no_utilisateur "
			+ 	"where pseudo = '?' AND montant_enchere != NULL AND prix_vente = NULL";

	private static final String SELECT_WIN = 	"select * from ENCHERES e "
			+	"LEFT OUTER JOIN  ARTICLES_VENDUS a ON a.no_article=e.no_article "
			+	"INNER JOIN UTILISATEURS u ON  u.no_utilisateur=e.no_utilisateur "
			+ 	"where pseudo = '?' AND montant_enchere != NULL ";
	
	
	public ArticleVendu insert(ArticleVendu article) throws SQLException {
		
		
			PreparedStatement pstmt;
			ResultSet rs;
		
			try {
				Connection cnx = ConnectionProvider.getConnection();
				pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				
				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescription());
				pstmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEnchere()));
				pstmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEnchere()));
				pstmt.setInt(5, article.getMiseAPrix());
				pstmt.setInt(6, article.getCategorie().getNoCategorie());
				pstmt.setInt(7,article.getUtilisateur().getNoUtilisateur());
				pstmt.setString(8,article.getEtatVente());
				
			//	LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()), rs.getTime("date_enchere").toLocalTime())
				
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				
				if(rs.next()){
					article.setNoArticle(rs.getInt(1));

				}
				
			} catch (SQLException e) {
				BuisnessException be = new BuisnessException();
				be.ajouterErreur("Problème d'insertion de l'article dans la base. Cause :" + e.getMessage());
				throw e;
			//	}			throw new Exception("Problème d'insertion de l'utilisateur dans la base. Cause : " + e.getMessage());
			}

			return article;
		}
	


	public  List <ArticleVendu> select () throws SQLException {

		ArticleVendu articleVendu = new ArticleVendu ();
		List <ArticleVendu> listeRetourArticle =  new ArrayList <> ();
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx . createStatement ();
			rs = stmt . executeQuery (SELECT);

			while (rs . next ()) {
				articleVendu = new ArticleVendu();
				articleVendu.setNoArticle(rs.getInt ("no_article"));
				articleVendu.setNomArticle(rs.getString ("nom_article"));
				articleVendu.setDescription(rs.getString ("description"));
				articleVendu.setDateDebutEnchere(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()), rs.getTime("date_debut_enchere").toLocalTime()));
				articleVendu.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()), rs.getTime("date_fin_enchere").toLocalTime()));
				articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
				articleVendu.setPrixVente (rs.getInt("prix_vente"));
				articleVendu.setEtatVente (rs.getString("etat_vente"));

				Utilisateur vendeur = new Utilisateur();
				vendeur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				vendeur.setPseudo(rs.getString("pseudo"));
				vendeur.setNom(rs.getString("nom"));
				vendeur.setPrenom(rs.getString("prenom"));
				vendeur.setEmail(rs.getString("email"));
				vendeur.setTelephone(rs.getString("telephone"));
				vendeur.setRue(rs.getString("rue"));
				vendeur.setCodePostal(rs.getString("code_postal"));
				vendeur.setVille(rs.getString("ville"));
				vendeur.setMotDePasse(rs.getString("mot_de_passe"));
				vendeur.setCredit(rs.getInt("credit"));
				vendeur.setAdministrateur(rs.getBoolean("administrateur"));
				articleVendu.setUtilisateur(vendeur);

				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				articleVendu.setCategorie(categorie);
				
				Enchere enchere = null;
				if(rs.getDate("date_enchere") !=  null) {
					enchere = new Enchere();
					enchere.setDateEnchere(LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()), rs.getTime("date_enchere").toLocalTime()));
					enchere.setMontantEnchere(rs.getInt("montant_enchere"));						
				}
				articleVendu.setEnchere(enchere);
				
				
				listeRetourArticle.add(articleVendu);

			}
		} catch ( SQLException e) {		
			e.printStackTrace();
		}
		try {
			if (stmt != null ) stmt.close();
			if (cnx != null ) cnx.close();
		} catch ( SQLException e) {
			e.printStackTrace();
		}

		return listeRetourArticle;		
	}	
	
public ArticleVendu selectByName(String nomArticle) throws Exception {
		
		ArticleVendu detailArticle = new ArticleVendu();
		




			try {
				Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement rqt = cnx.prepareStatement(SELECTBYNAME);

				rqt.setString(1, nomArticle);
				
				ResultSet rs = rqt.executeQuery();
				if(rs.next())
				{					
					detailArticle.setNomArticle(rs.getString("nom_article"));
					detailArticle.setDescription(rs.getString("description"));
					detailArticle.setMiseAPrix(rs.getInt("prix_initial"));
					detailArticle.setDateDebutEnchere(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()), rs.getTime("date_debut_enchere").toLocalTime()));
					detailArticle.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()), rs.getTime("date_fin_enchere").toLocalTime()));
					detailArticle.setNoArticle(rs.getInt("no_article"));
					detailArticle.setMiseAPrix(rs.getInt("prix_initial"));
					
					Categorie categ = new Categorie();
					categ.setLibelle(rs.getString("libelle"));
					detailArticle.setCategorie(categ);
					
					
					Enchere enchere = null;
					if(rs.getDate("date_enchere") !=  null) {
						enchere = new Enchere();
						enchere.setDateEnchere(LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()), rs.getTime("date_enchere").toLocalTime()));
						enchere.setMontantEnchere(rs.getInt("montant_enchere"));						
					}
					detailArticle.setEnchere(enchere);
					
					Retrait retrait = new Retrait();
				
					retrait.setRue(rs.getString("rue"));
					retrait.setCodePostal(rs.getString("code_postal"));
					retrait.setVille(rs.getString("ville"));
					detailArticle.setRetrait(retrait);

					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setPseudo(rs.getString("pseudo"));
					utilisateur.setCredit(rs.getInt("credit"));
					detailArticle.setUtilisateur(utilisateur);
					
				}
			} catch (SQLException e) {
				//propager une exception personnalisée
				throw new Exception("Problème d'extraction des l'article de la base. Cause : " + e.getMessage());
			}

			return detailArticle;
	}
	

public List <ArticleVendu> SelectMyEncours(Utilisateur pseudo) throws SQLException {
	List <ArticleVendu> vMyEncours = new ArrayList <ArticleVendu>();
	ArticleVendu myEncours = new ArticleVendu();
	
	Connection cnx = null;
	ResultSet rs = null;
	try {
		cnx = ConnectionProvider.getConnection();
		PreparedStatement stmt = cnx.prepareStatement(SELECT_MY_ENCOURS);
		rs = stmt . executeQuery ();

		while (rs . next ()) {
			Utilisateur vendeur = new Utilisateur();
			vendeur.setPseudo(rs.getString ("pseudo"));
			myEncours.setUtilisateur(vendeur);

			myEncours.setNomArticle(rs.getString("nom_article"));
			myEncours.setMiseAPrix(rs.getInt("prix_initial"));
			myEncours.setDateDebutEnchere(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()), rs.getTime("date_debut_enchere").toLocalTime()));
			myEncours.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()), rs.getTime("date_fin_enchere").toLocalTime()));

			vMyEncours.add(myEncours);
		}
	} catch ( SQLException e) {		
		e.printStackTrace();
	}
	try {
		if (cnx != null ) cnx.close();
	} catch ( SQLException e) {
		e.printStackTrace();
	}
	return vMyEncours;	

}



public List <ArticleVendu> selectNoBegin(Utilisateur pseudo) throws SQLException{
	List <ArticleVendu> vNoBegin = new ArrayList <ArticleVendu>();
	ArticleVendu noBegin= new ArticleVendu();
	
	Connection cnx = null;
	ResultSet rs = null;
	try {
		cnx = ConnectionProvider.getConnection();
		PreparedStatement stmt = cnx.prepareStatement(SELECT_NO_BEGIN);
		rs = stmt . executeQuery ();
		
		while(rs.next())
		{	
			noBegin.setNomArticle(rs.getString("nom_article"));
			noBegin.setMiseAPrix(rs.getInt("prix_initial"));
			noBegin.setDateDebutEnchere(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),rs.getTime("date_debut_enchere").toLocalTime()));
			noBegin.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),rs.getTime("date_fin_enchere").toLocalTime()));

			Utilisateur vendeur = new Utilisateur();
			vendeur.setPseudo(rs.getString ("pseudo"));
			noBegin.setUtilisateur(vendeur);

			vNoBegin.add(noBegin);
		}
	} catch (SQLException e) {

	}

	return vNoBegin;
}


public List <ArticleVendu> selectFinish(Utilisateur pseudo) throws SQLException{

	ArticleVendu finish = new ArticleVendu();
	List <ArticleVendu> vFinish = new ArrayList <ArticleVendu>();

	Connection cnx = null;
	ResultSet rs = null;
	try {
		cnx = ConnectionProvider.getConnection();
		PreparedStatement stmt = cnx.prepareStatement(SELECT_FINISH);
		rs = stmt . executeQuery ();
		while(rs.next())
		{	
			finish.setNomArticle(rs.getString("nom_article"));
			finish.setNomArticle(rs.getString ("prix_initial"));
			finish.setDateDebutEnchere(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),rs.getTime("date_debut_enchere").toLocalTime()));
			finish.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),rs.getTime("date_fin_enchere").toLocalTime()));

			Utilisateur vendeur = new Utilisateur();
			vendeur.setPseudo(rs.getString ("pseudo"));
			finish.setUtilisateur(vendeur);
			
			vFinish.add(finish);

		}
	} catch (SQLException e) {

	}

	return vFinish;
}


public List <ArticleVendu> SelectOpen() throws SQLException {
	List <ArticleVendu> listeArticle = new ArrayList <ArticleVendu> ();
	ArticleVendu article = new ArticleVendu ();
	
	Connection cnx = null;
	ResultSet rs = null;
	try {
		cnx = ConnectionProvider.getConnection();
		PreparedStatement stmt = cnx.prepareStatement(ENCHERES_OUVERTES);
		rs = stmt . executeQuery ();
		while (rs . next ()) {

			
			article.setNomArticle(rs.getString ("nom_article"));
			article.setMiseAPrix(rs.getInt ("prix_initial"));
			article.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()), rs.getTime("date_fin_enchere").toLocalTime()));

			Utilisateur vendeur = new Utilisateur();
			vendeur.setPseudo(rs.getString ("pseudo"));
			article.setUtilisateur(vendeur);
			
			Enchere enchere = new Enchere();
			enchere.setMontantEnchere(rs.getInt ("montant_enchere"));
			article.setEnchere(enchere);

			listeArticle.add(article);

		}
	} catch ( SQLException e) {		
		e.printStackTrace();
	}
	return listeArticle;	

}



public List <ArticleVendu> selectByPseudo( Utilisateur pseudo) throws SQLException{

	List <ArticleVendu> enchereByPseudo = new ArrayList<ArticleVendu> ();
	ArticleVendu article  = new ArticleVendu ();

	Connection cnx = null;
	ResultSet rs = null;
	try {
		cnx = ConnectionProvider.getConnection();
		PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
		rs = stmt . executeQuery ();
		
		if(rs.next())
		{	
			article.setNomArticle(rs.getString("nom_article"));
			article.setMiseAPrix(rs.getInt("prix_initial"));
			article.setDateDebutEnchere(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),rs.getTime("date_debut_enchere").toLocalTime()));
			article.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),rs.getTime("date_fin_enchere").toLocalTime()));

			Utilisateur acheteur = new Utilisateur();
			acheteur.setPseudo(rs.getString ("pseudo"));
			article.setUtilisateur(acheteur);
			
			Enchere encher = new Enchere();
			encher.setMontantEnchere(rs.getInt("montant_enchere"));
			encher.setDateEnchere(LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()),rs.getTime("date_enchere").toLocalTime()));
			article.setEnchere(encher);
			
		}
	} catch (SQLException e) {

	}

	return enchereByPseudo;
}


public List <ArticleVendu> select_Win(Utilisateur pseudo) throws SQLException{
	List <ArticleVendu> enchereWin = new ArrayList<ArticleVendu> ();
	ArticleVendu articleWin  = new ArticleVendu ();

	Connection cnx = null;
	ResultSet rs = null;
	try {
		cnx = ConnectionProvider.getConnection();
		PreparedStatement stmt = cnx.prepareStatement(SELECT_WIN);
		rs = stmt . executeQuery ();
		if(rs.next())
		{	
			articleWin.setNomArticle(rs.getString("nom_article"));
			articleWin.setMiseAPrix(rs.getInt("prix_initial"));


			Utilisateur acheteur = new Utilisateur();
			acheteur.setPseudo(rs.getString ("pseudo"));
			articleWin.setUtilisateur(acheteur);

			Enchere encher = new Enchere();
			encher.setMontantEnchere(rs.getInt("montant_enchere"));
			encher.setDateEnchere(LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()),
					rs.getTime("date_enchere").toLocalTime()));
			
			articleWin.setEnchere(encher);
			
			enchereWin.add(articleWin);

		}
	} catch (SQLException e) {

	}

	return enchereWin;
}
}
