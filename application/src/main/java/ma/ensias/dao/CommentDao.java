package ma.ensias.dao;

import ma.ensias.beans.Comment;

public interface CommentDao {
	void create(Comment comment);
	Comment find(int id);
	void update(Object...  fields);
	void delete(Comment comment);
}
