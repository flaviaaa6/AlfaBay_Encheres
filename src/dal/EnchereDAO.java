package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Enchere;
import bo.Utilisateur;
import exceptions.BuisnessException;

public class EnchereDAO {

	private static final String INSERT = " INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere"
			+ "VALUES (?,?,?,?)";
	
	private  final  static  String ENCHERESOUVERTES =	"select * from  ENCHERES";
	
	public void insert(Enchere enchere) throws SQLException {
		
		
		PreparedStatement pstmt;
		ResultSet rs;
	
		try {
			Connection cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT);
			
			pstmt.setInt(1, enchere.getNoUtilisateur());
			pstmt.setInt(2, enchere.getNoArticle());
			
			//pstmt.setLocalDateTime(3, enchere.getDateEnchere().toLocalDate(),enchere.getDateEnchere().toLocalTime());
			pstmt.setTimestamp(3, Timestamp.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontantEnchère());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			BuisnessException be = new BuisnessException();
			be.ajouterErreur("Problème d'insertion de l'enchere dans la base. Cause :" + e.getMessage());
			throw e;
	
		}
		
	}
	
	/*
	public  Enchere insert(String  dateEnchere , Enchere  enchere ) throws  SQLException {

			
			Connection cnx = null;
			PreparedStatement pstmt = null;
			
		
			try {
				cnx = ConnectionProvider.getConnection();
				pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				
				Utilisateur vendeur = new Utilisateur();
				pstmt.setInt(1, vendeur.getNoUtilisateur());
				
				ArticleVendu article = new ArticleVendu();
				pstmt.setInt(2, article.getNoArticle());
				
				pstmt.setString(3, dateEnchere);
				pstmt.setInt(4, enchere.getMontantEnchère());
	
				pstmt.executeUpdate();
			
			} catch ( SQLException e) {		
				e.printStackTrace();
			}
			try {
				if (pstmt != null ) pstmt.close();
				if (cnx != null ) cnx.close();
			} catch ( SQLException e) {
				e.printStackTrace();
			}

			return enchere;		
		} */
	
	
	public List<Enchere> SelectAll() throws SQLException {
		Enchere enchere= new Enchere();
		List <Enchere> listeEnchere =  new ArrayList <> ();
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx . createStatement ();
			rs = stmt . executeQuery (ENCHERESOUVERTES);

			while (rs . next ()) {
				Utilisateur vendeur = new Utilisateur();
				vendeur.setPseudo(rs.getString ("pseudo"));
				enchere.setUtilisateur(vendeur);
				
				ArticleVendu article = new ArticleVendu ();
				article.setNomArticle(rs.getString ("nom_article"));
				enchere.setArticleVendu(article);
				
				enchere.setDateEnchere(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()), rs.getTime("date_debut_enchere").toLocalTime()));
				enchere.setMontantEnchère(rs.getInt("montant_enchere"));;
				
				listeEnchere.add(enchere);
				
			}
		} catch ( SQLException e) {		
			e.printStackTrace();
		}
		try {
			if (stmt != null ) stmt.close();
			if (cnx != null ) cnx.close();
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		return listeEnchere;	
		
	}
	
}
