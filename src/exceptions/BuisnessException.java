package exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public class BuisnessException extends Exception {
	private List<String> listeMessagesErreur;
	
	public BuisnessException() {
		super();
		listeMessagesErreur = new ArrayList<String>();
	}
	
	public void ajouterErreur(String message) {
		//TODO gérer les doublons dans la liste
		if (!listeMessagesErreur.contains(message)) {
			listeMessagesErreur.add(message);
		}
	}
	
	public boolean hasErreurs() {
		return listeMessagesErreur.size() > 0;
	}

	/**
	 * @return the listeMessagesErreur
	 */
	public List<String> getListeMessagesErreur() {
		return listeMessagesErreur;
	}
	
	
}
