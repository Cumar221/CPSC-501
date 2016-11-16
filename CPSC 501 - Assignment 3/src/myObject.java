import java.io.Serializable;

@SuppressWarnings("serial")
public class myObject implements Serializable{
	public String firstName;
	public String lastName;
	public String middleName;
	public int 	dob;
	public char[] str = {'h','e','l','l','o'};
	public secondObject sObj = new secondObject(5);
	public secondObject[] objArray = new secondObject[10];
	
	public myObject(String newFN, String newLN, String newMN, int newDOB){
		this.firstName = newFN;
		this.lastName = newLN;
		this.middleName = newMN;
		this.dob = newDOB;
		
		for (int i = 0; i < 10; i++){
			objArray[i] = new secondObject(i*2);
		}

	}
	
	
}
