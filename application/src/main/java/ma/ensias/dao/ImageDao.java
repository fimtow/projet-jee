package ma.ensias.dao;

import ma.ensias.beans.Image;

public interface ImageDao {
	void create(Image image);
	Image find(int id);
	void update(Object...  fields);
	void delete(Image image);
}
