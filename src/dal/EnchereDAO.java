package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import bo.Encheres;
import exceptions.BuisnessException;

public class EnchereDAO {

	private static final String INSERT = " INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere"
			+ "VALUES (?,?,?,?)";
	
	public void insert(Encheres enchere) throws SQLException {
		
		
		PreparedStatement pstmt;
		ResultSet rs;
	
		try {
			Connection cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT);
			
			pstmt.setInt(1, enchere.getNoUtilisateur());
			pstmt.setInt(2, enchere.getNoArticle());
			
			//pstmt.setLocalDateTime(3, enchere.getDateEnchere().toLocalDate(),enchere.getDateEnchere().toLocalTime());
			pstmt.setTimestamp(3, Timestamp.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontantEnchère());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			BuisnessException be = new BuisnessException();
			be.ajouterErreur("Problème d'insertion de l'enchere dans la base. Cause :" + e.getMessage());
			throw e;
	
		}

		
	}
	
}
