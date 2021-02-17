package ma.ensias.dao;

import static ma.ensias.dao.DAOusef.closeConnectionItems;
import static ma.ensias.dao.DAOusef.initQueryPrepared;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ma.ensias.beans.User;

public class UserDaoImpl implements UserDao {
	
	private static final String SQL_INSERT = "INSERT INTO User(username,password,email) VALUES (?,?,?) ";
	private static final String SQL_SELECT_BY_USERNAME_PASSWORD = "SELECT id,username,password,email FROM user WHERE username = ? AND password = ? ";
	private static final String SQL_SELECT_BY_ID = "SELECT id,username,password,email FROM user WHERE id = ? ";
	private static final String SQL_UPDATE = "UPDATE user SET username = ?, password = ?, email = ? WHERE id = ?";
	
	private DAOFactory daoFactory;
	
	UserDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	
	private static User map(ResultSet resultset) throws SQLException {
		User User = new User();
		User.setId(resultset.getInt("id"));
		User.setUsername(resultset.getString("username"));
		User.setEmail(resultset.getString("email"));
		User.setPassword(resultset.getString("password"));
		
		return User;
	}

	@Override
	public void create(User user) throws DAOException {
		Connection connexion = null;
		PreparedStatement  preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		
		String username = user.getUsername();
		String email = user.getEmail();
		String password = getMd5(user.getPassword());
		
		
		try 
		{	
			connexion = daoFactory.getConnection();
			preparedStatement = initQueryPrepared(connexion,SQL_INSERT,true,username,password,email);
			int statut = preparedStatement.executeUpdate();
			if(statut == 0)
			{
				throw new DAOException("user creation error , no line was inserted");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if(valeursAutoGenerees.next() )
			{
				user.setId(valeursAutoGenerees.getInt(1));
			}
			else
			{
				throw new DAOException("User creation error in DB , no auto generated ID was returned");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			 closeConnectionItems(preparedStatement,connexion);
		}
	}

	@Override
	public User find(String username,String password) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_SELECT_BY_USERNAME_PASSWORD, false, username,getMd5(password) );
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            user = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	    	closeConnectionItems( resultSet, preparedStatement, connexion );
	    }		

		return user;
	}
	
	public User find(int id) throws DAOException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_SELECT_BY_ID, false,id);
	        resultSet = preparedStatement.executeQuery();
	        if ( resultSet.next() ) {
	            user = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	    	closeConnectionItems( resultSet, preparedStatement, connexion );
	    }		

		return user;
	}
		
	@Override
	public void update(User user) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement  preparedStatement = null;
		
		int id = user.getId();
		String username = user.getUsername();
		String email = user.getEmail();
		String password = getMd5(user.getPassword());
		
		try {
			 connexion = daoFactory.getConnection();
	        preparedStatement =  initQueryPrepared( connexion, SQL_UPDATE, false, username,password,email,id );
	        int statut = preparedStatement.executeUpdate();
	        if(statut  == 0)
	        {
	        	throw new DAOException("user update error , no line was updated");
	        }

		} catch(SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnectionItems(preparedStatement, connexion );
		}
		
	}
	
	public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
	

}
