package manager;

import java.sql.SQLException;

import bo.Encheres;
import dal.EnchereDAO;

public class EnchereManager {
	
	
	EnchereDAO dao = new EnchereDAO();
	
	public void insererEnchere(Encheres enchere) throws SQLException {
		dao.insert(enchere);
		
	}

}
