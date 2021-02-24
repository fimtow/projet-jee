package ma.ensias.process;

import java.io.Serializable;
import java.util.LinkedList;
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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String USER_SESSION = "userSession";
	
	private User user;
	private List<Topic> listOfTopics; 
	private List<Post> listOfPosts;

	public HomeGenerator(HttpServletRequest request) {
		
		user = (User) request.getAttribute(USER_SESSION);
		listOfTopics = new LinkedList<>();
		listOfPosts = new LinkedList<>();
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
			 for(Post post : postDao.find(topic))
				 this.listOfPosts.add(post);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Topic> getListOfTopics() {
		return listOfTopics;
	}

	public void setListOfTopics(List<Topic> listOfTopics) {
		this.listOfTopics = listOfTopics;
	}

	public List<Post> getListOfPosts() {
		return listOfPosts;
	}

	public void setListOfPosts(List<Post> listOfPosts) {
		this.listOfPosts = listOfPosts;
	}
	
	
	
	
	
	
	
	

}
