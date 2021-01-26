package controleur;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.ArticleVendu;
import exceptions.BuisnessException;
import manager.ArticleVenduManager;

/**
 * Servlet implementation class ServletAccueillir
 */
@WebServlet("/accueil")
public class ServletAccueillir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccueillir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleVenduManager mgr = new ArticleVenduManager();
		List<ArticleVendu> listeAV = null;
		
			try { 
				
				listeAV  = mgr.select();
				
			} catch (BuisnessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticlesVendus", listeAV );
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil/accueil.jsp");
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
