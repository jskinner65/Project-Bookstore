package beans;

public class UserBean {

	private int uid;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String privilege;

	public UserBean(int uid, String fname, String lname, String email, String password, String privilege) {
		super();
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.privilege = privilege;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

}
