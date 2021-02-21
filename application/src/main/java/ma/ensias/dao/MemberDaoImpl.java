package ma.ensias.dao;

import static ma.ensias.dao.DAOusef.closeConnectionItems;
import static ma.ensias.dao.DAOusef.initQueryPrepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ma.ensias.beans.Topic;
import ma.ensias.beans.User;

public class MemberDaoImpl implements MemberDao{
	
	public static final String SQL_INSERT = "INSERT INTO member(userid,topicid,ismoderator) values (?,?,?)";
	public static final String SQL_SELECT_BY_TOPIC = "SELECT userid,topicid,ismoderator FROM member WHERE topicid = ? ";
	public static final String SQL_SELECT_BY_USER = "SELECT topicid FROM member WHERE member.userid = ? ";
	public static final String SQL_DELETE = "DELETE FROM member WHERE userid = ? AND topicid = ?";
	
	private DAOFactory daoFactory;
	
	
	
	MemberDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	/*
	private  Map<User,Boolean> map(ResultSet resultset) throws SQLException {
		Map<User,Boolean> members = new HashMap<>();
		
		while(resultset.next())
		{
			members.put(daoFactory.getUserDao().find(resultset.getInt("userid")),resultset.getBoolean("ismoderator"));
		}
		
		return members;
	}
	*/

	@Override
	public void create(User user,Topic topic) throws DAOException {
		
		Connection connexion = null;
		PreparedStatement  preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();

			preparedStatement = initQueryPrepared(connexion,SQL_INSERT,false,user.getId(),topic.getId(),topic.getMembers().get(user));
			int statut = preparedStatement.executeUpdate();
			if(statut == 0 )
			{
				throw new DAOException("Member Insertion error , no line was inserted");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnectionItems(preparedStatement,connexion);
		}
	}
	
	@Override
	public Map<User, Boolean> find(Topic topic) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<User, Boolean> members = new HashMap<>();
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_SELECT_BY_TOPIC, false, topic.getId());
	        resultSet = preparedStatement.executeQuery();
	        while( resultSet.next() ) {
	            members.put(daoFactory.getUserDao().find(resultSet.getInt("userid")),resultSet.getBoolean("ismoderator"));
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	    	closeConnectionItems( resultSet, preparedStatement, connexion );
	    }		

		return members;
	}
	@Override
	public List<Topic> find(User user) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Topic topic = null;
		List<Topic> topics = new LinkedList<>();
		TopicDao topicDao = daoFactory.getTopicDao();
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_SELECT_BY_USER ,false, user.getId());
	        resultSet = preparedStatement.executeQuery();
	       while( resultSet.next() )
	        {
	            topic = topicDao.find(resultSet.getInt("topicid"));
	            topics.add(topic);
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	    	closeConnectionItems( resultSet, preparedStatement, connexion );
	    }		

		return topics;
	}


	@Override
	public void delete(Topic topic , User user) throws DAOException {
		
		Connection connexion = null;
		PreparedStatement  preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initQueryPrepared(connexion,SQL_DELETE,false,user.getId(),topic.getId());
			int statut = preparedStatement.executeUpdate();
			if(statut == 0 )
			{
				throw new DAOException("Member delete error , no line was deleted");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnectionItems(preparedStatement,connexion);
		}
		
	}

	

}
