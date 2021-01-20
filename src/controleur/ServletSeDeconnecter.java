package controleur;

import java.io.IOException;

import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;






/**
 * Servlet implementation class ServletSeDeconnecter
 */
@WebServlet("/deconnect")
public class ServletSeDeconnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSeDeconnecter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Invalider la session de l'utilisateur
        //Context context = context).getCurrentInstance();
       // HttpSession session = (HttpSession) context).getExternalContext().getSession(false);
      //  session.invalidate();
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		
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
