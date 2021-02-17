package ma.ensias.dao;

import java.util.Map;

import ma.ensias.beans.Topic;
import ma.ensias.beans.User;

public interface DAOmember {
	
	public void create(int idUser ,Topic topic) throws DAOException;
	
	public Map<Integer,Boolean> find(Topic topic) throws DAOException;
	
	public void delete(Topic topic, User user) throws DAOException;
	

}
