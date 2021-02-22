package ma.ensias.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private static final String FICHIER_PROPERTIES       = "ma/ensias/dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";

    private static DAOFactory single_instance = null;
    
    private String              url;
    private String              username;
    private String              password;

    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {
    	if(single_instance == null)
    	{
	        Properties properties = new Properties();
	        String url;
	        String nomUtilisateur;
	        String motDePasse;
	
	        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
	
	        if ( fichierProperties == null ) {
	            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
	        }
	
	        try {
	            properties.load( fichierProperties );
	            url = properties.getProperty( PROPERTY_URL );
	            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
	            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
	        } catch ( IOException e ) {
	            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
	        }
	        single_instance = new DAOFactory( url, nomUtilisateur, motDePasse );
    	}

        return single_instance;
    }


	Connection getConnection() throws SQLException 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return DriverManager.getConnection( url, username, password );
    }

    public BeanTestDao getBeanTestDao() {
        return new BeanTestDaoImpl( this );
    }
    
    public CommentDao getCommentDao()
    {
    	return new CommentDaoImpl( this );
    }

    public TextDao getTextDao()
    {
    	return new TextDaoImpl( this );
    }

    public ImageDao getImageDao()
    {
    	return new ImageDaoImpl( this );
    }

    public InvitationDao getInvitationDao()
    {
    	return new InvitationDaoImpl( this );
    }
    
    public EventDao getEventDao()
    {
    	return new EventDaoImpl( this );
    }
    
    public MemberDao getMemberDao()
    {
    	return new MemberDaoImpl( this );
    }
    
    public PostDao getPostDao()
    {
    	return new PostDaoImpl( this );
    }
    
    public TopicDao getTopicDao()
    {
    	return new TopicDaoImpl( this );
    }
    
    public UserDao getUserDao()
    {
    	return new UserDaoImpl( this );
    }
    
    public PostLikeDao getPostLikeDao()
    {
    	return new PostLikeDaoImpl( this );
    }
    
    public CommentLikeDao getCommentLikeDao()
    {
    	return new CommentLikeDaoImpl( this );
    }
    
}
