package ma.ensias.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.UserDaoImpl;


public final class SignUp {
	
    private static final String  USERNAME_FIELD    = "username";
    private static final String  PASSWORD_FIELD   = "password";
    private static final String EMAIL_FIELD = "email";
    

    private String              result;
    private Map<String, String>  errors = new HashMap<String, String>();

    public Map<String, String> geterrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }

    public User createUser( HttpServletRequest request ) {
    	
        String username = getFieldValue( request, USERNAME_FIELD );
        String password = getFieldValue( request, PASSWORD_FIELD );
        String email = getFieldValue( request, EMAIL_FIELD );
      
        User user = new User();
       
        try {
        	checkUserinDataBase(username);
        } catch ( Exception e ) {
            setErreur( USERNAME_FIELD, e.getMessage() );
        }
        
        

        if ( errors.isEmpty() ){
        	
            result = "User created successfully.";
            user.setUsername( username );
            user.setEmail( email );
            user.setPassword(password);
            DAOFactory daoFactory = DAOFactory.getInstance();
        	UserDaoImpl userDao = (UserDaoImpl)daoFactory.getUserDao();
        	userDao.create(user);
            
        } else {
            result = "Errors occurred";
        }

        return user;
    }
    /*
     *  Method to check if the username exists in BD
     */

    private void checkUserinDataBase(String username) throws Exception {
    	DAOFactory daoFactory = DAOFactory.getInstance();
    	UserDaoImpl userDao = (UserDaoImpl)daoFactory.getUserDao();
    	System.out.println(userDao.find(username));
        if ( userDao.find(username) != null ) {
        	
        	throw new Exception( "The user name exist , Please chose another one" );
        } 
    }

    /*
     * Add message and the field generating the error.
     */
    private void setErreur( String field, String message ) {
        errors.put( field, message );
    }

    /*
     * returns null if field is empty , else the value of the field
     * 
     */
    private static String getFieldValue( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
