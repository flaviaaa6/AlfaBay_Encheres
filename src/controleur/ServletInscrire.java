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
import exceptions.BuisnessException;
import manager.ConnectionManager;

/**
 * Servlet implementation class ServletInscrire
 */
@WebServlet("/inscription")
public class ServletInscrire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscrire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connection/register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = null;
		Utilisateur utilisateurConnecte = null;
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
		
		ConnectionManager mgr = new ConnectionManager();
		try {
			utilisateurConnecte = mgr.inscrire(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, confirmMotDePasse);
		} catch (BuisnessException e) {
			request.setAttribute("erreurs", e.getListeMessagesErreur());
			message = "Erreur, l'utilisateur n'a pas été ajouté !";
		}
		if (utilisateurConnecte != null) {
			message = "inscription terminée";
			HttpSession session = request.getSession();
			session.setAttribute("utilisateurCnx", utilisateurConnecte);
		}
		
		request.setAttribute("message", message);
		
	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connection/register.jsp");
	rd.forward(request, response);

	}
}
