package controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
 * Servlet implementation class ServletEncheresEnCours
 */
@WebServlet("/EncheresEnCours")
public class ServletListeForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListeForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil/accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String encheres = null;
    	String encours = null;
    	String remportees = null;

    	String venteencours = null;
    	String nondebutees = null;
    	String terminees = null;

    	String achats = null;
    	String pseudo = null;
    	
    	HttpSession session = request.getSession();
    	Utilisateur pseudocnx = (Utilisateur) session.getAttribute("utilisateurCnx");
    	pseudo = pseudocnx.getPseudo();
    	
    	String parametre = String.valueOf(request. getParameter("type-encheres"));
    	String choixE = String.valueOf(request. getParameter ("encheres"));
    	String choixV = String.valueOf(request. getParameter ("ventes"));


    	ArticleVenduManager mgr = new ArticleVenduManager();
    	List<ArticleVendu>  listeE = null;

    	if (parametre.equals(achats)) {
    		if(choixE.equals(encheres))
    			try {
    				listeE = mgr.SelectOpen();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}

    		if(choixE.equals(encours))
    			try {
    				listeE = mgr.Select_By_Pseudo(pseudo);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}

    		if(choixE.equals(remportees))
    			try {
    				listeE = mgr.Select_Win(pseudo);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    	}

    	else {
    		if(choixV.equals(venteencours))
    			try {
    				listeE = mgr.SelectMyEncours(pseudo);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}

    		if(choixV.equals(nondebutees))
    			try {
    				listeE = mgr.SelectNoBegin(pseudo);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}

    		if(choixV.equals(terminees))
    			try {
    				listeE = mgr.SelectFinish(pseudo);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    	}
    	request.setAttribute("listeArticlesVendus", listeE);

    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil/accueil.jsp");
		rd.forward(request, response);
			
		
	}

}
