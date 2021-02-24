package ma.ensias.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    /*
     * si le post est image ou texte
     */
    private static final String TOPIC_FIELD = "topic";
    /*
     * si text 
     */
    private static final String TEXT_FIELD = "text";
    /*
     * si image 
     */
    private static final String URL_FIELD = "url";
    /*
     * si le post est event
     */
    private static final String LOCATION_FIELD = "location";
    private static final String DATE_FIELD = "date";
    private static final String DESCRIPTION_FIELD = "description";
    public static final String SESSION_USER = "userSession";
    

    private boolean success = true;
    private List<Comment> comments;
    
    public boolean getResult() {
        return success;
    }
    
    public void createPost(HttpServletRequest request)
    {	
    	
    
    	String title = getFieldValue(request,TITLE_FIELD);
    	String typeOfContent = getFieldValue(request,CONTENT_TYPE_FIELD);
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute(SESSION_USER);
    	Content content = null ;
    	Post post;
    	
    	if(typeOfContent.equals("invitation"))
    	{	
    		
    		String location = getFieldValue(request,LOCATION_FIELD);
    		String dateString = getFieldValue(request,DATE_FIELD);
    		String description = getFieldValue(request,DESCRIPTION_FIELD);
    		Date date;
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
			} catch (ParseException e) {
				
				e.printStackTrace();
				success = false;
				return;
			}
			
    		Invitation invitation = new Invitation(description,date,location);
    		Event event = new Event(title,description,location,date,user);
    		DAOFactory.getInstance().getEventDao().create(event);
    		post = new Post(title,invitation,event,user);
    		DAOFactory.getInstance().getPostDao().create(post);
    		invitation.setPostId(post.getId());
    		DAOFactory.getInstance().getInvitationDao().create(invitation);
    		
			
    	}
    	else
    	{
	    	String idTopicString = getFieldValue(request,TOPIC_FIELD);
	    
	    	if(idTopicString == null)
	    	{	
	    		success = false;
	    		return;
	    	}
	    	
	    	int idTopic = Integer.parseInt(idTopicString);
	    	DAOFactory daoFactory = DAOFactory.getInstance();
	    	TopicDao topicDao = daoFactory.getTopicDao();
	    	Topic topic = topicDao.find(idTopic);
	    	if(  topic == null)
	    	{	
	    		this.success = false;
	    		return;
	    	}
	
	    	post = new Post(title,topic,user);
	    	
	    	if(typeOfContent.equals("text"))
	    	{
	    		post.setType(Post.TEXT);
	    		content = new Text(getFieldValue(request,TEXT_FIELD));
	    		
	    		 
	    	}
	    	else
	    	{
	    		post.setType(Post.IMAGE);
	    		content = new Image(getFieldValue(request,URL_FIELD));
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
