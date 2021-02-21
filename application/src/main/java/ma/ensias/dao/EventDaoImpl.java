package ma.ensias.dao;

import static ma.ensias.dao.DAOusef.closeConnectionItems;
import static ma.ensias.dao.DAOusef.initQueryPrepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import ma.ensias.beans.Event;
import ma.ensias.beans.Topic;
import ma.ensias.beans.User;

public class EventDaoImpl implements EventDao {
	
	private static final String SQL_INSERT = "INSERT INTO event(id,location,datetime) VALUES (?,?,?) ";
	private static final String SQL_SELECT_BY_ID = "SELECT topic.id, topic.title, topic.iconUrl, topic.coverUrl,event.location,event.datetime FROM event,topic WHERE id = ? AND topic.id = event.id";
	private static final String SQL_UPDATE = "UPDATE event SET location = ?, datetime = ?  WHERE id = ?";
	
	private DAOFactory daoFactory;
	
	EventDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	
	private Event map(ResultSet resultset) throws SQLException {
		Event event = new Event();
		event.setId(resultset.getInt("id"));
		event.setTitle(resultset.getString("title"));
		event.setIconUrl(resultset.getString("iconUrl"));
		event.setCoverUrl(resultset.getString("coverUrl"));
		event.setLocation(resultset.getString("location"));
		event.setDate(resultset.getDate("datetime"));
		return event;
	}
	

	@Override
	public void create(Event event) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connexion = null;
		PreparedStatement  preparedStatement = null;
		int id = event.getId();
		String location = event.getLocation();
		Date date = event.getDate();
		Topic topic = new Topic(event.getTitle(),event.getDescription(),event.getIconUrl(),event.getCoverUrl());
		daoFactory.getTopicDao().create(topic,true); // To store the part of topic into table topic
		
		try 
		{	
			connexion = daoFactory.getConnection();
			preparedStatement = initQueryPrepared(connexion,SQL_INSERT,false,id,location,date);
			int statut = preparedStatement.executeUpdate();
			if(statut == 0)
			{
				throw new DAOException("Event creation error , no line was inserted");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			 closeConnectionItems(preparedStatement,connexion);
			 
			 for(User user : event.getMembers().keySet())
					daoFactory.getMemberDao().create(user, event);
			 
		}

	}

	@Override
	public Event find(int id) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Event event = null;
		
		
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_SELECT_BY_ID, false,id);
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            event = map( resultSet ); 
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	    	closeConnectionItems( resultSet, preparedStatement, connexion );
	    }	
	    event.setMembers(daoFactory.getMemberDao().find(event));
	    return event;
	}

	@Override
	public void update(Event event) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement  preparedStatement = null;
		
		String location = event.getLocation();
		Date date = event.getDate();
		
		try {
			 connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_UPDATE, false,location,date,event.getId());
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
