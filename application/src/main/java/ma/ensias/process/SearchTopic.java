package ma.ensias.process;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.ensias.beans.Topic;
import ma.ensias.dao.DAOFactory;
import ma.ensias.dao.TopicDao;

public class SearchTopic {
	
	public boolean success = false; 
	public List<Topic> listOfTopics;
	
	public SearchTopic(HttpServletRequest request)
	{
		String nameTyped = getFieldValue(request,"topic");
		if(nameTyped == null)
			return;
		
		TopicDao topicDao = DAOFactory.getInstance().getTopicDao();
		listOfTopics = topicDao.find(nameTyped);
		if(listOfTopics.size() > 0)
		{
			success = true;
			for(Topic topic : listOfTopics)
			{
				topic.setMembers(null);
			}
		}
		
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
