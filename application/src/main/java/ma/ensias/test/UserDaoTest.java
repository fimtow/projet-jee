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
import ma.ensias.dao.UserDaoImpl;

class UserDaoTest {
	
	public static UserDao userDao;
	
	@BeforeAll
    static void setup(){
		userDao = DAOFactory.getInstance().getUserDao();
        System.out.println("Starting Testing , all variables are set");
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
		User userFromDataBase = userDao.find(user.getId());
		assertEquals(userFromDataBase.getId(),user.getId());
		assertEquals(userFromDataBase.getPassword(),UserDaoImpl.getMd5(user.getPassword()));
		assertEquals(userFromDataBase.getEmail(),user.getEmail());
	}
	@Test
	void testFind()
	{
		User userExistInDB = new User("Test","TestPassword","test@gmail.com");
		User userDoesntExist = new User("xxx","mdp","xxx@gmail.com");
		/* 
		 * User exist in DB
		 */
		assertEquals(userDao.find("Test","TestPassword").getUsername(),userExistInDB.getUsername());
		assertEquals(userDao.find(1).getUsername(),userExistInDB.getUsername());
		/*
		 *  User doesn't exist
		 */
		assertNotEquals(userDao.find("xxx","mdp"),userDoesntExist);
		assertEquals(userDao.find(12),null);
	}
	
}
