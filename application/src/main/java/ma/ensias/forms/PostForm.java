package ma.ensias.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ma.ensias.beans.Comment;
import ma.ensias.beans.Content;
import ma.ensias.beans.Event;
import ma.ensias.beans.Image;
import ma.ensias.beans.Invitation;
import ma.ensias.beans.Post;
import ma.ensias.beans.Text;
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
    public static final String SESSION_USER = "userSession";
    private static final String TOPIC_FIELD = "topic";
    /*
     * si le post est event
     */
    private static final String LOCATION_FIELD = "location";
    private static final String DATE_FIELD = "date";
    private static final String DESCRIPTION_FIELD = "description";
    /*
     * si le post est text
     */
    private static final String TEXT_FIELD = "text";
    /*
     * si le post est image
     */
    private static final String URL_FIELD = "url";
    
  
    

    private boolean success = true;
    private Map<String, String>  errors = new HashMap<String, String>();
    
    public Map<String, String> getErrors()
    {
    	return errors;
    }
    private List<Comment> comments;
    
    public boolean getResult() {
        return success;
    }
    
    public void createPost(HttpServletRequest request)
    {	
    	
    
    	String title = getFieldValue(request,TITLE_FIELD);
    	String typeOfContent = getFieldValue(request,CONTENT_TYPE_FIELD);
    	
        if(title == null )
        {
        	success = false;
        	errors.put("fields", "missing title field value");
        	return;
        }
        if( typeOfContent == null)
        {
        	success = false;
        	errors.put("fields", "missing type of content field value");
        	return;
        }
        
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute(SESSION_USER);
    	Content content = null ;
    	Post post;
    	
    	String idTopicString = getFieldValue(request,TOPIC_FIELD);
    	if(idTopicString == null)
    	{
    		success = false;
    		errors.put("idTopic", "missing fields value");
    		return;
    	}
    	int idTopic = Integer.parseInt(idTopicString);
    	
    	if(typeOfContent.equals("invitation"))
    	{	
    		
    		String location = getFieldValue(request,LOCATION_FIELD);
    		String dateString = getFieldValue(request,DATE_FIELD);
    		String description = getFieldValue(request,DESCRIPTION_FIELD);
    		Date date;
    		if( location == null || dateString == null || description == null)
    		{	
    			success = false;
    			return;
    		}
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
			} catch (ParseException e) {
				
				errors.put("date", "Invalid date");
				success = false;
				return;
			}
			
    		Invitation invitation = new Invitation(description,date,location,idTopic);
    		Event event = new Event(title,description,location,date,user);
    		DAOFactory.getInstance().getEventDao().create(event);
    		
    		DAOFactory daoFactory = DAOFactory.getInstance();
	    	TopicDao topicDao = daoFactory.getTopicDao();
	    	Topic topic = topicDao.find(idTopic);
	    	if(topic == null)
	    	{	
	    		this.success = false;
	    		errors.put("topic", "inexistent topic");
	    		return;
	    	}
    		post = new Post(title,invitation,topic,user);
    		DAOFactory.getInstance().getPostDao().create(post);
    		invitation.setPostId(post.getId());
    		DAOFactory.getInstance().getInvitationDao().create(invitation);
    	}
    	else if( typeOfContent.equals("text") || typeOfContent.equals("image"))
    	{	
    		DAOFactory daoFactory = DAOFactory.getInstance();
	    	TopicDao topicDao = daoFactory.getTopicDao();
	    	Topic topic = topicDao.find(idTopic);
	    	if(topic == null)
	    	{	
	    		this.success = false;
	    		errors.put("topic", "inexistent topic");
	    		return;
	    	}
	
	    	post = new Post(title,topic,user);
	    	
	    	if(typeOfContent.equals("text"))
	    	{
	    		post.setType(Post.TEXT);
	    		String value = getFieldValue(request,TEXT_FIELD);
		    	if(value == null)
		    	{	
		    		success = false;
		    		errors.put("fields", "missing topic field value");
		    		return;
		    	}
	    		content = new Text(value);
	    	}
	    	else
	    	{
	    		post.setType(Post.IMAGE);
	    		String value = getFieldValue(request,URL_FIELD);
		    	if(value == null)
		    	{	
		    		success = false;
		    		errors.put("fields", "missing url field value");
		    		return;
		    	}
	    		content = new Image(value);
	    	}
	    	
	    	
	    	post.setContent(content);
	    	DAOFactory.getInstance().getPostDao().create(post);
	    	content.setPostId(post.getId());
	    	
	    	if(typeOfContent.equals("text"))
	    	{
	    		daoFactory.getTextDao().create((Text)content);
	    		
	    	}
	    	else if (typeOfContent.equals("image"))
	    	{
	    		daoFactory.getImageDao().create((Image)content);
	    	}
    		
    		
    	}
    	else
    	{
    		success = false;
    		errors.put("topic", "inexistent type");
    		return;
    	}	
    }
    
   

    public Post searchPost( HttpServletRequest request ) {
    	String stringId = getFieldValue( request, ID_FIELD );
    	
        if(stringId == null)
        {
        	success = false;
        	errors.put("fields", "missing fields value");
        	return null;
        }
    	
    	
        int id = Integer.parseInt(stringId);
        
        DAOFactory daoFactory = DAOFactory.getInstance();
        PostDao postDao = daoFactory.getPostDao();
        
        Post post = postDao.find(id);
        
        if(post == null)
        {
        	success = false;
        	errors.put("post", "inexistent post");
        	return null;
        }
        	
        
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
