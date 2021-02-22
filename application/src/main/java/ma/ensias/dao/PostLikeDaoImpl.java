package ma.ensias.dao;

import static ma.ensias.dao.DAOusef.closeConnectionItems;
import static ma.ensias.dao.DAOusef.initQueryPrepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ma.ensias.beans.Post;
import ma.ensias.beans.User;

public class PostLikeDaoImpl implements PostLikeDao{
	public static final int NOTSET = 0;
	public static final int NONE = 1;
	public static final int DISLIKE = 2;
	public static final int LIKE = 3;
	private DAOFactory daoFactory;
	private static final String SQL_SELECT = "SELECT islike FROM postlike WHERE postid = ? and userid = ?";
	private static final String SQL_UPDATE = "UPDATE postlike SET islike = ? where postid = ? and userid = ?";
	private static final String SQL_INSERT = "INSERT INTO postlike (postid,userid,islike) VALUES (?,?,?)";
	
	PostLikeDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	@Override
	public void create(Post post, User user, int status) {
	    boolean isLike = false;
	    if(status == LIKE)
	    	isLike = true;
	    else if(status == NONE)
	    	return;
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initQueryPrepared( connexion, SQL_INSERT, false, post.getId(), user.getId(), isLike );
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
	            throw new DAOException( "Post like creation error, no line was inserted" );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        closeConnectionItems( preparedStatement, connexion );
	    }
	}

	@Override
	public int find(Post post, User user) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int status = NOTSET;
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initQueryPrepared( connexion, SQL_SELECT, false, post.getId(), user.getId() );
	        resultSet = preparedStatement.executeQuery();
	        if( resultSet.next() ) {
	            status = map(resultSet);
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        closeConnectionItems( resultSet, preparedStatement, connexion );
	    }		

		return status;
	}

	private int map(ResultSet resultSet) throws SQLException{
		int status = NONE;
		if(resultSet.getBoolean("islike") && resultSet.wasNull() == false)
			status = LIKE;
		else if(resultSet.getBoolean("islike") && resultSet.wasNull() == false)
			status = DISLIKE;
		return status;
	}
	@Override
	public void update(Post post, User user, int status) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        connexion = daoFactory.getConnection();
	        if(status == LIKE)
	        	preparedStatement = initQueryPrepared( connexion, SQL_UPDATE, false, post.getId(), user.getId(), true );
	        else if(status == DISLIKE)
	        	preparedStatement = initQueryPrepared( connexion, SQL_UPDATE, false, post.getId(), user.getId(), false );
	        else if(status == NONE)
	        	preparedStatement = initQueryPrepared( connexion, SQL_UPDATE, false, post.getId(), user.getId(), null );
	        else
	        	return;
	        int statut = preparedStatement.executeUpdate();
	        if ( statut == 0 ) {
	            throw new DAOException( "Post like creation error, no line was inserted" );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        closeConnectionItems( preparedStatement, connexion );
	    }
	}

}
