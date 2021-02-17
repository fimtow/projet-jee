package ma.ensias.dao;

import java.util.List;

import ma.ensias.beans.Comment;
import ma.ensias.beans.Post;

public interface CommentDao {
	void create(Comment comment);
	Comment find(int id);
	List<Comment> findByPost(Post post);
	void update(Object...  fields);
	void delete(Comment comment);
}
