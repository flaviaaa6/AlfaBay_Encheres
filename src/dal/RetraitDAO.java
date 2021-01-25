package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import bo.Retrait;
import exceptions.BuisnessException;

public class RetraitDAO {

	
	private static final String INSERT = " INSERT INTO RETRAITS (no_article, rue, code_postal,"
			+ " ville) VALUES (?,?,?,?)";
	
	public void insert(Retrait retrait) throws SQLException {
		
		
		PreparedStatement pstmt;
		ResultSet rs;
	
		try {
			Connection cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT);
			
			pstmt.setInt(1, retrait.getId_article());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
		
			
	
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			BuisnessException be = new BuisnessException();
			be.ajouterErreur("Problème d'insertion du retrait dans la base. Cause :" + e.getMessage());
			throw e;
	
		}

		
	}
}
