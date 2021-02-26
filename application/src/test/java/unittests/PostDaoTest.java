package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import ma.ensias.beans.Content;
import ma.ensias.beans.Post;
import ma.ensias.beans.Text;
import ma.ensias.beans.Topic;
import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.PostDao;

public class PostDaoTest {
	
	public static PostDao postDao;
	
	@Before
    static void setup(){
		postDao = DAOFactory.getInstance().getPostDao();
        System.out.println("Starting Testing , all variables are set");
    }
	@Test
	void testCreate()
	{	
		Content content = new Text("text de post");
		Topic topic = DAOFactory.getInstance().getTopicDao().find(4);
		User user = DAOFactory.getInstance().getUserDao().find(1);
		Post post = new Post("Test post",content,topic,user);
		postDao.create(post);
		assertEquals(postDao.find(post.getId()).getTitle(),post.getTitle());
		
	}
	@Test
	void testFind()
	{
		Post post = postDao.find(1);
		assertEquals(post.getTitle(),"test de post");
	}
	
}