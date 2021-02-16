package ma.ensias.dao;

import java.sql.*;
import static ma.ensias.dao.DAOUtilitaire.*;
import ma.ensias.beans.Comment;

public class CommentDaoImpl implements CommentDao{

	private DAOFactory daoFactory;
	
	private static final String SQL_SELECT_BY_ID = "SELECT id, text, likes , dislikes, date, user FROM comment WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO comment (text,likes,dislikes,date,user) VALUES (?,?,?,NOW(),?)";
	
	CommentDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	
	private static Comment map(ResultSet resultset) throws SQLException {
		Comment comment = new Comment();
		comment.setId(resultset.getInt("id"));
		comment.setText(resultset.getString("text"));
		comment.setLikes(resultset.getInt("likes"));
		comment.setDislikes(resultset.getInt("dislikes"));
		comment.setDate(resultset.getDate("date"));
		// TO DO : add setUser
		return comment;
		
	}
	@Override
	public void create(Comment comment) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, comment.getText(), comment.getLikes(), comment.getDislikes(), comment.getUser().getId() );
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
	            throw new DAOException( "comment creation error, no line was inserted" );
	        }
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	            comment.setId( valeursAutoGenerees.getInt( 1 ) );
	        } else {
	            throw new DAOException( "comment creation error in DB, no auto generated ID was returned" );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    }
		
	}

	@Override
	public Comment find(int id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Comment comment = null;
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            comment = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }		

		return comment;
	}

	@Override
	public void update(Object... fields) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Comment comment) {
		// TODO Auto-generated method stub
		
	}

}
