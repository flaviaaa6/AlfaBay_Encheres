package controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Retrait;
import bo.Utilisateur;
import exceptions.BuisnessException;
import manager.ArticleVenduManager;
import manager.CategorieManager;
import manager.RetraitManager;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/vente/add")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private final String DATEFORMAT = "yyyy-MM-dd'T'HH:mm";
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

		CategorieManager mgr = new CategorieManager();
		List<Categorie>listeCategories = mgr.listerCategorie();
		
		request.setAttribute("categories", listeCategories);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ventearticles/nouvellevente.jsp");
		rd.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("article");
		String description = request.getParameter("description");
		String[] categorie = request.getParameter("categorie").split(";");
		// file photo = request.getParameter("photo");
		String prixDepart = request.getParameter("prixDepart");
		String dateDebutEnchereStr = request.getParameter("dateDebutEnchere");
		String dateFinEnchereStr = request.getParameter("dateFinEnchere");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		ArticleVendu articleAVendre = null;
		String message = null;
		BuisnessException be = new BuisnessException();
		
		//convertir les données :
		
		//les dates
		LocalDateTime dateDebut = null; 
		LocalDateTime dateFin= null;
		
		try
	{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATEFORMAT);
			dateDebut = LocalDateTime.parse(dateDebutEnchereStr, dtf);
			
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyy");
			System.out.println("TOTO !!!!!!!!" + dateDebut.format(formatter2));

			
		}
		catch(DateTimeParseException e)
		{
			e.printStackTrace();
		}
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATEFORMAT);
			dateFin = LocalDateTime.parse(dateFinEnchereStr,dtf);
		}
		catch(DateTimeParseException e)
		{
			e.printStackTrace();
		}
		
		//les entiers
		
		int prixInitial = Integer.parseInt(prixDepart);
				
		
		//création de la categorie
		
		Categorie categ = new Categorie();
		int idCateg = Integer.parseInt(categorie[0]);
		categ.setNoCategorie(idCateg);
		categ.setLibelle(categorie[1]);
		
		// recuperation de l'utilisateur
		
		HttpSession session = request.getSession();
		Utilisateur utilisateurCnx = (Utilisateur) session.getAttribute("utilisateurCnx");
		
		
				
		//appel au manager, envoi des données pour créer un nouvel article
		
		
		ArticleVenduManager mgr = new ArticleVenduManager();
		
			try {
				articleAVendre = mgr.insereArticle(nom,description,dateDebut,dateFin,prixInitial, categ, utilisateurCnx);
			} catch (SQLException e) {
				be = new BuisnessException();
				request.setAttribute("erreurs", be.getListeMessagesErreur());
				message = "Erreur, l'utilisateur n'a pas été ajouté !";
				
			}
		
			if (articleAVendre != null) {
				message = "article enregistré";
				
				
			}
			
			//creation du retrait
			
			Retrait retrait = new Retrait();
			retrait.setId_article(articleAVendre.getNoArticle());
			retrait.setRue(rue);
			retrait.setCodePostal(codePostal);
			retrait.setVille(ville);
			
			// envoi du retrait en base de donnée
			
			RetraitManager retraitmgr = new RetraitManager();
			try {
				retraitmgr.insereretrait(retrait);
			} catch (SQLException e) {
				be = new BuisnessException();
				request.setAttribute("erreurs", be.getListeMessagesErreur());
				message = "Erreur, le retrait n'a pas été ajouté !";
			}
			
			request.setAttribute("message", message);
			
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ventearticles/nouvellevente.jsp");
		rd.forward(request, response);

		
	}

}
