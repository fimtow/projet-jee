package ma.ensias.dao;

import ma.ensias.beans.Topic;

public interface TopicDao {
	
	void create(Topic topic) ;
	
	Topic find (String title);
	// la fonction utilise varargs pour avoir la possibilte de changer un nombre variable de parametre
	void update(Object...  fields); 
	
	void delete(Topic topic);

}
