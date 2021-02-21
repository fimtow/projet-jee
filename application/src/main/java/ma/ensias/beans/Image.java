package ma.ensias.beans;

public class Image extends Content {
	
	private String url;
	

	public Image(String url,int idPost) {
		setId(idPost);
		this.url = url;
	}
	
	public Image()
	{
		
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

}
