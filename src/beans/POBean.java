package beans;

public class POBean {
	private int id;
	private String email;
	private String lname;
	private String fname;
	private String status;
	private int address;
	private String day;

	public POBean(int id, String email, String lname, String fname, String status, int address, String day) {
		super();
		this.id = id;
		this.email = email;
		this.lname = lname;
		this.fname = fname;
		this.status = status;
		this.address = address;
		this.day = day;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

}
