package ma.ensias.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.ensias.beans.Post;
import ma.ensias.beans.Topic;
import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.PostDao;
import ma.ensias.dao.TopicDao;

public class TopicForm {
    private static final String ID_FIELD  = "id";

    private boolean success = true;
    private List<Post> posts;
    public boolean getResult() {
        return success;
    }


    public Topic searchTopic( HttpServletRequest request ) {

        int id = Integer.parseInt(getFieldValue( request, ID_FIELD ));
        
        DAOFactory daoFactory = DAOFactory.getInstance();
        TopicDao topicDao = daoFactory.getTopicDao();
        
        Topic topic = topicDao.find(id);
        
        if(topic == null)
        	success = false;
        
        if(success)
        {
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
