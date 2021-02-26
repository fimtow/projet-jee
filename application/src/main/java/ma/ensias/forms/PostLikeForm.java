package ma.ensias.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ma.ensias.beans.Post;
import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.PostDao;
import ma.ensias.dao.PostLikeDao;
public class PostLikeForm {
	
    private static final String POST_FIELD = "post";
    private static final String LIKE_FIELD = "like";
    public static final String SESSION_USER = "userSession";
    
    private boolean success = true;
    private Map<String, String>  errors = new HashMap<String, String>();
    
    public Map<String, String> getErrors()
    {
    	return errors;
    }
    public boolean getResult() {
        return success;
    }
    public void insertLike(HttpServletRequest request)
    {	
    	String stringPostId = getFieldValue(request,POST_FIELD);
    	String stringStatus = getFieldValue(request,LIKE_FIELD);
    	
    	if(stringPostId == null || stringStatus == null)
    	{
    		success = false;
        	errors.put("fields", "missing fields value");
        	return;
    	}
    	int postID = Integer.parseInt(stringPostId);
    	DAOFactory daoFactory = DAOFactory.getInstance();
    	PostDao postDao = daoFactory.getPostDao();
    	Post post = postDao.find(postID);
    	if(post == null)
    	{
    		success = false;
    		errors.put("post", "inexistant post");
    		return;
    	}
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute(SESSION_USER);
    	
    	
    	int status = Integer.parseInt(stringStatus);
    	if(status < 1 || status > 3)
    	{
    		success = false;
    		errors.put("status", "inexistant status");
    		return;
    	}
    	
    	
    	PostLikeDao postLikeDao = daoFactory.getPostLikeDao();
    	int dbStatus = postLikeDao.find(post, user);
    	
    	if(dbStatus == 0)
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
