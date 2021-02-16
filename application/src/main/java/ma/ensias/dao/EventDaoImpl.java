package ma.ensias.dao;

import ma.ensias.beans.Event;
import static ma.ensias.dao.DAOusef.*;

public class EventDaoImpl implements EventDao {
	
	
	private DAOFactory daoFactory;
	
	EventDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(Event event) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Event find(String title) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object... fields) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Event event) throws DAOException {
		// TODO Auto-generated method stub
		

	}

}
