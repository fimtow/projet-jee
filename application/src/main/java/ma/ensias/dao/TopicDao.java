package ma.ensias.dao;

import java.util.List;

import ma.ensias.beans.Topic;

public interface TopicDao {
	
	public void create(Topic topic,boolean isevent) throws DAOException;
	

	
	public List<Topic> find() throws DAOException;
	
	Topic find (int id) throws DAOException;
	
	
	
	void update(Topic topic) throws DAOException; 
	
	
}
