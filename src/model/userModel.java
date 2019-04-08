package model;

import java.sql.SQLException;

public class userModel {
	int uid;
	String usertype;
	bookstoreModel model;

	public userModel() throws ClassNotFoundException {
		model = new bookstoreModel();
	}

	public boolean checkPassword(String email, String password) throws SQLException {
		if (password.equals(model.checkPassword(email))) {
			return true;

		}
		return false;
	}

}
