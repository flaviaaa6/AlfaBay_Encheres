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
import bo.Encheres;
import bo.Retrait;
import bo.Utilisateur;
import exceptions.BuisnessException;

public class ArticleVenduDAO {
	
	private static final String INSERT = " INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_enchere,"
			+ " date_fin_enchere, prix_initial, no_categorie, no_utilisateur)" +
			" VALUES (?,?,?,?,?,?,?)";
	
	private  final  static  String  SELECT =  "select * from ARTICLES_VENDUS a"
			+ 	"INNER JOIN UTILISATEURS u ON u.no_utilisateur=a.no_utilisateur where etat_vente = 'EC'"; 
	
	private static final String SELECTBYID =" SELECT * from ARTICLES_VENDUS WHERE no_utilisateur = ?" 
			+ " INNER JOIN  ENCHERES e ON e.a.no_article=a.no_article" 
			+ "	INNER JOIN UTILISATEURS u ON u.no_utilisateur=a.no_utilisateur"
			+ "INNER JOIN RETRAITS r ON r.no_article=a.no_article"
			+ "INNER JOIN CATEGORIES c ON c.no_categorie=a.no_categorie";


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
	


	public static  List <ArticleVendu> select () throws BuisnessException {

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
				//articleVendu.setNoCategorie(rs.getInt("no_categorie"));

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
	
public ArticleVendu selectById(int id) throws Exception {
		
		ArticleVendu detailArticle = new ArticleVendu();
		




			try {
				Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement rqt = cnx.prepareStatement(SELECTBYID);

				rqt.setInt(1, id);
				
				ResultSet rs = rqt.executeQuery();
				if(rs.next())
				{

					
					detailArticle.setNomArticle(rs.getString("nom_article"));
					detailArticle.setMiseAPrix(rs.getInt("prix_initial"));
					detailArticle.setDateDebutEnchere(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()), rs.getTime("date_debut_enchere").toLocalTime()));
					detailArticle.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()), rs.getTime("date_fin_enchere").toLocalTime()));
					detailArticle.setNoArticle(rs.getInt("no_article"));
					detailArticle.setMiseAPrix(rs.getInt("prix_initial"));
					
					Categorie categ = new Categorie();
					categ.setLibelle(rs.getString("libelle"));
					detailArticle.setCategorie(categ);
					
					Encheres enchere = new Encheres();
					enchere.setDateEnchere(LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()), rs.getTime("date_enchere").toLocalTime()));
					enchere.setMontantEnchère(rs.getInt("montant_enchere"));
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
	
}
