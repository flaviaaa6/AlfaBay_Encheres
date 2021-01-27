package manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Enchere;
import dal.EnchereDAO;

public class EnchereManager {
	
	
	EnchereDAO dao = new EnchereDAO();
	
	public void insererEnchere(Enchere enchere) throws SQLException {
		dao.insert(enchere);
		
	}

	public List<Enchere> SelectAll() throws SQLException{
		List <Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere=dao.SelectAll();
		return listeEnchere;
		
	}
	
}
