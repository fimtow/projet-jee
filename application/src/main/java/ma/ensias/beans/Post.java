package ma.ensias.beans;

import java.util.Date;

public class Post {
	
	public static final int IMAGE = 0;
	public static final int TEXT = 1;
	public static final int INVITATION = 2;
	
	private int id;
	private String title;
	private int likes;
	private int dislikes;
	private Date date;
	private Content content;
	private Topic topic;
	private int type;
	private User user;
	
	public Post() {}
	
	public Post(String title,Content content,Topic topic,User user) {
	
		this.title = title;
		this.content = content;
		this.likes = 0;
		this.dislikes = 0;
		this.date = new Date();
		this.user = user;
		this.topic = topic;
		
		if(content instanceof Image)
			type = IMAGE;
		else if(content instanceof Text)
			type = TEXT;
		else
			type = INVITATION;
	}

	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
