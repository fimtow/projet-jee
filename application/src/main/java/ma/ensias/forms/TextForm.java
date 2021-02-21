package ma.ensias.forms;

import ma.ensias.beans.Text;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.TextDao;

public class TextForm {
	

	public Text createText(String textContent,int idPost)
	{
		DAOFactory daoFactory = DAOFactory.getInstance();
		TextDao textDao = daoFactory.getTextDao();
		Text text = new Text(textContent,idPost);
		textDao.create(text);
		return text;
	}

}
