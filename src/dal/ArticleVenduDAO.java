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
import bo.Utilisateur;
import exceptions.BuisnessException;

public class ArticleVenduDAO {
	
	private static final String INSERT = " INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_enchere,"
			+ " date_fin_enchere, prix_initial, no_categorie, no_utilisateur)" +
			" VALUES (?,?,?,?,?,?,?)";
	private static final String SELECT =" SELECT " + 
			
			"	no_article," + 
			"	nom_article," +
			"	prix_initial," +
			"	date_fin_enchere," + 
			"	no_utilisateur," + 
			"	pseudo" + 
			"	FROM ARTICLES_VENDUS "; 
			//"	INNER JOIN  ENCHERES e ON e.a.no_article=a.no_article" +
			//"	INNER JOIN UTILISATEURS u ON u.no_utilisateur=a.no_utilisateur" +
			//"	ORDER BY date_fin_enchere desc";	


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
	


	public List<ArticleVendu> select() throws Exception {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<ArticleVendu>();
		ArticleVendu articleVendu = new ArticleVendu();

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT);
			while(rs.next())
			{
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
				articleVendu.setDateFinEnchere(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()), rs.getTime("date_fin_enchere").toLocalTime()));
				//enchereLu.setPseudo(rs.getString("pseudo"));

				listeArticlesVendus.add(articleVendu);	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();

			throw e;
		}
		return listeArticlesVendus;
	}
}
