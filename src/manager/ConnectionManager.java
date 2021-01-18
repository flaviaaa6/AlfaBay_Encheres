package manager;

import java.util.List;

import bo.Utilisateur;
import dal.UtilisateurDAO;

public class ConnectionManager {
	
	private List<Utilisateur> utilisateurs ;
	private boolean trouve = false;
	
	public boolean verifier(Utilisateur utilisateur) throws Exception {
		
		UtilisateurDAO dao = new UtilisateurDAO();
		utilisateurs = dao.select();
		for (Utilisateur u : utilisateurs) {
			if(u.getPseudo() == utilisateur.getPseudo()) {
				if(u.getMotDePasse() == utilisateur.getMotDePasse()) {
					trouve = true;
				}
			}
			
		}
			
		
		
	return trouve;
		
	}
	
	

}
