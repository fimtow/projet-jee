package ma.ensias.dao;

import ma.ensias.beans.Topic;

public interface TopicDao {
	
	void create(Topic topic) throws DAOException;
	
	Topic find (String title) throws DAOException;
	// la fonction utilise varargs pour avoir la possibilte de changer un nombre variable de parametre
	void update(Object...  fields) throws DAOException; 
	
	void delete(Topic topic) throws DAOException;

}
