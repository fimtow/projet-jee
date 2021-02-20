package ma.ensias.forms;


import javax.servlet.http.HttpServletRequest;

import ma.ensias.beans.User;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.UserDao;

public final class ConnexionForm {
	
    private static final String USERNAME_FIELD  = "username";
    private static final String PASSWORD_FIELD   = "password";

    private boolean success = true;

    public boolean getResult() {
        return success;
    }


    public User connectUser( HttpServletRequest request ) {
        /* get values from form */
        String username = getFieldValue( request, USERNAME_FIELD );
        String password = getFieldValue( request, PASSWORD_FIELD );
        
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDao userDao = daoFactory.getUserDao();
        
        User user = userDao.find(username, password);
        if(user == null)
        	success = false;
        return user;
    }


    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }
}
