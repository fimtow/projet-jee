package ma.ensias.beans;

import java.util.List;

public class User {
	
	private static int NEXTIDVALUE = 0 ;
	
	private int id;
	private String username;
	private String email;
	private List<Topic> topics;
	
	public User() {}
	
	public User(String username, String email) {
		
		this.id = NEXTIDVALUE++;
		this.username = username;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	
}
