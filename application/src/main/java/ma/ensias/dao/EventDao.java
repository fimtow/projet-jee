package ma.ensias.dao;

import ma.ensias.beans.Event;

public interface EventDao {
	
	void create(Event event) throws DAOException;
	
	Event find (int id) throws DAOException;
	// la fonction utilise varargs pour avoir la possibilte de changer un nombre variable de parametre
	void update(Event event) throws DAOException; 

}
