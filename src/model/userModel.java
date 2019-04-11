package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	String fname;
	String lname;
	String email;
	String salt;

	public String getSalt() {
		return salt;
	}

	public userModel() throws ClassNotFoundException, IOException {

		uid = 2;// MUST CHANGE
				// LATER____________________________________________________________________________***
		model = new bookstoreModel();
	//	salt = ioGetSalt();
	}

	public String ioGetSalt() {
		FileReader fr;
		BufferedReader br = null;

		try {
			fr = new FileReader("salty.txt");
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found!!");
		}

		String salty = "";
		try {
		//	salty = br.readLine();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(salty);

		return salty;

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

	public POBean createPO() {
		UserBean user = null;

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dateString = format.format(new Date());
		POBean poID = null;
		AddressBean address = null;
		try {
			user = model.getUser(this.uid);
			try {
				address = model.getShippingID(this.uid);
				poID = model.createPO(user.getUid(), user.getLname(), user.getFname(), "ORDERED", address.getId(),
						dateString);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
