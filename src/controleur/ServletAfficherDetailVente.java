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
import manager.ArticleVenduManager;
import manager.EnchereManager;

/**
 * Servlet implementation class ServletAfficherDetailVente
 */
@WebServlet("/detail/vente")
public class ServletAfficherDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleVendu articleDetail = new ArticleVendu();
       
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
		
		ArticleVenduManager manager = new ArticleVenduManager();
		try {
			articleDetail = manager.selectByName(nomArticle);
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
		
		// recuperation de l'utilisateur
		
		HttpSession session = request.getSession();
		Utilisateur utilisateurCnx = (Utilisateur) session.getAttribute("utilisateurCnx");
				
		//création de l'enchere a inséré : 
		Enchere enchere = new Enchere();
		enchere.setMontantEnchère(proposition);
		enchere.setNoUtilisateur(utilisateurCnx.getNoUtilisateur());
		enchere.setDateEnchere(LocalDateTime.now());
		enchere.setNoArticle(articleDetail.getNoArticle());
		
		EnchereManager manager = new EnchereManager();
		try {
			manager.insererEnchere(enchere);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		doGet(request, response);
	}

}
