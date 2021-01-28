package controleur;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
 * Servlet implementation class ServletSupprimerCompte
 */
@WebServlet("/suppression")
public class ServletSupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSupprimerCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Supprimer la session de l'utilisateur
		HttpSession session = request.getSession();

        Utilisateur utilisateurCnx = (Utilisateur) session.getAttribute("utilisateurCnx");
		int numUtilisateur = utilisateurCnx.getNoUtilisateur();
		
		UtilisateurManager mgr = new UtilisateurManager();
		
		String message = null;
		session.invalidate();
		try {
			mgr.deleteUtilisateur(numUtilisateur);
		} catch (BuisnessException e) {
			request.setAttribute("erreurs", e.getListeMessagesErreur());
			message = "Erreur, l'utilisateur n'a pas été supprimé !";
		}
		
	
		message = "La suppression est terminée avec succès";
		
		
		
		request.setAttribute("message", message);
		

		//request.setAttribute("utilisateur", numUtilisateur);
		
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profil/modifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
