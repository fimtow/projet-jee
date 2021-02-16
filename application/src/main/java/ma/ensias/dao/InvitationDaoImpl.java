package ma.ensias.dao;

import static ma.ensias.dao.DAOUtilitaire.fermeturesSilencieuses;
import static ma.ensias.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ma.ensias.beans.Invitation;

public class InvitationDaoImpl implements InvitationDao{

	private DAOFactory daoFactory;
	private static final String SQL_SELECT_BY_ID = "SELECT id, joined FROM content WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO content (joined) VALUES (?)";
	
	InvitationDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	
	private static Invitation map(ResultSet resultset) throws SQLException {
		Invitation invitation = new Invitation();
		invitation.setId(resultset.getInt("id"));
		invitation.setJoined(resultset.getInt("joined"));
		return invitation;
		
	}
	@Override
	public void create(Invitation invitation) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, invitation.getJoined() );
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
	            throw new DAOException( "invitation creation error, no line was inserted" );
	        }
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	            invitation.setId( valeursAutoGenerees.getInt( 1 ) );
	        } else {
	            throw new DAOException( "invitation creation error in DB, no auto generated ID was returned" );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    }
		
	}

	@Override
	public Invitation find(int id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Invitation invitation = null;
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            invitation = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }		

		return invitation;
	}

	@Override
	public void update(Object... fields) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Invitation invitation) {
		// TODO Auto-generated method stub
		
	}

}
