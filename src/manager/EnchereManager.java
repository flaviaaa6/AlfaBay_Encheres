package manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Enchere;
import bo.Utilisateur;
import dal.EnchereDAO;
import exceptions.BuisnessException;

public class EnchereManager {
	
	
	EnchereDAO dao = new EnchereDAO();
	BuisnessException exception = new BuisnessException();
	
	public void insererEnchere(Enchere enchere, int ancienMontant, Utilisateur util) throws BuisnessException {
		try {
			validerProposition(enchere.getMontantEnchere(), ancienMontant );
		} catch (Exception e) {
			exception.ajouterErreur(e.getMessage());
		} 
		
		try {
			validerCredit(util, enchere.getMontantEnchere());
		} catch (Exception e) {
			exception.ajouterErreur(e.getMessage());
		} 
		
		if (!exception.hasErreurs()) {
			
			
			try {
				dao.insert(enchere);
			} catch (Exception e) {
				exception.ajouterErreur(e.getMessage());
			}	
		}
				
		//si des erreurs ont été détectées
		if (exception.hasErreurs()) {
			//propager la BuisnessException à la servlet
			throw exception;
		}
		
		
		
		
	}

	private void validerCredit(Utilisateur util, int montantEnchere) throws Exception {
		int credit = util.getCredit();
		
		if(credit< montantEnchere) {
			Exception e = new Exception("Le crédit n'est pas suffisant!");
			throw e;
		}
		
	}

	public List<Enchere> SelectAll() throws SQLException{
		List <Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere=dao.SelectAll();
		return listeEnchere;
		
	}
	
	private void validerProposition(int nouvProposition, int enchere) throws Exception {
		if (nouvProposition == 0 || nouvProposition <= enchere) {
			
			Exception e = new Exception("La proposition est obligatoire est doit être supérieur au montant de la dernière enchère!");
			throw e;
		}
	}
}
	
