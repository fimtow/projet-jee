package ma.ensias.test;
import ma.ensias.dao.*;

import ma.ensias.beans.*;
public class Test {

	public static void main(String[] args) {
		BeanTest beanTest = new BeanTest();
		beanTest.setName("loool");
		DAOFactory daoFactory = DAOFactory.getInstance();
		BeanTestDao beanTestDao = daoFactory.getBeanTestDao();
		beanTestDao.create(beanTest);
	}

}
