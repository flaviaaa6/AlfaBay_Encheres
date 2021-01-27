package controleur;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Utilisateur;
import exceptions.BuisnessException;
import manager.UtilisateurManager;

/**
 * Servlet implementation class ServletAutreProfil
 */
@WebServlet("/autreutilisateur")
public class ServletAfficherAutreProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAfficherAutreProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String pseudo = request.getParameter("pseudo");
		Utilisateur utilisateur = null;
		UtilisateurManager mgr = new UtilisateurManager();

		try {
			utilisateur = mgr.afficherAutreProfil(pseudo);
		} catch (BuisnessException e) {

			e.printStackTrace();
		}

		request.setAttribute("PseudoUser", utilisateur);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profil/afficherProfil.jsp");
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
