package ma.ensias.beans;

public class Text extends Content{
	
	private String text;

	public Text(String text) {
		this.text = text;
	}
	
	public Text()
	{
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

}
