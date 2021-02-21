package ma.ensias.forms;

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

    public boolean getResult() {
        return success;
    }


    public void createComment( HttpServletRequest request ) {
        /* get values from form */
        int postId = Integer.parseInt(getFieldValue( request, POST_FIELD ));
        String commentText = getFieldValue( request, COMMENT_FIELD );
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
