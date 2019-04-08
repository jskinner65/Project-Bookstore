package model;

import java.sql.SQLException;

public class userModel {
	int uid;
	String usertype;
	bookstoreModel model;
	String fname;
	String email;
	String salt;

	public userModel() throws ClassNotFoundException {

		uid = 1;// MUST CHANGE
				// LATER____________________________________________________________________________***
		model = new bookstoreModel();
		salt = PasswordUtils.getSalt(50);
	}

	public boolean checkPassword(String email, String password) throws SQLException {

		return (PasswordUtils.verifyUserPassword(password, model.checkPassword(email), salt));

	}

	public boolean createUser(String fname, String lname, String email, String password, String privilege)
			throws SQLException {
		String encPassword = PasswordUtils.generateSecurePassword(password, salt);
		return model.addUser(fname, lname, email, encPassword, privilege);

	}

	public String getCart() throws SQLException {
		return model.displayCart(this.uid);
	}

//to return the bookstore model
	public bookstoreModel getbookStoreModel() {
		return model;
	}
}
