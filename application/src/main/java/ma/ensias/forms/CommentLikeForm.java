package ma.ensias.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ma.ensias.beans.Comment;
import ma.ensias.beans.User;
import ma.ensias.dao.CommentDao;
import ma.ensias.dao.CommentLikeDao;
import ma.ensias.dao.DAOFactory;
public class CommentLikeForm {
	
    private static final String COMMENT_FIELD = "comment";
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
    	String stringCommentID = getFieldValue(request,COMMENT_FIELD);
    	String stringStatus = getFieldValue(request,LIKE_FIELD);
    	
    	if(stringCommentID == null || stringStatus == null)
    	{
    		success = false;
        	errors.put("fields", "missing fields value");
        	return;
    	}
    	
    	
    	
    	int commentID = Integer.parseInt(stringCommentID);
    	DAOFactory daoFactory = DAOFactory.getInstance();
    	CommentDao commentDao = daoFactory.getCommentDao();
    	Comment comment = commentDao.find(commentID);
    	if(comment == null)
    	{
    		success = false;
    		errors.put("comment", "inexsitant comment");
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
    	
    	
    	CommentLikeDao commentLikeDao = daoFactory.getCommentLikeDao();
    	int dbStatus = commentLikeDao.find(comment, user);
    	
    	if(dbStatus == 0)
    		commentLikeDao.create(comment, user, status);
    	else if(dbStatus == status)
    		return;
    	else
    		commentLikeDao.update(comment, user, status);
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
