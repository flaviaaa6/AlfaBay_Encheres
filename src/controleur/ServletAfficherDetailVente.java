package controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.ArticleVendu;
import bo.Enchere;
import bo.Utilisateur;
import exceptions.BuisnessException;
import manager.ArticleVenduManager;
import manager.EnchereManager;

/**
 * Servlet implementation class ServletAfficherDetailVente
 */
@WebServlet("/detail/vente")
public class ServletAfficherDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleVendu articleDetail = new ArticleVendu();
	ArticleVenduManager managerAV = new ArticleVenduManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAfficherDetailVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		//recuperation du parametre no_article sur la jsp accueil; name = "id"
		
		String nomArticle = request.getParameter("nomArticle");
		
		
		
		
		
		//appel au manager pour la fonction selectByID
		
		
		try {
			articleDetail = managerAV.selectByName(nomArticle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//envoi de l'attribut article pour afficher la jsp
		request.setAttribute("article", articleDetail);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ventearticles/detailsvente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String propositionStr = request.getParameter("proposition");	
		int proposition = Integer.parseInt(propositionStr);
		String message = null;
		
		// recuperation de l'utilisateur
		
		HttpSession session = request.getSession();
		Utilisateur utilisateurCnx = (Utilisateur) session.getAttribute("utilisateurCnx");
				
		//création de l'enchere a inséré : 
		Enchere enchere = new Enchere();
		enchere.setMontantEnchere(proposition);
		
		
		enchere.setUtilisateur(utilisateurCnx);
		enchere.setDateEnchere(LocalDateTime.now());
		enchere.setArticleVendu(articleDetail);
		
		EnchereManager manager = new EnchereManager();
		
			try {
				manager.insererEnchere(enchere, articleDetail.getEnchere().getMontantEnchere());
			} catch (BuisnessException | SQLException e) {
				// TODO Auto-generated catch block
			
			message = "erreur, l'enchère n'a pas été validée";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			message = "enchère enregistrée";

			try {
				articleDetail = managerAV.selectByName(articleDetail.getNomArticle());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		request.setAttribute("message", message);		
		request.setAttribute("article", articleDetail);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ventearticles/detailsvente.jsp");
		rd.forward(request, response);
	}

}
