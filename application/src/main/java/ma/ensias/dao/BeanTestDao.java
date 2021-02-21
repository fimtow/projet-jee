package ma.ensias.dao;
import ma.ensias.beans.BeanTest;

public interface BeanTestDao {
	void create(BeanTest beantest) throws DAOException;
	BeanTest find(String name) throws DAOException;
}
