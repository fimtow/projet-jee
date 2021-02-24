package ma.ensias.process;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ma.ensias.beans.Post;
import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.UserDao;

public class UserGenerator {
	
	public static final String SESSION_USER = "userSession";
	
	public User user;
	public List<Post> listOfPosts;
	public boolean succes = false;
	
	
	public UserGenerator(HttpServletRequest request )
	{	
	
		HttpSession session = request.getSession();
		User userOfTheSession = (User) session.getAttribute(SESSION_USER);
		String idString = getFieldValue(request,"id");
		if(idString != null )
		{
			int id = Integer.parseInt(idString);
			
			if( userOfTheSession != null  && userOfTheSession.getId() == id )
			{
				user = userOfTheSession;
			}
			else
			{
				UserDao userDao = DAOFactory.getInstance().getUserDao();
				user = userDao.find(id);
			}
			if( user != null)
			{
				listOfPosts = DAOFactory.getInstance().getPostDao().find(user);
				for(Post post : listOfPosts)
				{
					post.getUser().setPassword(null);
				}
				user.setPassword(null);
				succes = true;
			}
		}
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
