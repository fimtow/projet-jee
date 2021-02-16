package ma.ensias.dao;

import ma.ensias.beans.Content;
import ma.ensias.beans.Post;
import ma.ensias.beans.Topic;
import ma.ensias.beans.User;
import static ma.ensias.dao.DAOusef.*;

public class PostDaoImpl implements PostDao {
	
	private DAOFactory daoFactory;
	
	PostDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(Post post) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Post find(String title, Content content, Topic topic, User user) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object... fields) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Post post) throws DAOException {
		// TODO Auto-generated method stub

	}

}
