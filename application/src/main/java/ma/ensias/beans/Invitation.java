package ma.ensias.beans;

import java.util.Date;

public class Invitation extends Content {
	
	
	private String description;
	private String location;
	private Date date;
	private int joined;

	public Invitation(String description,Date date,String location,int idTopic) {
		
		this.joined = 0;
		this.description = description;
		this.date = date;
		this.location = location;
		this.setPostId(idTopic);
	}
	public Invitation() {
		
		this.joined = 0;
		
	}

	public int getJoined() {
		return joined;
	}

	public void setJoined(int joined) {
		this.joined = joined;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	

}
