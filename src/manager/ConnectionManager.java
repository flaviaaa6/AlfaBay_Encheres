package manager;

import bo.Utilisateur;
import dal.UtilisateurDAO;

public class ConnectionManager {

	Utilisateur utilisateurCnx = new Utilisateur();
	
	public Utilisateur verifier(Utilisateur utilisateur) throws Exception {
		
		UtilisateurDAO dao = new UtilisateurDAO();
		utilisateurCnx = dao.select(utilisateur);

	return utilisateurCnx;
		
	}

}
