package ma.ensias.beans;

import java.util.Date;

public class Event extends Topic {
	
	private String location;
	private Date date;
	
	public Event() {}

	public Event(String title,String description,String iconUrl,String coverUrl,String location, Date date) {
		super(title,description,iconUrl,coverUrl);
		this.location = location;
		this.date = date;
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
