package ma.ensias.dao;

import ma.ensias.beans.Image;
import ma.ensias.beans.Post;

public interface ImageDao {
	void create(Image image);
	Image find(Post post);
	Image find(int id);
	void update(Object...  fields);
	void delete(Image image);
}
