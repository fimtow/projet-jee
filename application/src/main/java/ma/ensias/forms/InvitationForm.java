package ma.ensias.forms;

import ma.ensias.beans.Invitation;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.InvitationDao;

public class InvitationForm {
	
	
	public Invitation createInvitation(int idPost)
	{
		DAOFactory daoFactory = DAOFactory.getInstance();
		InvitationDao invitationDao = daoFactory.getInvitationDao();
		Invitation invitation = new Invitation(idPost);
		invitationDao.create(invitation);
		return invitation;
	}

}
