package ma.ensias.dao;

import ma.ensias.beans.Invitation;

public class InvitationDaoImpl implements InvitationDao{

	private DAOFactory daoFactory;
	InvitationDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	@Override
	public void create(Invitation invitation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Invitation find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object... fields) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Invitation invitation) {
		// TODO Auto-generated method stub
		
	}

}
