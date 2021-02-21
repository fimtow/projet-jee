package ma.ensias.dao;

import static ma.ensias.dao.DAOusef.closeConnectionItems;
import static ma.ensias.dao.DAOusef.initQueryPrepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ma.ensias.beans.Post;
import ma.ensias.beans.Topic;


public class PostDaoImpl implements PostDao {
	
	private static final String SQL_INSERT = "INSERT INTO post(title,likes,dislikes,date,type,topic,user) VALUES (?,?,?,?,?,?,?,?) ";
	private static final String SQL_SELECT_BY_ID = "SELECT id,title,likes,dislikes,date,type,topic,user FROM post WHERE id = ?";
	private static final String SQL_SELECT_BY_TOPIC = "SELECT id,title,likes,dislikes,date,type,topic,user FROM post WHERE topic = ?";
	//private static final String SQL_UPDATE = "UPDATE topic SET title = ?, description = ?, iconUrl = ?, coverUrl = ?  WHERE id = ?";
	
	private DAOFactory daoFactory;
	
	PostDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	
	private Post map(ResultSet resultset) throws SQLException {
		int type;
		Post post = new Post();
		post.setId(resultset.getInt("id"));
		post.setTitle(resultset.getString("title"));		
		post.setLikes(resultset.getInt("likes"));
		post.setDislikes(resultset.getInt("dislikes"));
		post.setDate(resultset.getDate("date"));
		post.setType(resultset.getInt("type"));
		type = resultset.getInt("type");
		if( type == Post.IMAGE )
			post.setContent(daoFactory.getImageDao().find(post));
		else if ( type == Post.INVITATION )
			post.setContent(daoFactory.getInvitationDao().find(post));
		else
			post.setContent(daoFactory.getTextDao().find(post));
		
		post.setUser(daoFactory.getUserDao().find(resultset.getInt("user")));
		post.setTopic(daoFactory.getTopicDao().find(resultset.getInt("topic")));
		
		return post;
	}

	@Override
	public void create(Post post) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement  preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		
		String title = post.getTitle();
		int likes = post.getLikes();
		int dislikes = post.getDislikes();
		Date date = post.getDate();
		int idContent = post.getContent().getId();
		int type = post.getType();
		int idTopic = post.getTopic().getId();
		int idUser = post.getUser().getId();
		
		
		try 
		{	
			connexion = daoFactory.getConnection();
			preparedStatement = initQueryPrepared(connexion,SQL_INSERT,true,title,title,likes,dislikes,date,idContent,type,idTopic,idUser);
			int statut = preparedStatement.executeUpdate();
			if(statut == 0)
			{
				throw new DAOException("Post creation error , no line was inserted");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if(valeursAutoGenerees.next() )
			{
				post.setId(valeursAutoGenerees.getInt(1));
				
			}
			else
			{
				throw new DAOException("Post creation error in DB , no auto generated ID was returned");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			 closeConnectionItems(preparedStatement,connexion); 
		}
	}

	@Override
	public Post find(int id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Post post = null;
		
		
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_SELECT_BY_ID, false,id);
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            post = map( resultSet ); 
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	    	closeConnectionItems( resultSet, preparedStatement, connexion );
	    }	
	    
	    return post;
	}
	

	@Override
	public void update(Object... fields) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Post post) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> find(Topic topic) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Post> post = new LinkedList<Post>();
		
		
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_SELECT_BY_TOPIC, false,topic.getId());
	        resultSet = preparedStatement.executeQuery();
	        while ( resultSet.next() ) {
	            post.add(map( resultSet )); 
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	    	closeConnectionItems( resultSet, preparedStatement, connexion );
	    }	
		return post;
	}

}
