package ma.ensias.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ma.ensias.beans.Comment;
import ma.ensias.beans.Content;
import ma.ensias.beans.Post;
import ma.ensias.beans.Topic;
import ma.ensias.beans.User;
import ma.ensias.dao.CommentDao;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.PostDao;
import ma.ensias.dao.TopicDao;

public class PostForm {
	
    private static final String ID_FIELD  = "id";
    private static final String TITLE_FIELD = "title";
    private static final String CONTENT_TYPE_FIELD = "type_content";
    private static final String TOPIC_FIELD = "topic";
    public static final String SESSION_USER = "userSession";
    

    private boolean success = true;
    private List<Comment> comments;
    public boolean getResult() {
        return success;
    }
    
    public void createPost(HttpServletRequest request)
    {	
    	String title = getFieldValue(request , TITLE_FIELD);
    	String titleOfTopic = getFieldValue(request , TOPIC_FIELD);
    	String typeOfContent = getFieldValue(request, CONTENT_TYPE_FIELD);
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute(SESSION_USER);
    	
    	DAOFactory daoFactory = DAOFactory.getInstance();
    	TopicDao topicDao = daoFactory.getTopicDao();
    	Topic topic = topicDao.find(titleOfTopic);
    	Post post = new Post(title,topic,user);
    	PostDao postDao = daoFactory.getPostDao();
    	postDao.create(post);
    	Content content = null;
    	
    	if(topic == null)
    	{	
    		this.success = false;
    		return;
    	}
    	if(typeOfContent == "text")
    		 content = new TextForm().createText(getFieldValue(request,"text"),post.getId());
    	else if (typeOfContent == "image")
    		content = new ImageForm().createImage(getFieldValue(request,"url"),post.getId());
    	else 
    		content = new InvitationForm().createInvitation(post.getId());
    	
    	post.setContent(content);
    		
    	// TODO : nkmel hada o ngad post n affecti lih dakshi dyalo 
    	
    	
    		
    	
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
