package controleur;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.ArticleVenduManager;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/vente/add")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNouvelleVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ventearticles/nouvellevente.jsp");
		rd.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
		// file photo = request.getParameter("photo");
		String prixDepart = request.getParameter("prixDepart");
		String dateDebutEnchereStr = request.getParameter("dateDebutEnchere");
		String dateFinEnchereStr = request.getParameter("dateFinEnchere");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		
		//convertir les données :
		
		//les dates
		LocalDateTime dateDebut = null; 
		LocalDateTime dateFin= null;
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm");
			dateDebut = LocalDateTime.parse(request.getParameter("dateDebutEnchereStr"),dtf);
		}
		catch(DateTimeParseException e)
		{
			e.printStackTrace();
		}
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm");
			dateFin = LocalDateTime.parse(request.getParameter("dateFinEnchereStr"),dtf);
		}
		catch(DateTimeParseException e)
		{
			e.printStackTrace();
		}
		
		//les entiers
		
		int prixInitial = Integer.parseInt(prixDepart);
				
				
		//appel au manager, envoi des données pour créer un nouvel article
		ArticleVenduManager mgr = new ArticleVenduManager();
		mgr.insereArticle(nom,description,categorie,dateDebut,dateFin,prixInitial);
		
		
	}

}
