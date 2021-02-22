package ma.ensias.forms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ma.ensias.beans.Post;
import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.PostDao;
import ma.ensias.dao.PostLikeDao;
public class PostLikeForm {
	public static final int NOTSET = 0;
	public static final int NONE = 1;
	public static final int DISLIKE = 2;
	public static final int LIKE = 3;
	
    private static final String POST_FIELD = "post";
    private static final String LIKE_FIELD = "like";
    public static final String SESSION_USER = "userSession";
    
    private boolean success = true;
    public boolean getResult() {
        return success;
    }
    public void insertLike(HttpServletRequest request)
    {	
    	int postID = Integer.parseInt(getFieldValue(request,POST_FIELD));
    	DAOFactory daoFactory = DAOFactory.getInstance();
    	PostDao postDao = daoFactory.getPostDao();
    	Post post = postDao.find(postID);
    	if(post == null)
    	{
    		success = false;
    		return;
    	}
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute(SESSION_USER);
    	
    	
    	String like = getFieldValue(request,LIKE_FIELD);
    	int status;
    	if(like.equals("true"))
    		status = LIKE;
    	else if(like.equals("false"))
    		status = DISLIKE;
    	else
    		status = NONE;
    	PostLikeDao postLikeDao = daoFactory.getPostLikeDao();
    	int dbStatus = postLikeDao.find(post, user);
    	
    	if(dbStatus == NOTSET)
    		postLikeDao.create(post, user, status);
    	else if(dbStatus == status)
    		return;
    	else
    		postLikeDao.update(post, user, status);
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
