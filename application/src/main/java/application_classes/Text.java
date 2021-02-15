package application_classes;

public class Text extends Content{
	
	private String text;

	public Text(String text) {
	
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

}
