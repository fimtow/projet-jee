package ma.ensias.dao;

import ma.ensias.beans.Event;

public interface EventDao {
	
	void create(Event event) ;
	
	Event find (String title);
	// la fonction utilise varargs pour avoir la possibilte de changer un nombre variable de parametre
	void update(Object...  fields); 
	
	void delete(Event event);

}
