package controleur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Utilisateur;
import manager.UtilisateurManager;

/**
 * Servlet implementation class ServletConnection
 */
@WebServlet(name = "ServletConnecter", urlPatterns = { "/connect" })
public class ServletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connection/seconnecter.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
	
		String message = null;
		
		Utilisateur utilisateur = new Utilisateur(pseudo, password);
		
		Utilisateur utilisateurCnx = null;
		
		UtilisateurManager mgr = new UtilisateurManager();
		try {
			utilisateurCnx = mgr.verifier(utilisateur);
		} catch (Exception e) {
			// TODO Auto-generated catch block (Gérer l'exception)
			e.printStackTrace();
		}
		
		if (utilisateurCnx != null) {
		
			HttpSession session = request.getSession();
			
			session.setAttribute("utilisateurCnx", utilisateurCnx);
		}else {
			message = "pseudo ou mot de passe non valide";
			request.setAttribute("message", message);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/accueil");
		rd.forward(request, response);
	}
}
