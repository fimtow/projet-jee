package ma.ensias.dao;

import ma.ensias.beans.Comment;
import ma.ensias.beans.User;

public interface CommentLikeDao {
	void create(Comment comment, User user, int status);
	int find(Comment comment, User user);
	void update(Comment comment, User user, int status);
}
