package ma.ensias.dao;

import ma.ensias.beans.Text;

public interface TextDao {
	void create(Text text);
	Text find(int id);
	void update(Object...  fields);
	void delete(Text text);
}
