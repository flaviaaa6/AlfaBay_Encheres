package manager;

import bo.Utilisateur;
import dal.UtilisateurDAO;

public class ConnectionManager {

	UtilisateurDAO dao = new UtilisateurDAO();
	Utilisateur utilisateurCnx = new Utilisateur();
	
	public Utilisateur verifier(Utilisateur utilisateur) throws Exception {
	
		utilisateurCnx = dao.select(utilisateur);

	return utilisateurCnx;
		
	}
	
	public Utilisateur inscrire(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse) {
		
		Utilisateur utilisateurInsere;
		
		Utilisateur nouvelUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		utilisateurInsere = dao.insert(nouvelUtilisateur); 
		return utilisateurInsere;
	}
	

}
