package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Utilisateur;

public class UtilisateurDAO {
	
	private static final String SELECT = "SELECT * FROM UTILISATEURS WHERE  pseudo = ? AND mot_de_passe = ? ";
	private static final String INSERT = " INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe)" +
							" VALUES (?,?,?,?,?,?,?,?,?)";
	
	Utilisateur utilisateurCnx;
	
	public Utilisateur select(Utilisateur u) throws Exception {
		
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(SELECT);
			
			rqt.setString(1, u.getPseudo());
			rqt.setString(2, u.getMotDePasse());
			
			
			ResultSet rs = rqt.executeQuery();
			if(rs.next())
			{
				
				utilisateurCnx=new Utilisateur();
				utilisateurCnx.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateurCnx.setPseudo(rs.getString("pseudo"));
				utilisateurCnx.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateurCnx.setNom(rs.getString("nom"));
				utilisateurCnx.setPrenom(rs.getString("prenom"));
				utilisateurCnx.setEmail(rs.getString("email"));
				utilisateurCnx.setTelephone(rs.getString("telephone"));
				utilisateurCnx.setRue(rs.getString("rue"));
				utilisateurCnx.setCodePostal(rs.getString("code_postal"));
				utilisateurCnx.setVille(rs.getString("ville"));
				utilisateurCnx.setCredit(rs.getInt("credit"));
				utilisateurCnx.setAdministrateur(rs.getBoolean("administration"));
				
			}
		} catch (SQLException e) {
			//propager une exception personnalisée
			throw new Exception("Problème d'extraction des utilisateurs de la base. Cause : " + e.getMessage());
		}
			
	
		return utilisateurCnx;
	}
	
	
	public Utilisateur insert(Utilisateur utilisateur) {
		
	
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(INSERT);

			ResultSet rs;
			
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			
			pstmt.executeUpdate();
			//rs
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return utilisateur;
	}
	

}
