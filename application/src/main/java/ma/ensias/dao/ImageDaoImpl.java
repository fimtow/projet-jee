package ma.ensias.dao;

import static ma.ensias.dao.DAOusef.*;

import java.sql.*;

import ma.ensias.beans.Image;
import ma.ensias.beans.Post;

public class ImageDaoImpl implements ImageDao{

	private DAOFactory daoFactory;
	
	private static final String SQL_SELECT_BY_ID = "SELECT id, url, post FROM image WHERE id = ?";
	private static final String SQL_SELECT_BY_POST = "SELECT id, url, post FROM image WHERE post = ?";
	private static final String SQL_INSERT = "INSERT INTO image (url,post) VALUES (?,?)";
	
	ImageDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	
	private Image map(ResultSet resultset) throws SQLException {
		Image image = new Image();
		image.setId(resultset.getInt("id"));
		image.setUrl(resultset.getString("url"));
		image.setPost(daoFactory.getPostDao().find(resultset.getInt("post")));
		return image;
		
	}
	@Override
	public void create(Image image) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initQueryPrepared( connexion, SQL_INSERT, true, image.getUrl(), image.getPost().getId() );
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
	            throw new DAOException( "image creation error, no line was inserted" );
	        }
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	            image.setId( valeursAutoGenerees.getInt( 1 ) );
	        } else {
	            throw new DAOException( "image creation error in DB, no auto generated ID was returned" );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        closeConnectionItems( valeursAutoGenerees, preparedStatement, connexion );
	    }
		
	}

	@Override
	public Image find(Post post) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Image image = null;
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initQueryPrepared( connexion, SQL_SELECT_BY_POST, false, post.getId() );
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            image = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        closeConnectionItems( resultSet, preparedStatement, connexion );
	    }		

		return image;
	}

	@Override
	public void update(Object... fields) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Image image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image find(int id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Image image = null;
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initQueryPrepared( connexion, SQL_SELECT_BY_ID, false, id );
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            image = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        closeConnectionItems( resultSet, preparedStatement, connexion );
	    }		

		return image;
	}

}
