package manager;

import java.util.ArrayList;
import java.util.List;

import bo.Categorie;
import dal.CategorieDAO;
import exceptions.BuisnessException;

public class CategorieManager {
	
	private List<Categorie> categories = new ArrayList<Categorie>();
	CategorieDAO dao = new CategorieDAO();
	BuisnessException exception = new BuisnessException();
	
	
		public List<Categorie> listerCategorie(){
			try {
				categories = dao.selectAll();
			} catch (Exception e) {
				exception.ajouterErreur(e.getMessage());
			} 
			return categories;
			
			
		}
		
		
	
	
}
