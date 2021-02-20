package ma.ensias.process;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.ensias.beans.Post;
import ma.ensias.beans.Topic;
import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.MemberDao;
import ma.ensias.dao.PostDao;
import ma.ensias.dao.TopicDao;

public class HomeGenerator implements Serializable  { 
	
	private static final String USER_SESSION = "userSession";
	
	private User user;
	private List<Topic> listOfTopics; 
	private List<Post> listOfPosts;

	public HomeGenerator(HttpServletRequest request) {
		
		user = (User) request.getAttribute(USER_SESSION);
		if( user != null)
		{
			getTopicsOfUser();
			getPostsOfTopic();
		}
		else
		{
			getRandomTopics();
			getPostsOfTopic();
		}
	}
	
	public void getPostsOfTopic()
	{	
		for(Topic topic : listOfTopics)
		{
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 PostDao postDao = daoFactory.getPostDao();
			 this.listOfPosts = postDao.find(topic);
		}
	}
	public void getTopicsOfUser()
	{
		 DAOFactory daoFactory = DAOFactory.getInstance();
		 MemberDao memberDao = daoFactory.getMemberDao();
		 listOfTopics = memberDao.find(user);
	}
	public void getRandomTopics()
	{
		DAOFactory daoFactory = DAOFactory.getInstance();
		TopicDao topicDao = daoFactory.getTopicDao();
		listOfTopics = topicDao.find();
		
	}
	
	
	
	
	
	
	
	

}
