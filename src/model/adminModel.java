package model;

import java.io.IOException;
import java.sql.SQLException;

import beans.UserBean;

public class adminModel {
	bookstoreModel bModel;
	UserBean adminUser;
	userModel uModel;
	boolean loggedIn;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public adminModel() throws ClassNotFoundException, IOException {
		uModel = new userModel();
		bModel = uModel.getbookStoreModel();
		loggedIn = false;
	}

	public boolean checkID(String email, String pword) throws SQLException {
		UserBean user = bModel.getUserFromEmail(email);
		boolean validPassword = false;
		boolean validID = false;
		if (user == null) {
			return false;
		} else {
			validPassword = PasswordUtils.verifyUserPassword(pword, user.getPassword(), uModel.ioGetSalt());
			validID = (user.getPrivilege().equals("Admin"));
		}
		if (validPassword && validID) {
			adminUser = user;
			loggedIn = true;
			uModel.setUser(user);
			return true;
		}
		return false;
	}
}
