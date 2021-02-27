package ma.ensias.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ma.ensias.beans.Comment;
import ma.ensias.beans.Post;
import ma.ensias.beans.User;
import ma.ensias.dao.CommentDao;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.PostDao;


public class CommentForm {
    private static final String POST_FIELD  = "post";
    private static final String COMMENT_FIELD   = "comment";
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


    public void createComment( HttpServletRequest request ) {
        /* get values from form */
    	String stringPostId = getFieldValue( request, POST_FIELD );
    	String commentText = getFieldValue( request, COMMENT_FIELD );
    	 
        if(stringPostId == null || commentText == null)
        {
        	success = false;
        	errors.put("fields", "missing fields value");
        	return;
        }
    	 
    	 
        int postId = Integer.parseInt(stringPostId);
       
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute( SESSION_USER );
        DAOFactory daoFactory = DAOFactory.getInstance();
        PostDao postDao = daoFactory.getPostDao();
        Post post = postDao.find(postId);
        if (post == null)
        {
        	success = false;
        	return;
        }
        CommentDao commentDao = daoFactory.getCommentDao();
        Comment comment = new Comment(commentText,user,post);
        commentDao.create(comment);

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
