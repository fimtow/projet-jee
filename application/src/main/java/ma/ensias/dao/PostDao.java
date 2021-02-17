package ma.ensias.dao;

import ma.ensias.beans.Post;

public interface PostDao {
	
	void create(Post post) throws DAOException;
	
	Post find(int id) throws DAOException;
	
	void update(Object... fields) throws DAOException;
	
	void delete(Post post) throws DAOException;

}
