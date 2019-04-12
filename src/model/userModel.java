package model;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import beans.AddressBean;
import beans.POBean;
import beans.UserBean;
import beans.VisitEventBean;

public class userModel {
	int uid;
	String usertype;
	bookstoreModel model;
	String salt;
	UserBean user;

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) throws SQLException {
		this.user = user;
		model.changeUsersVisitEvent(uid, user.getUid());
		uid = user.getUid();
	}

	public String getSalt() {
		return salt;
	}

	public userModel() throws ClassNotFoundException, IOException {

		uid = (int) (Math.random() * 1000000000) + 1000000000; // Set a blank user;
		model = new bookstoreModel();
		salt = ioGetSalt();
	}

	public String ioGetSalt() {
		String salty = "Fn6qsAc8FWxWUmlEOIliO6EscUAWMEh9bT55HlllQIRVU5AeRcJYN2fWdOrNzNePVCgorivXhgnJIEpMK30XjisrRcKlaBzMKlqHiIsa5GOD6xWPGVlGDjaYyAWSWhxlBMSPFHHhhxLw6Bx3QVuKYYM0PCBXgbhCGQaZ6sWMIskymz8JQ6tw9oqasfesOdtJUmCyyWtnIhJirOMGot5IJqLvuTY5Q7zmoL9H6I9Cstu4MYPfrCJn4gzkQK3ADE4SNbMeVVsHstX6dZxnWyL3gFDOlepVVShbilw2ZlehHbX8jdmR3GEV69cgNx1AVO1a0h3orLbmGLHNpSAQzsep9B6OIj1VpkcfkfL5HVyqpEoSW5kdUUq1MG9vKi2dlgkFvSNSSOefLH2pOV9UtQBejBTwqCafLCVs8TmXVyvSW18IGj0o3u5sr4YI8a8zyAAA7bowlmN3877ci0cqpPZ443tqV3tAMO9PajU8jl8pIgTc4TmI8jwK6kU6duiHa8qOHnnICGNEH6IHjVMqHW4zF6UfCn0xZR3YHtHBJSQwF5UiLWG0EHXS3ycSm1CP5Lf1JPVO5fpWvjTrrfpjuhSWYr90y0YN1tL9KeHx03a32ekjenTxfLLMNX7cHmq8DulzzQRmUy1BIlq6hEmibEmzAEnhcKEEpXH63ZfhAUWEdxDafomAZnRXwBwQM75U4tnpzCL79B6TSIAR5jUXEmMxobFxCJSxfhQwkTX4TpZxfr49LHMc0IsNWvoGBWNtPlAZKPJ18Va5VK7NmxvkgvBfYWDZERFOm3V0ukrxP366ECEG47ESqy27lqAMH6LfAGmK8NnRfwWBF3hXCyL0wfvcRGS7lQPYghTs8lswLZtctRu5vMSLQ4PK4dtFOuoTmilaAjmjNBttzMqBU3Thnatb0rN1fRfGDTjOKlnXckNEYAk7NhmI4NnosHDDLWjc3g8vv00W5O97D2nHAnztnXrAvK7aNUh8WW9j7lSShZfs";
		return salty;
	}

	public boolean checkPassword(String email, String password) throws SQLException {

		return (PasswordUtils.verifyUserPassword(password, model.checkPassword(email), salt));

	}

	public boolean createUser(String fname, String lname, String email, String password, String privilege)
			throws SQLException {
		boolean created = false;
		String encPassword = PasswordUtils.generateSecurePassword(password, salt);
		try {
			created = model.addUser(fname, lname, email, encPassword, privilege);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.user = model.getUserFromEmail(email);
		this.uid = user.getUid();

		return created;
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

	public POBean createPO() {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dateString = format.format(new Date());
		POBean poID = null;
		AddressBean address = null;

		try {
			address = model.getShippingID(this.uid);
			poID = model.createPO(user.getUid(), user.getLname(), user.getFname(), "ORDERED", address.getId(),
					dateString);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return poID;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public void cartPlus(String bid) throws SQLException {
		model.cartPlus(bid, uid);

	}

	public void cartMinus(String bid) throws SQLException {
		model.cartMinus(bid, uid);

	}

	public void addToCart(String bid) throws SQLException {
		model.addToCartPlus(bid, uid);

	}
}
