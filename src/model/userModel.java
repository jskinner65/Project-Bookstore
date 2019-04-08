package model;

import java.sql.SQLException;
import java.util.Map;

import beans.VisitEventBean;

public class userModel {
	int uid;
	String usertype;
	bookstoreModel model;
	String fname;
	String email;
	String salt;

	public userModel() throws ClassNotFoundException {

		uid = 2;// MUST CHANGE
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

	public String getCartSimple() throws SQLException {
		return model.displayCartSimple(uid);
	}

	public String getCart() throws SQLException {
		return model.displayCart(this.uid);
	}

//to return the bookstore model
	public bookstoreModel getbookStoreModel() {
		return model;
	}

	public int getCartSize() throws SQLException {
		Map<String, VisitEventBean> allbeans = model.getVisitsCartByUID(this.uid);
		return allbeans.size();
	}

	public double getSubtotal() throws SQLException {
		Map<String, VisitEventBean> allbeans = model.getVisitsCartByUID(this.uid);
		double subtotal = 0;
		for (Map.Entry<String, VisitEventBean> pair : allbeans.entrySet()) {
			subtotal += pair.getValue().getPrice() * pair.getValue().getQuantity();
		}
		return subtotal;
	}
}
