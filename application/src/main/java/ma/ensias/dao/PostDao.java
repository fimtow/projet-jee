package ma.ensias.dao;

import ma.ensias.beans.Content;
import ma.ensias.beans.Post;
import ma.ensias.beans.Topic;
import ma.ensias.beans.User;

public interface PostDao {
	
	void create(Post post);
	
	Post find(String title,Content content,Topic topic,User user);
	
	void update(Object... fields);
	
	void delete(Post post);

}
