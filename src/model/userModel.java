package model;

import java.sql.SQLException;

public class userModel {
	int uid;
	String usertype;
	bookstoreModel model;
	String fname;
	String email;

	public userModel() throws ClassNotFoundException {
		model = new bookstoreModel();
	}

	public boolean checkPassword(String email, String password) throws SQLException {
		if (password.equals(model.checkPassword(email))) {
			return true;

		}
		return false;
	}

//to return the bookstore model
	public bookstoreModel getbookStoreModel() {
		return model;
	}
}
