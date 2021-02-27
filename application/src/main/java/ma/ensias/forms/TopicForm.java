package ma.ensias.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ma.ensias.beans.Post;
import ma.ensias.beans.Topic;
import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.MemberDao;
import ma.ensias.dao.PostDao;
import ma.ensias.dao.TopicDao;

public class TopicForm {
    private static final String ID_FIELD  = "id";
    private static final String TITLE_FIELD   = "title";
    private static final String DESCRIPTION_FIELD   = "description";
    private static final String ICON_FIELD   = "icon";
    private static final String COVER_FIELD   = "cover";
    public static final String SESSION_USER = "userSession";
    
    
    
    private boolean success = true;
    private List<Post> posts;
    private boolean joined;
    private boolean logged = false;
    
    private Map<String, String>  errors = new HashMap<String, String>();
    
    public Map<String, String> getErrors()
    {
    	return errors;
    }
    
    public boolean getJoined()
    {
    	return joined;
    }
    
    public boolean getLogged()
    {
    	return logged;
    }
    public boolean getResult() {
        return success;
    }

    public void createTopic( HttpServletRequest request ) {
    	
    	success = false;
        /* get values from form */
        String title = getFieldValue( request, TITLE_FIELD );
        String description = getFieldValue( request, DESCRIPTION_FIELD );
        String icon = getFieldValue( request, ICON_FIELD );
        String cover = getFieldValue( request, COVER_FIELD );
        
        if(title == null)
        {
        	success = false;
        	errors.put("fields", "missing fields value");
        	return;
        }
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute( SESSION_USER );
        
        Topic topic = new Topic(title,description,icon,cover,user);
        DAOFactory daoFactory = DAOFactory.getInstance();
        TopicDao topicDao = daoFactory.getTopicDao();
        topicDao.create(topic, false);
        
        success = true;
    }
    public Topic searchTopic( HttpServletRequest request ) {

        
        String stringId	= getFieldValue( request, ID_FIELD );
        
        if(stringId == null)
        {
        	success = false;
        	errors.put("fields", "missing fields value");
        	return null;
        }
        
        int id = Integer.parseInt(stringId);
        DAOFactory daoFactory = DAOFactory.getInstance();
        TopicDao topicDao = daoFactory.getTopicDao();
        
        Topic topic = topicDao.find(id);
        
        if(topic == null)
        {
        	success = false;
        	errors.put("topic", "Inexistent topic");
        	return null;
        }
        
        	
    	// topic members isn't showing correctly (it's showing the user oject reference) , so i hid it
    	topic.setMembers(null);
    	
        HttpSession session = request.getSession();
        User cuser = (User) session.getAttribute( SESSION_USER );
        if(cuser != null)
        {
        	logged = true;
        	MemberDao memberDao = daoFactory.getMemberDao();
        	joined = memberDao.find(cuser, topic);
        }
    	PostDao postDao = daoFactory.getPostDao();
    	posts = postDao.find(topic);
    	for(Post post : posts)
    	{
    		post.setTopic(null);
    		User user = post.getUser();
    		user.setPassword(null);
    		user.setEmail(null);
    		post.setUser(user);
    	}
    	
        return topic;
    }
    
    public List<Post> getPosts()
    {
    	return posts;
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
