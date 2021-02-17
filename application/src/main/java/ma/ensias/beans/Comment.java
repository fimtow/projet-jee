package ma.ensias.beans;

import java.util.Date;

public class Comment {
		
	private int id;
	private String text;
	private int likes;
	private int dislikes;
	private Date date;
	private User user;
	private Post post;
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment(String text, User user, Post post) {
		
		this.text = text;
		this.user = user;
		this.likes = this.dislikes = 0;
		this.post = post;
		this.date = new Date();
	}
	
	public Comment()
	{
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
