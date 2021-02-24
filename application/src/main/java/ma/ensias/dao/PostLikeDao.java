package ma.ensias.dao;

import ma.ensias.beans.Post;
import ma.ensias.beans.User;

public interface PostLikeDao {
	void create(Post post, User user, int status);
	int find(Post post, User user);
	void update(Post post, User user, int status);
}
