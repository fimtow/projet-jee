package ma.ensias.dao;

import ma.ensias.beans.Topic;

public interface TopicDao {
	
	public void create(Topic topic,boolean isevent) throws DAOException;
	
	Topic find (int id) throws DAOException;
	// la fonction utilise varargs pour avoir la possibilte de changer un nombre variable de parametre
	void update(Topic topic) throws DAOException; 
	
	void delete(Topic topic) throws DAOException;

}
