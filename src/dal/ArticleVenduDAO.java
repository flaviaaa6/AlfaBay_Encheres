package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import bo.ArticleVendu;
import bo.Utilisateur;
import exceptions.BuisnessException;

public class ArticleVenduDAO {
	
	private static final String INSERT = " INSERT INTO ARTICLES_VENDUES (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, no_categorie, no_utilisateur)" +
			" VALUES (?,?,?,?,?,?,?)";
	
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
	

}
