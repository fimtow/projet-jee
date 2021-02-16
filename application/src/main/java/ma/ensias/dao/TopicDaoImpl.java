package ma.ensias.dao;

import ma.ensias.beans.Topic;

public class TopicDaoImpl implements TopicDao {
	
	private DAOFactory daoFactory;
	
	TopicDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(Topic topic) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Topic find(String title) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object... fields) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Topic topic) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
