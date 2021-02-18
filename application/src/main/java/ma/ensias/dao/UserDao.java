package ma.ensias.dao;

import ma.ensias.beans.User;

public interface UserDao {
	
	void create(User user ) throws DAOException ;
	User find(String username) throws DAOException; 
	
	User find (String username,String password) throws DAOException;
	
	User find (int id) throws DAOException;
	
	void update(User user) throws DAOException;

	

}
