package javabeans;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Post {
	
	private static int NEXTIDVALUE = 0;
	
	private int id;
	private String title;
	private int likes;
	private int dislikes;
	private Date date;
	private Content content;
	private Topic topic;
	private List<Comment> comments;
	private User user;
	
	public Post() {}
	
	public Post(String title,Content content,Topic topic,User user) {
	
		this.id = NEXTIDVALUE++;
		this.title = title;
		this.content = content;
		this.likes = 0;
		this.dislikes = 0;
		this.date = new Date();
		this.user = user;
		this.topic = topic;
		comments = new LinkedList<>();
	}

	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
