package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Categorie;

import bo.Utilisateur;

public class CategorieDAO {
	
	private final String SELECTALL = "select * from CATEGORIES";
	
	
	public List<Categorie> selectAll() throws Exception {
		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			Statement rqt = cnx.createStatement();
			ResultSet rs = rqt.executeQuery(SELECTALL);
			while(rs.next())
			{
				Categorie categ;
				categ=new Categorie();
				categ.setNoCategorie(rs.getInt("no_categorie"));
				categ.setLibelle(rs.getString("libelle"));
				
				categories.add(categ);
			}
		} catch (SQLException e) {
			//propager une exception personnalisée
			throw new Exception("Problème d'extraction des categories de la base. Cause : " + e.getMessage());
		}
		
	return categories;
		
	}

}
