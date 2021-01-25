package manager;

import java.sql.SQLException;

import bo.Retrait;
import dal.RetraitDAO;

public class RetraitManager {

	
	public void insereretrait(Retrait retrait) throws SQLException {
		RetraitDAO dao = new RetraitDAO();
		dao.insert(retrait);
		
	}
}
