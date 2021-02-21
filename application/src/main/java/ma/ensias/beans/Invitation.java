package ma.ensias.beans;

public class Invitation extends Content {
	
	private int joined;

	public Invitation(int idPost) {
		setId(idPost);
		this.joined = 0;
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
	
	
	
	

}
