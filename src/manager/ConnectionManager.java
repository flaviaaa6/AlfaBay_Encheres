package manager;
import bo.Utilisateur;
import dal.UtilisateurDAO;
import exceptions.BuisnessException;


public class ConnectionManager {

	UtilisateurDAO dao = new UtilisateurDAO();
	Utilisateur utilisateurCnx = new Utilisateur();
	
	public Utilisateur verifier(Utilisateur utilisateur) throws Exception {
	
		utilisateurCnx = dao.select(utilisateur);

	return utilisateurCnx;
		
	}
	
	public Utilisateur inscrire(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, String confirmMotDePasse) throws BuisnessException {
		
		Utilisateur utilisateurInsere = null;
		BuisnessException exception = new BuisnessException();
		Utilisateur nouvelUtilisateur;
		
		try {
			validerCodePostal(codePostal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			exception.ajouterErreur(e.getMessage());
		}
		try {
			validerTelephone(codePostal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			exception.ajouterErreur(e.getMessage());
		}
		try {
			validerMotdePasse(motDePasse, confirmMotDePasse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			exception.ajouterErreur(e.getMessage());
		}
		try {
			validerPseudo(pseudo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			exception.ajouterErreur(e.getMessage());
		}
		try {
			validerEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			exception.ajouterErreur(e.getMessage());
		}
		//si les données sont validées
		if (!exception.hasErreurs()) {
			nouvelUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
			try {
				utilisateurInsere = dao.insert(nouvelUtilisateur);
			} catch (Exception e) {
				exception.ajouterErreur(e.getMessage());
			} 
			
		}
				
		//si des erreurs ont été détectées
		if (exception.hasErreurs()) {
			//propager la BuisnessException à la servlet
			throw exception;
		}
		return utilisateurInsere;
	}
	

	private void validerCodePostal(String codePostal) throws Exception {
		if (codePostal == null || codePostal.trim().length() != 5) {
			
			Exception e = new Exception("Le code postal est obligatoire et doit contenir 5 caracteres !");
			throw e;
		}
	}
	
	private void validerTelephone(String telephone) throws Exception {
		if (telephone.trim().length() != 10) {
			
			Exception e = new Exception("Le telephone doit contenir 10 caracteres !");
			throw e;
		}
	}
	
	private void validerMotdePasse(String motdePasse, String confirmMotDePasse ) throws Exception {
		if (motdePasse == null || motdePasse.trim().length()<8 || confirmMotDePasse == null || confirmMotDePasse.trim().length() < 8) {
			
			Exception e = new Exception("Le mot de passe est obligatoire et doit contenir au minimum 8 caracteres !");
			throw e;
			}
		if (!motdePasse.equals(confirmMotDePasse)) {
			Exception e = new Exception("Les deux mots de passe doivent etre identique !");
			throw e;
			}
	}	
	
	private void validerPseudo(String Pseudo) throws Exception {
		if (Pseudo == null || Pseudo.trim().length() == 0) {
			
			Exception e = new Exception("Le pseudo est obligatoire!");
			throw e;
		}
	}
	private void validerEmail(String email) throws Exception {
		if (email == null || email.trim().length() == 0 || !email.contains("@")) {
			
			Exception e = new Exception("Un email valide est obligatoire!");
			throw e;
		}
	}
}
