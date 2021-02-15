package ma.ensias.beans;

public class Topic {
	
	private static int NEXTIDVALUE = 0 ;
	
	private int id;
	private String title;
	private String description;
	private String iconUrl;
	private String coverUrl;
	
	public Topic(){}
	
	public Topic(String title, String description, String iconUrl, String coverUrl)
	{
		
		this.id = NEXTIDVALUE++;
		this.title = title;
		this.description = description;
		this.iconUrl = iconUrl;
		this.coverUrl = coverUrl;
	}

	public Topic(int id, String title, String description)
	{
		
		this.id = NEXTIDVALUE++;
		this.title = title;
		this.description = description;
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
	

}
