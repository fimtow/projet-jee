package ma.ensias.dao;

import java.util.List;

import ma.ensias.beans.Post;
import ma.ensias.beans.Topic;

public interface PostDao {
	
	void create(Post post) throws DAOException;
	
	Post find(int id) throws DAOException;
	List<Post> find(Topic topic);
	void update(Object... fields) throws DAOException;
	
	void delete(Post post) throws DAOException;

}
