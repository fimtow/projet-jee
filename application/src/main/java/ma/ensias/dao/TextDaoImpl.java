package ma.ensias.dao;

import static ma.ensias.dao.DAOusef.*;

import java.sql.*;

import ma.ensias.beans.Post;
import ma.ensias.beans.Text;

public class TextDaoImpl implements TextDao{

	private DAOFactory daoFactory;
	private static final String SQL_SELECT_BY_ID = "SELECT id, text, post FROM text WHERE id = ?";
	private static final String SQL_SELECT_BY_POST = "SELECT id, text, post FROM text WHERE post = ?";
	private static final String SQL_INSERT = "INSERT INTO text (text,post) VALUES (?,?)";
	
	TextDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	
	private Text map(ResultSet resultset) throws SQLException {
		Text text = new Text();
		text.setId(resultset.getInt("id"));
		text.setText(resultset.getString("text"));
		text.setPost(daoFactory.getPostDao().find(resultset.getInt("post")));
		return text;
	}
	@Override
	public void create(Text text) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initQueryPrepared( connexion, SQL_INSERT, true, text.getText(), text.getPost().getId() );
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
	            throw new DAOException( "text creation error, no line was inserted" );
	        }
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	            text.setId( valeursAutoGenerees.getInt( 1 ) );
	        } else {
	            throw new DAOException( "text creation error in DB, no auto generated ID was returned" );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        closeConnectionItems( valeursAutoGenerees, preparedStatement, connexion );
	    }
		
	}

	@Override
	public Text find(Post post) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Text text = null;
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initQueryPrepared( connexion, SQL_SELECT_BY_POST, false, post.getId() );
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            text = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        closeConnectionItems( resultSet, preparedStatement, connexion );
	    }		

		return text;
	}

	@Override
	public void update(Object... fields) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Text text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Text find(int id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Text text = null;
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initQueryPrepared( connexion, SQL_SELECT_BY_ID, false, id );
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            text = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        closeConnectionItems( resultSet, preparedStatement, connexion );
	    }		

		return text;
	}

}
