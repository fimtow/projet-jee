package ma.ensias.dao;

import ma.ensias.beans.Invitation;
import ma.ensias.beans.Post;

public interface InvitationDao {
	void create(Invitation invitation);
	Invitation find(Post post);
	Invitation find(int id);
	void update(Object...  fields);
	void delete(Invitation invitation);
}
