package ma.ensias.beans;

public class Text extends Content{
	
	private String text;
	
	public Text()
	{
		super();
	}


	public Text(String text) {
		super();
		this.text = text;
	}
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

}
