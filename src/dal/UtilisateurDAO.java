package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Utilisateur;

public class UtilisateurDAO {
	
	private static final String SELECT = "SELECT pseudo, mot_de_passe FROM UTILISATEUR";
	
	
	public List<Utilisateur> select() throws Exception {
		
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			Statement rqt = cnx.createStatement();
			ResultSet rs = rqt.executeQuery(SELECT);
			while(rs.next())
			{
				Utilisateur utilisateur;
				utilisateur=new Utilisateur();
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			//propager une exception personnalisée
			throw new Exception("Problème d'extraction des repas de la base. Cause : " + e.getMessage());
		}
			
		
		
		
		return utilisateurs;
	}
	
	
	
	

}
