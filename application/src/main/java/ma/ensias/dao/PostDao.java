package ma.ensias.dao;

import ma.ensias.beans.Content;
import ma.ensias.beans.Post;
import ma.ensias.beans.Topic;
import ma.ensias.beans.User;

public interface PostDao {
	
	void create(Post post) throws DAOException;
	
	Post find(String title,Content content,Topic topic,User user) throws DAOException;
	
	void update(Object... fields) throws DAOException;
	
	void delete(Post post) throws DAOException;

}
