package ma.ensias.dao;

import ma.ensias.beans.Invitation;

public interface InvitationDao {
	void create(Invitation invitation);
	Invitation find(int id);
	void update(Object...  fields);
	void delete(Invitation invitation);
}
