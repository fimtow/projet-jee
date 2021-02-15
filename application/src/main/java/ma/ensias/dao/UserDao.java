package ma.ensias.dao;

import ma.ensias.beans.User;

public interface UserDao {
	
	void create(User user ) ;
	
	User find (String username);
	// la fonction utilise varargs pour avoir la possibilte de changer un nombre variable de parametre
	
	void update(Object...  fields); 

}
