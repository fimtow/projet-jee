package ma.ensias.dao;

import ma.ensias.beans.Text;

public class TextDaoImpl implements TextDao{

	private DAOFactory daoFactory;
	TextDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	@Override
	public void create(Text text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Text find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object... fields) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Text text) {
		// TODO Auto-generated method stub
		
	}

}
