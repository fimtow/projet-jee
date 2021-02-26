package ma.ensias.dao;

import java.util.List;
import java.util.Map;

import ma.ensias.beans.Topic;
import ma.ensias.beans.User;

public interface MemberDao {
	
	public void create(User user ,Topic topic) throws DAOException;
	
	public void create(User user,int idTopic, Boolean isModerator) throws DAOException;
	
	public List<Topic> find(User user) throws DAOException;
	
	public Map<User,Boolean> find(Topic topic) throws DAOException;
	
	public boolean find(User user,int idTopic) throws DAOException;
	
	public void delete(Topic topic, User user) throws DAOException;
	

}
