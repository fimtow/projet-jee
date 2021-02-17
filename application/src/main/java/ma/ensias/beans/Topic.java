package ma.ensias.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Topic {
	
	private int id;
	private String title;
	private String description;
	private String iconUrl;
	private String coverUrl;
	private Map<Integer,Boolean> members;
	
	public Topic(){}
	
	public Topic(String title, String description, String iconUrl, String coverUrl,User user)
	{
		
		this.title = title;
		this.description = description;
		this.iconUrl = iconUrl;
		this.coverUrl = coverUrl;
		this.members = new HashMap<>();
		this.members.put(user.getId(),true); // the creator is moderator
	}

	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) 
	{
		this.iconUrl = iconUrl;
	}
	public String getCoverUrl() 
	{
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) 
	{
		this.coverUrl = coverUrl;
	}

	public Map<Integer,Boolean> getMembers() {
		return members;
	}

	public void setMembers(Map<Integer,Boolean> members) {
		this.members = members;
	}

	
	

}
