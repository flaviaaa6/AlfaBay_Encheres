package controleur;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Utilisateur;
import exceptions.BuisnessException;
import manager.UtilisateurManager;

/**
 * Servlet implementation class ServletAfficherProfil
 */
@WebServlet("/profil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifierProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profil/modifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = null;
		Utilisateur utilisateurCnx = null;
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("lastname");
		String prenom = request.getParameter("firstname");
		String email = request.getParameter("email");
		String telephone = request.getParameter("phone");
		String rue = request.getParameter("street");
		String codePostal = request.getParameter("zipcode");
		String ville = request.getParameter("city");
		String motDePasse = request.getParameter("password");
		String confirmMotDePasse = request.getParameter("confirm_password");
		
		UtilisateurManager mgr = new UtilisateurManager();
		
		try {
			utilisateurCnx = mgr.update(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, confirmMotDePasse);
		} catch (BuisnessException e) {
			request.setAttribute("erreurs", e.getListeMessagesErreur());
			message = "Erreur, la modification n'a pas été effectuée !";
		}
		
		System.out.println(utilisateurCnx);
		
		if (utilisateurCnx != null) {
			message = "La modification est terminée avec succès!";
			HttpSession session = request.getSession();
			session.setAttribute("utilisateurCnx", utilisateurCnx);
		}
		
		request.setAttribute("message", message);
		
	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profil/afficherProfil.jsp");
	rd.forward(request, response);
	}

}
