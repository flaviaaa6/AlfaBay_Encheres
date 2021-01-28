package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Utilisateur;
import exceptions.BuisnessException;

public class UtilisateurDAO {

	private static final String SELECT = "SELECT * FROM UTILISATEURS WHERE  pseudo = ? AND mot_de_passe = ? ";
	private static final String INSERT = " INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe)" +
			" VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String SELECTBYID = "SELECT * UTILISATEURS WHERE no_utilisateur = ? "; 

	private static final String UPDATE = "UPDATE UTILISATEURS SET "
			+ " pseudo = ?, "
			+ " nom = ?, "
			+ " prenom = ?, "
			+ "email = ?, "
			+ " telephone = ?, "
			+ "rue = ?, "
			+ " code_postal = ?, "
			+ " ville = ?, "
			+ " mot_de_passe = ? " 
			+ " WHERE no_utilisateur = ? ";

	private static final String DELETE = "DELETE FROM UTILISATEURS where no_utilisateur = ?";

	private static final String SELECTBYPSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ?"; 


	/* Implémentation des méthodes définies dans l'UtilisateurManager */
	
	Utilisateur utilisateurCnx;
	PreparedStatement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	Connection cnx = null;
	Utilisateur utilisateur; 


	public Utilisateur select(Utilisateur u) throws Exception {


		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(SELECT);

			rqt.setString(1, u.getPseudo());
			rqt.setString(2, u.getMotDePasse());


			ResultSet rs = rqt.executeQuery();
			if(rs.next())
			{

				utilisateurCnx=new Utilisateur();
				utilisateurCnx.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateurCnx.setPseudo(rs.getString("pseudo"));
				utilisateurCnx.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateurCnx.setNom(rs.getString("nom"));
				utilisateurCnx.setPrenom(rs.getString("prenom"));
				utilisateurCnx.setEmail(rs.getString("email"));
				utilisateurCnx.setTelephone(rs.getString("telephone"));
				utilisateurCnx.setRue(rs.getString("rue"));
				utilisateurCnx.setCodePostal(rs.getString("code_postal"));
				utilisateurCnx.setVille(rs.getString("ville"));
				utilisateurCnx.setCredit(rs.getInt("credit"));
				utilisateurCnx.setAdministrateur(rs.getBoolean("administrateur"));

			}
		} catch (SQLException e) {
			//propager une exception personnalisée
			throw new Exception("Problème d'extraction des utilisateurs de la base. Cause : " + e.getMessage());
		}

		return utilisateurCnx;
	}

	public Utilisateur insert(Utilisateur utilisateur) throws Exception {

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());

			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();

			if(rs.next()){
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}

		} catch (SQLException e) {
			BuisnessException be = new BuisnessException();
			be.ajouterErreur("Problème d'insertion de l'utilisateur dans la base. Cause :" + e.getMessage());
			throw e;
			//	}			throw new Exception("Problème d'insertion de l'utilisateur dans la base. Cause : " + e.getMessage());
		}

		return utilisateur;
	}

	public Utilisateur selectById(int id) {

		try {

			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(SELECTBYID);

			// binder id
			stmt.setInt(1, id); 
			rs = stmt.executeQuery();
			// On enleve le resultat de la pile
			rs.next();

			utilisateur = map(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return utilisateur;
	}


	public Utilisateur trouver( String pseudo ) { 

		try {
			// Récupération d'une connexion 
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(SELECTBYPSEUDO);
			rs = stmt.executeQuery(); 
			// Parcours de la ligne de données de l'éventuel ResulSet retourné 
			if ( rs.next() ) { 
				utilisateur = map(rs); 
			} 
		} catch ( SQLException e ) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateur;
	}

	public Utilisateur map( ResultSet rs ) throws SQLException { 

		utilisateur = new Utilisateur(); 

		utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));
		utilisateur.setEmail(rs.getString("email"));
		utilisateur.setTelephone(rs.getString("telephone"));
		utilisateur.setRue(rs.getString("rue"));
		utilisateur.setCodePostal(rs.getString("code_postal"));
		utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
		utilisateur.setCredit(rs.getInt("credit"));
		utilisateur.setAdministrateur(rs.getBoolean("administrateur"));

		return utilisateur; 
	}

	public void update(Utilisateur utilisateur) throws SQLException {


		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement stmtUpdate = cnx.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);
			//Valoriser les paramètres Binder

			stmtUpdate.setString(1, utilisateur.getPseudo());
			stmtUpdate.setString(2, utilisateur.getNom());
			stmtUpdate.setString(3, utilisateur.getPrenom());
			stmtUpdate.setString(4, utilisateur.getEmail());
			stmtUpdate.setString(5, utilisateur.getTelephone());
			stmtUpdate.setString(6, utilisateur.getRue());
			stmtUpdate.setString(7, utilisateur.getCodePostal());
			stmtUpdate.setString(8, utilisateur.getVille());
			stmtUpdate.setString(9, utilisateur.getMotDePasse());
			stmtUpdate.setInt(10, utilisateur.getNoUtilisateur());
			stmtUpdate.executeUpdate();

			stmtUpdate.executeUpdate();

		} catch (SQLException e) {
			BuisnessException be = new BuisnessException();
			be.ajouterErreur("Problème de modification du profil de l'utilisateur dans la base. Cause :" + e.getMessage());
			throw e;
		}
	}
	
	public Utilisateur autreUtilisateur(String pseudo) throws BuisnessException { 

		Utilisateur autreUtilisateur = null;
		Connection cnx = null;
		PreparedStatement pstmt;
		try {
			// Récupération d'une connexion 
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECTBYPSEUDO);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery(); 
			// Parcours de la ligne de données de l'éventuel ResulSet retourné 
			if ( rs.next() ) { 
				autreUtilisateur = new Utilisateur(); 

				
				autreUtilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				autreUtilisateur.setPseudo(rs.getString("pseudo"));
				autreUtilisateur.setNom(rs.getString("nom"));
				autreUtilisateur.setPrenom(rs.getString("prenom"));
				autreUtilisateur.setEmail(rs.getString("email"));
				autreUtilisateur.setTelephone(rs.getString("telephone"));
				autreUtilisateur.setRue(rs.getString("rue"));
				autreUtilisateur.setCodePostal(rs.getString("code_postal"));
				autreUtilisateur.setVille(rs.getString("ville"));
				
			} 
		} catch ( SQLException e ) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			BuisnessException be = new BuisnessException();
			be.ajouterErreur("Pas d'utilisateur dans la base  à ce pseudo. Cause :" + e.getMessage());
			throw be;
		}
		return autreUtilisateur;
	}


	public void deleteUtilisateur(int id) throws BuisnessException {

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BuisnessException be = new BuisnessException();
			be.ajouterErreur("Problème de suppression de l'utilisateur dans la base. Cause :" + e.getMessage());
			throw be;
		}

	}

}
