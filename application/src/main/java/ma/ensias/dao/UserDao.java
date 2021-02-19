package ma.ensias.dao;

import ma.ensias.beans.User;

public interface UserDao {
	
	public static final int USERNAME = 0;

	public static final int EMAIL = 1;
	
	void create(User user ) throws DAOException ;
	
	User find(String refChamp,int champ) throws DAOException; 
	
	User find (String username,String password) throws DAOException;
	
	
	User find (int id) throws DAOException;
	
	void update(User user) throws DAOException;

	

}
