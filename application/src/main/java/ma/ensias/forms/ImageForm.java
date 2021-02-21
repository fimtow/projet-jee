package ma.ensias.forms;

import ma.ensias.beans.Image;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.ImageDao;

public class ImageForm {
	
	public Image createImage(String url,int idPost)
	{
		DAOFactory daoFactory = DAOFactory.getInstance();
		ImageDao imageDao = daoFactory.getImageDao();
		Image image = new Image(url,idPost);
		imageDao.create(image);
		return image;
	}

}
