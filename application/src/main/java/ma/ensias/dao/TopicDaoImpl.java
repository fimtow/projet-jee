package ma.ensias.dao;

import static ma.ensias.dao.DAOusef.closeConnectionItems;
import static ma.ensias.dao.DAOusef.initQueryPrepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ma.ensias.beans.Topic;

public class TopicDaoImpl implements TopicDao {
	
	private static final String SQL_INSERT = "INSERT INTO topic(title,description,iconUrl,coverUrl,isevent) VALUES (?,?,?,?,?) ";
	private static final String SQL_SELECT_BY_ID = "SELECT id,title,description,iconUrl,coverUrl,isevent FROM topic WHERE id = ?";
	private static final String SQL_UPDATE = "UPDATE topic SET title = ?, description = ?, iconUrl = ?, coverUrl = ?  WHERE id = ?";
	
	
	private DAOFactory daoFactory;
	
	TopicDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	
	private static Topic map(ResultSet resultset) throws SQLException {
		Topic topic = new Topic();
		topic.setId(resultset.getInt("id"));
		topic.setDescription(resultset.getString("description"));
		topic.setTitle(resultset.getString("title"));
		topic.setIconUrl(resultset.getString("iconUrl"));		
		return topic;
	}


	@Override
	public void create(Topic topic,boolean isevent) throws DAOException {
		Connection connexion = null;
		PreparedStatement  preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		
		
		String title = topic.getTitle();
		String iconUrl = topic.getIconUrl();
		String coverUrl = topic.getCoverUrl();
		String description = topic.getDescription();
		
		try 
		{	
			connexion = daoFactory.getConnection();
			preparedStatement = initQueryPrepared(connexion,SQL_INSERT,true,title,description,iconUrl,coverUrl,isevent);
			int statut = preparedStatement.executeUpdate();
			if(statut == 0)
			{
				throw new DAOException("Topic creation error , no line was inserted");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if(valeursAutoGenerees.next() )
			{
				topic.setId(valeursAutoGenerees.getInt(1));
				
			}
			else
			{
				throw new DAOException("User creation error in DB , no auto generated ID was returned");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			 closeConnectionItems(preparedStatement,connexion);
			 
			 for(int idUser : topic.getMembers().keySet())
					new DAOmemberImpl(daoFactory).create(idUser, topic);
			 
		}
	}

	@Override
	public Topic find(int id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Topic topic = null;
		
		
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_SELECT_BY_ID, false,id);
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            topic = map( resultSet ); 
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	    	closeConnectionItems( resultSet, preparedStatement, connexion );
	    }	
	    topic.setMembers(new DAOmemberImpl(daoFactory).find(topic));
	    return topic;
	}

	@Override
	public void update(Topic topic) throws DAOException {
		Connection connexion = null;
		PreparedStatement  preparedStatement = null;
		
		int id = topic.getId();
		String title = topic.getTitle();
		String iconUrl = topic.getIconUrl();
		String coverUrl = topic.getCoverUrl();
		String description = topic.getDescription();
		
		try {
			 connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_UPDATE, false,title,description,iconUrl,coverUrl,id );
	        int statut = preparedStatement.executeUpdate();
	        if(statut  == 0)
	        {
	        	throw new DAOException("topic update error , no line was updated");
	        }

		} catch(SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnectionItems(preparedStatement, connexion );
		}
	}

	

}
