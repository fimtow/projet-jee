package ma.ensias.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.ensias.beans.Comment;
import ma.ensias.beans.Post;
import ma.ensias.beans.User;
import ma.ensias.dao.CommentDao;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.PostDao;

public class PostForm {
    private static final String ID_FIELD  = "id";

    private boolean success = true;
    private List<Comment> comments;
    public boolean getResult() {
        return success;
    }


    public Post searchPost( HttpServletRequest request ) {

        int id = Integer.parseInt(getFieldValue( request, ID_FIELD ));
        
        DAOFactory daoFactory = DAOFactory.getInstance();
        PostDao postDao = daoFactory.getPostDao();
        
        Post post = postDao.find(id);
        
        if(post == null)
        	success = false;
        
        if(success)
        {
        	User postUser = post.getUser();
        	postUser.setPassword(null);
        	postUser.setEmail(null);
        	CommentDao commentDao = daoFactory.getCommentDao();
        	comments = commentDao.find(post);
        	for(Comment comment : comments)
        	{
        		comment.setPost(null);
        		User user = comment.getUser();
        		user.setPassword(null);
        		user.setEmail(null);
        	}
        }
        return post;
    }
    
    public List<Comment> getComments()
    {
    	return comments;
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
