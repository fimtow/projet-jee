package ma.ensias.dao;

import java.sql.*;

import ma.ensias.beans.BeanTest;

public class BeanTestDaoImpl implements BeanTestDao{

	private DAOFactory daoFactory;
	BeanTestDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	@Override
	public void create(BeanTest beantest) throws DAOException {
		try {
			Connection connection = daoFactory.getConnection();
            ResultSet rs = connection.getMetaData().getTables(null, null, "%", null);
            while(rs.next())
            {
                System.out.println(rs.getString("TABLE_NAME"));
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("BeanTest "+beantest.getName()+" created");
		
	}

	@Override
	public BeanTest find(String name) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
