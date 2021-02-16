package ma.ensias.beans;

public abstract class Content {
	private static int NEXTIDVALUE = 0 ;
	
	private int id;
	
	public Content()
	{
		this.id = NEXTIDVALUE++;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
