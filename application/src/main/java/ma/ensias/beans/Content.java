package ma.ensias.beans;

public abstract class Content {
	private int id;
	private Post idPost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Post getidPost() {
		return idPost;
	}

	public void setPost(Post idPost) {
		this.idPost = idPost;
	}
}
