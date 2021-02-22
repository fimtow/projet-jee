package ma.ensias.beans;

public class Invitation extends Content {
	
	
	private String description;
	private int joined;

	public Invitation(String description) {
		
		this.joined = 0;
		this.description = description;
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
	
	
	
	

}
