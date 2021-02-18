package ma.ensias.dao;

import static ma.ensias.dao.DAOusef.closeConnectionItems;
import static ma.ensias.dao.DAOusef.initQueryPrepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ma.ensias.beans.Topic;
import ma.ensias.beans.User;

public class MemberDaoImpl implements MemberDao{
	
	public static final String SQL_INSERT = "INSERT INTO membre(userid,topicid,ismoderator) values (?,?,?)";
	public static final String SQL_SELECT_BY_TOPIC = "SELECT userid,ismoderator FROM member WHERE topicid = ? ";
	public static final String SQL_DELETE = "DELETE FROM membre WHERE userid = ? AND topicid = ?";
	
	private DAOFactory daoFactory;
	
	
	
	MemberDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	private  Map<User,Boolean> map(ResultSet resultset) throws SQLException {
		Map<User,Boolean> members = new HashMap<>();
		while(resultset.next())
		{
			members.put(daoFactory.getUserDao().find(resultset.getInt("userid")),resultset.getBoolean("ismoderaotr"));
		}
		
		return members;
	}


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
		Map<User, Boolean> members = null;
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_SELECT_BY_TOPIC, false, topic.getId());
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            members = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	    	closeConnectionItems( resultSet, preparedStatement, connexion );
	    }		

		return members;
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