package woodspring.springat8.model;

import java.util.Random;

public class NodeValue {
	
	private String firstname;
	private String lastname;
	private int phone;
	private String status;
	
	public NodeValue( String firstname, String lastName, String status) {
		this.firstname = firstname;
		this.lastname = lastName;
		this.status = status;
		Random rand = new Random(1000000000);
		this.phone = rand.nextInt();		
	}
	@Override
	public String toString() {
		return "NodeValue [firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone + ", status="
				+ status + "]";
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
