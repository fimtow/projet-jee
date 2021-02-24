package ma.ensias.dao;

import java.util.List;

import ma.ensias.beans.Post;
import ma.ensias.beans.Topic;
import ma.ensias.beans.User;

public interface PostDao {
	
	void create(Post post) throws DAOException;
	
	Post find(int id) throws DAOException;
	
	List<Post> find(Topic topic);
	
	public List<Post> find(User user) throws DAOException ;
	

}
