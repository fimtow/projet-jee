package ma.ensias.dao;

import ma.ensias.beans.User;

public class UserDaoImpl implements UserDao {
	
	private DAOFactory daoFactory;
	
	UserDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(User user) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User find(String username) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object... fields) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
