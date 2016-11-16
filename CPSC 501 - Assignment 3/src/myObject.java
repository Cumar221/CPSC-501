import java.io.Serializable;

@SuppressWarnings("serial")
public class myObject implements Serializable{
	public String name;
	
	public myObject(String newName) {
		this.name = newName;
	}
}
