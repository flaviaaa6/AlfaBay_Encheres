package controleur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.ArticleVendu;
import bo.Utilisateur;
import manager.ArticleVenduManager;

/**
 * Servlet implementation class ServletAfficherDetailVente
 */
@WebServlet("/detail/vente")
public class ServletAfficherDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		ArticleVendu articleDetail = new ArticleVendu();
		int idArticle = Integer.parseInt(request.getParameter("id"));
		articleDetail.setNoArticle(idArticle);
		
		
		//appel au manager pour la fonction selectByID
		
		ArticleVenduManager manager = new ArticleVenduManager();
		try {
			articleDetail = manager.selectById(idArticle);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
