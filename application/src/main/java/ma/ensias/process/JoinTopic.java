package ma.ensias.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;

public class JoinTopic {
	
	public static final String SESSION_USER = "userSession";
	
	public boolean succes = false; ;
	
	public JoinTopic(HttpServletRequest request)
	{	
		HttpSession httpSession = request.getSession();
		User user = (User)httpSession.getAttribute(SESSION_USER);
		if(user == null)
		{
			return;
		}
		String idTopicString = getFieldValue(request,"idtopic"); 
		
		if(idTopicString == null)
		{
			return;
		}
		int idTopic = Integer.parseInt(idTopicString);
		if(DAOFactory.getInstance().getMemberDao().find(user,idTopic))
		{
			return;
		}
		DAOFactory.getInstance().getMemberDao().create(user,idTopic,false);
		succes = true;
	}
	
	
	private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }

}
