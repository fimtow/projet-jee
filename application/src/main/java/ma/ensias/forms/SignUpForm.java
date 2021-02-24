package ma.ensias.forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.UserDao;



public final class SignUpForm {
	
    private static final String  USERNAME_FIELD    = "username";
    private static final String  PASSWORD_FIELD   = "password";
    private static final String EMAIL_FIELD = "email";
    

    private boolean              result;
    private Map<String, String>  errors = new HashMap<String, String>();

    public Map<String, String> geterrors() {
        return errors;
    }

    public Boolean getResult() {
        return result;
    }

    public User createUser( HttpServletRequest request ) {
    	
        String username = getFieldValue( request, USERNAME_FIELD );
        String password = getFieldValue( request, PASSWORD_FIELD );
        String email = getFieldValue( request, EMAIL_FIELD );
      
        User user = new User();
       
        try {
        	checkUsernameinDataBase(username);
        } catch ( Exception e ) {
            setErreur( USERNAME_FIELD, e.getMessage() );
        }
        try {
        	checkEmailinDataBase(email);
        } catch ( Exception e ) {
            setErreur( EMAIL_FIELD, e.getMessage() );
        }
        
        

        if ( errors.isEmpty() ){
        	
            result = true;
            user.setUsername( username );
            user.setEmail( email );
            user.setPassword(password);
            DAOFactory daoFactory = DAOFactory.getInstance();
        	UserDao userDao = daoFactory.getUserDao();
        	userDao.create(user);
            
        } else {
            result = false;
        }

        return user;
    }
    /*
     *  Method to check if the username exists in BD
     */

    private void checkUsernameinDataBase(String username) throws Exception {
    	DAOFactory daoFactory = DAOFactory.getInstance();
    	UserDao userDao = daoFactory.getUserDao();
    	
        if ( userDao.find(username,UserDao.USERNAME) != null ) {
        	
        	throw new Exception( "The username is used , Please chose another one" );
        } 
        
    }
    private void checkEmailinDataBase(String email) throws Exception {
    	DAOFactory daoFactory = DAOFactory.getInstance();
    	UserDao userDao = daoFactory.getUserDao();
    	
        if ( userDao.find(email,UserDao.EMAIL) != null ) {
        	
        	throw new Exception( "The email is used , Please chose another one" );
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
