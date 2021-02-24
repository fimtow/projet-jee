package ma.ensias.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.UserDao;

class TestsDAOUser {
	
	public static UserDao userDao = DAOFactory.getInstance().getUserDao();
	
	@BeforeAll
    static void setup(){
        System.out.println("Starting Testing");
    }
	@BeforeEach
    void setupThis(){
        System.out.println("A Test will begin ");
    }
	@AfterEach
	void tearThis(){
      System.out.println("Test finished");
	}
	@AfterAll
    static void tear(){
        System.out.println("All Tests finished");
    }   
	@Test
	void testCreate()
	{
		User user = new User("Test","TestPassword","test@gmail.com");
		userDao.create(user);
		assertNotEquals(userDao.find(user.getId()),null);
	}
	@Test
	void testFind()
	{
		User user = new User("Mohammed_ab","123@2021","mohammed@gmail.com");
		User user2 = new User("Sabir","@@abcd","saber@gmail.com");
		
		assertEquals(userDao.find(1).getUsername(),user.getUsername());
		assertNotEquals(userDao.find(3).getUsername(),user2.getUsername());
	}
	
	@Test
	void testUpdate()
	{
		User user = userDao.find(3);
		user.setUsername("newUsername");
		userDao.update(user);;
		assertEquals(userDao.find(3).getUsername(),"newUsername");
	}

}
