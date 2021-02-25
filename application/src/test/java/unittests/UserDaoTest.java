package unittests;





import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.UserDao;
import ma.ensias.dao.UserDaoImpl;

class UserDaoTest {
	
	public static UserDao userDao;
	
	@Before
    static void setup(){
		userDao = DAOFactory.getInstance().getUserDao();
        System.out.println("Starting Testing , all variables are set");
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
