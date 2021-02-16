package ma.ensias.dao;

import ma.ensias.beans.Image;

public class ImageDaoImpl implements ImageDao{

	private DAOFactory daoFactory;
	ImageDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	@Override
	public void create(Image image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object... fields) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Image image) {
		// TODO Auto-generated method stub
		
	}

}
