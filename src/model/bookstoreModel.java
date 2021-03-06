package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DAO.AddressDAO;
import DAO.AnalyticsDAO;
import DAO.BookDAO;
import DAO.PODAO;
import DAO.POitemDAO;
import DAO.ReviewDAO;
import DAO.UserDAO;
import DAO.VisitEventDAO;
import beans.AddressBean;
import beans.AnalyticsBean;
import beans.BookBean;
import beans.POBean;
import beans.POitemBean;
import beans.ReviewBean;
import beans.TopTenBean;
import beans.UserBean;
import beans.VisitEventBean;

public class bookstoreModel {
	private AddressDAO addressDAO;
	private BookDAO bookDAO;
	private PODAO poDAO;
	private POitemDAO poitemDAO;
	private ReviewDAO reviewDAO;
	private VisitEventDAO visitEventDAO;
	private AnalyticsDAO analyticsDAO;
	private UserDAO userDAO;
	DataSource ds;
	private ReviewBean rb;

// ___________________________________CONSTRUCTOR____________________________________________
	public bookstoreModel() throws ClassNotFoundException {
		super();

		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		this.addressDAO = new AddressDAO(ds);
		this.bookDAO = new BookDAO(ds);
		this.poDAO = new PODAO(ds);
		this.poitemDAO = new POitemDAO(ds);
		this.reviewDAO = new ReviewDAO(ds);
		this.visitEventDAO = new VisitEventDAO(ds);
		this.userDAO = new UserDAO(ds);

	}
	
	public Map<String, Integer> getReviewBID(Map<String, ReviewBean> rb) {
		Map<String, Integer> rv = new HashMap<String, Integer>();
		for (Map.Entry<String, ReviewBean> pair : rb.entrySet()) {
			rv.put(pair.getValue().getBid(), pair.getValue().getRating());
		}
		return rv;
	}
	
	
	

// ___________________________DISPLAYING THE BOOKS ________________________________________
	public String displayBooks(Map<String, BookBean> rv) throws SQLException {
		String result = "";
		result = "<table>";
		
		if (rv == null) {
			result = result + "<tr><td>Sorry, no books match your search</td></tr>";
		} else {
			for (Map.Entry<String, BookBean> pair : rv.entrySet()) {
			
				//Map<String, ReviewBean> review = getReviewsByBIDMap(pair.getValue().getBid());
					
				result = result + "<tr><td>" + pair.getValue().getBid()
						+ "</td><td><img width=\"100\" height=\"100\" src='" + pair.getValue().getPicture()
						+ "' /></td><td>" + pair.getValue().getTitle() + "</td><td>" + pair.getValue().getPrice()
						+ "</td><td>  <a href=\"Start?currPage=categories&amp;addToCart=" + pair.getValue().getBid()
						+ "\">Add to Cart</a></td>" + "<td>  <a href=\"Start?currPage=categories&amp;addReview="
						+ pair.getValue().getBid() + "\">Add Review</a></td>" + "					<tr>\r\n" + 
								"						<td><label for=\"rating\" style=\"margin-right: 70px\"\r\n" + 
								"							class=\"w3-medium w3-text-red\"><br>\r\n" + 
								"							<font size=\"3\" color=\"#FF0000\"><b>Rate this book: </b></font></br></label></td>\r\n" + 
								"						<td><input type=\"radio\" id=\"rate1\" name=\"rate\" value=\"rate1\"\r\n" + 
								"							style=\"margin: 0 5px 0 5px;\"><label for=\"rate1\">1</label>\r\n" + 
								"							</input>\r\n" + 
								"							<input type=\"radio\" id=\"rate2\" name=\"rate\" value=\"rate2\"\r\n" + 
								"							style=\"margin: 0 5px 0 5px;\"><label for=\"rate2\">2</label>\r\n" + 
								"							</input>\r\n" + 
								"							<input type=\"radio\" id=\"rate3\" name=\"rate\" value=\"rate3\"\r\n" + 
								"							style=\"margin: 0 5px 0 5px;\"><label for=\"rate3\">3</label>\r\n" + 
								"							</input>\r\n" + 
								"							<input type=\"radio\" id=\"rate4\" name=\"rate\" value=\"rate4\"\r\n" + 
								"							style=\"margin: 0 5px 0 5px;\"><label for=\"rate4\">4</label>\r\n" + 
								"							</input>\r\n" + 
								"							<input type=\"radio\" id=\"rate5\" name=\"rate\" value=\"rate5\"\r\n" + 
								"							style=\"margin: 0 5px 0 5px;\"><label for=\"rate5\">5</label>\r\n" + 
								"							</input>\r\n" + 
								"							</td>\r\n"  +
								"					</tr>"  + "</tr>";
				} 
			}
		//System.out.println(result);
		result = result + "</table>";
		return result;
	}
	
	
	
//	//___________________________DISPLAYING THE REVIEWS ________________________________________
//	public String displayReviews(Map<String, ReviewBean> rev) throws SQLException {
//		String result1 = "";
//		result1 = "<table>";
//		
//		if (rev == null) {
//			result1 = result1 + "<tr><td>Sorry, no review exists</td></tr>";
//		} else {
//			for (Map.Entry<String, ReviewBean> pair : rev.get(key)) {
//				System.out.print("set is empty");
//				//Map<String, ReviewBean> review = getReviewsByBIDMap(pair.getValue().getBid());
//					
//				result1 = result1 + "<tr><td>" + pair.getValue().getBid()+ "</td></tr>"
//						+ "<tr><td>" + pair.getValue().getRating() + "</td></tr>";
//				} 
//			
//			}
//		result1 = result1 + "</table>";
//		System.out.println(result1);
//		return result1;
//	}
	
// _________________________ BOOKS___________________________________________________
	public String getBookbyName(String bookName) throws SQLException {
		String result = "";
		Map<String, BookBean> rv = bookDAO.retrieve(bookName);
		result = displayBooks(rv);
		return result;
	}

	public String getBookByBID(String bid) throws SQLException {
		String result = "";
		Map<String, BookBean> rv = bookDAO.retrieveByBID(bid);
		result = displayBooks(rv);
		return result;
	}

	public String getBooks(String category) throws SQLException {
		String result = "";
		Map<String, BookBean> rv = bookDAO.retrieve(category);
		result = displayBooks(rv);
		return result;
	}

	public String getAllBooks() throws SQLException {
		String result = "";
		Map<String, BookBean> rv = bookDAO.retrieveAll();
		result = displayBooks(rv);
		return result;
	}

	public Map<String, BookBean> getByNameMap(String name) throws SQLException {
		return bookDAO.retrieve(name);
	}

	public Map<String, BookBean> getByCategoryMap(String category) throws SQLException {
		return bookDAO.retrieveCategory(category);
	}

	public Map<String, BookBean> retrieveAllMap() throws SQLException {
		return bookDAO.retrieveAll();
	}

	public BookBean getByBIDBean(String bid) throws SQLException {
		Map<String, BookBean> book = bookDAO.retrieveByBID(bid);
		return book.get(bid);
	}

	public String getBYBIDExternal(String bid) throws SQLException {
		String result = "";
		BookBean bean = getByBIDBean(bid);
		if (bean == null) {
			return "(BOOK DOES NOT EXIST)";
		}
		result = "(bid: " + bid + ")  (Book Title:  " + bean.getTitle() + ")    (Book Description:  "
				+ bean.getDescription() + ")  (Price:  " + bean.getPrice() + ")";
		return result;
	}

	public boolean addBook(String bid, String title, String picture, double price, String category, String courseCode,
			String courseTitle, String description) throws SQLException {
		if (bookDAO.retrieveAll().containsKey(bid)) {
			return false;
		} else {
			BookBean book = new BookBean(bid, title, picture, price, category, courseCode, courseTitle, description);
			return bookDAO.addBook(book);
		}
	}

	public boolean updateBook(String bid, String title, String picture, double price, String category,
			String courseCode, String courseTitle, String description) throws SQLException {
		BookBean book = new BookBean(bid, title, picture, price, category, courseCode, courseTitle, description);
		return bookDAO.updateBook(book);
	}

	public boolean removeBook(String bid) throws SQLException {
		return bookDAO.dropBook(bid);
	}

// ____________________________ADDRESS____________________________________________________
	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public Map<String, AddressBean> getAddressByUID(int uid) throws SQLException {
		return addressDAO.retrieve(uid);
	}

	public boolean updateAddress(int id, int uid, String street, String city, String province, String country,
			String zip, String phone) throws SQLException {
		return addressDAO.updateAddress(id, uid, street, city, province, country, zip, phone);
	}

	public boolean addAddress(int id, int uid, String street, String city, String province, String country, String zip,
			String phone, String addressType) throws SQLException {
		AddressBean ab = new AddressBean(id, uid, street, city, province, country, zip, phone, addressType);
		return addressDAO.addAddress(ab);
	}

//_____________________________________GETTING REVIEWS________________________________________

	public Map<String, ReviewBean> getReviewsByBIDMap(String bid) throws SQLException {
	 
		return reviewDAO.getReviews(bid);
	}
	
	public ReviewBean getByReviewIDBean(String bid) throws SQLException {
		Map<String, ReviewBean> book = reviewDAO.getReviews(bid);
		return book.get(bid);
	}


	public boolean addReview(int reviewID, int rating, String bid, int uid, String reviewtext) throws SQLException {
		ReviewBean bean = new ReviewBean(reviewID, rating, bid, uid, reviewtext);
		return reviewDAO.addReview(bean);
	}

	public boolean removeReview(int reviewID) throws SQLException {
		return reviewDAO.remove(reviewID);
	}

//______________________________________PO_____________________________________________________

	public PODAO getPoDAO() {
		return poDAO;
	}

	public POBean getPOByID(int ID) throws SQLException {
		Map<String, POBean> po = poDAO.retrieveByID(ID);
		return po.get(ID + "");
	}

	public Map<String, POBean> getPOByEmail(int uid) throws SQLException {
		return poDAO.retrieveByUID(uid);
	}

	public POBean createPO(int uid, String lname, String fname, String status, int address, String day)
			throws SQLException {
		POBean newPO = new POBean(1, uid, lname, fname, status, address, day);
		int newPOID = poDAO.addPO(newPO);
		newPO.setId(newPOID);
		return newPO;
	}

	public Map<String, POBean> getPOitemsByPO(int poID) throws SQLException {
		return poDAO.retrieveByID(poID);
	}

// _____________________________________POITEMS_______________________________________________

	public POitemDAO getPoitemDAO() {

		return poitemDAO;
	}

	public boolean AddPOItem(int id, String bid, int quantity, double price) throws SQLException {
		POitemBean bean = new POitemBean(id, bid, quantity, price);
		return poitemDAO.addPOItem(bean);
	}

	public Map<String, POitemBean> retreiveAllPOItems() throws SQLException {
		return poitemDAO.retrieve();
	}

	public Map<String, POitemBean> retreivePOItems(int poID) throws SQLException {
		return poitemDAO.retrieveByPO(poID);
	}

//_________________________________________VISIT EVENTS_________________________________________
	public void cartPlus(String bid, int uid) throws SQLException {
		Map<String, VisitEventBean> visitEvents = visitEventDAO.retrieveCartByUID(uid);
		VisitEventBean bean = visitEvents.get(bid + uid);
		visitEventDAO.updateCart(bean.getDay(), bid, uid, bean.getQuantity() + 1);

	}

	public void cartMinus(String bid, int uid) throws SQLException {
		Map<String, VisitEventBean> visitEvents = visitEventDAO.retrieveCartByUID(uid);
		VisitEventBean bean = visitEvents.get(bid + uid);
		if (bean.getQuantity() == 1) {
			visitEventDAO.removeFromCart(bean.getDay(), bid, uid);
		} else {
			visitEventDAO.updateCart(bean.getDay(), bid, uid, bean.getQuantity() - 1);
		}
	}

	public VisitEventDAO getVisitEventDAO() {
		return visitEventDAO;
	}

	public Map<String, VisitEventBean> getAllVisits() throws SQLException {
		return visitEventDAO.retrieve();
	}

	public Map<String, VisitEventBean> getVisitsByUID(int uid) throws SQLException {
		return visitEventDAO.retrieveByUID(uid);
	}

	public Map<String, VisitEventBean> getVisitsCartByUID(int uid) throws SQLException {
		return visitEventDAO.retrieveCartByUID(uid);
	}

//test
	public boolean changeToPurchased(int uid) throws SQLException {
		return visitEventDAO.changeToPurchased(uid);
	}

	public boolean updateQuantity(String day, String bid, int uid, int quantity) throws SQLException {
		return visitEventDAO.updateCart(day, bid, uid, quantity);
	}

	public boolean removeFromCart(String day, String bid, int uid) throws SQLException {

		return visitEventDAO.removeFromCart(day, bid, uid);
	}

	public boolean addToCart(String day, String bid, int uid, String eventtype, int quantity, double price)
			throws SQLException {
		VisitEventBean bean = new VisitEventBean(day, bid, uid, eventtype, quantity, price);
		return visitEventDAO.addToCart(bean);
	}
	
	//NEED TO WORK ON THIS AT HOME
	
	public String displayReviews(String bid) throws SQLException {
		System.out.println("I can get here too");
		Map<String, ReviewBean> review = this.getReviewsByBIDMap(bid);
		String reviewResult = "<table>";

		//NumberFormat formatter = NumberFormat.getCurrencyInstance();
		if (review.size() == 0) {
			reviewResult = "<b> NO REVIEWS </b>";
		} else {
			for (Map.Entry<String, ReviewBean> pair : review.entrySet()) {
				String bookid = pair.getValue().getBid();
				ReviewBean bean = this.getByReviewIDBean(bookid);
				int rating = bean.getRating();
				//System.out.println(rating);
				reviewResult = reviewResult + "<tr><td colspan = \"2\">" + bean.getBookRating(bookid, rating ) + "</td></tr>" 
											+ "<tr><td>" + rating + "</td></tr>";
			}
			reviewResult = reviewResult + "</table>";
		}
		System.out.println(reviewResult);
		return reviewResult;
	}
	
	public String displayCart(int uid) throws SQLException {
		Map<String, VisitEventBean> cart = this.getVisitsCartByUID(uid);
		String result = "<table>";

		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		if (cart.size() == 0) {
			result = "<b> YOUR CART IS EMPTY </b>";
		} else {
			for (Map.Entry<String, VisitEventBean> pair : cart.entrySet()) {
				String bid = pair.getValue().getBid();
				BookBean bean = this.getByBIDBean(bid);
				String title = bean.getTitle();
				String image = bean.getPicture();
				String description = bean.getDescription();
				String price = formatter.format(pair.getValue().getPrice());
				String quantity = pair.getValue().getQuantity() + "";
				result = result + "<tr><td colspan = \"2\">" + title + "</td></tr>";
				result = result + "<tr><td><img height=\"100\" width=\"100\" src=\"" + image + "\"></td><td><p>"
						+ description + "</td></tr>";
				result = result + "<tr><td>Price: " + price
						+ "</td><td>Quantity: <a href=\"Start?currPage=cart&amp;adjust=plus&amp;bid=" + bean.getBid()
						+ "\"><b>+</b></a> " + quantity + " <a href=\"Start?currPage=cart&amp;adjust=minus&amp;bid="
						+ bean.getBid() + "\"><b>-</b></a></td></tr>";
			}
			result = result + "</table>";
		}
		return result;
	}

	public String displayCartSimple(int uid) throws SQLException {
		Map<String, VisitEventBean> cart = this.getVisitsCartByUID(uid);
		String result = "<table>";

		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		if (cart.size() == 0) {
			result = "<b> YOUR CART IS EMPTY </b>";
		} else {
			for (Map.Entry<String, VisitEventBean> pair : cart.entrySet()) {
				String bid = pair.getValue().getBid();
				BookBean bean = this.getByBIDBean(bid);
				String title = bean.getTitle();
				String image = bean.getPicture();
				String price = formatter.format(pair.getValue().getPrice());
				String quantity = pair.getValue().getQuantity() + "";
				result = result + "<tr><td colspan = \"3\">" + title + "</td></tr>";
				result = result + "<tr><td><img height=\"100\" width=\"100\" src=\"" + image + "\"></td><td>Price: "
						+ price + "</td><td>Quantity:  " + quantity + " </td></tr>";
			}
			result = result + "</table>";
		}
		
		return result;
	}
//_______________________________________ANALYTICS__________________________________________________

	public Map<String, BookBean> getTopTen() throws SQLException {
		Map<String, BookBean> allBooks = bookDAO.retrieveAll();
		Map<String, BookBean> books = new HashMap<String, BookBean>();
		Map<String, TopTenBean> topTen = poitemDAO.retrieveTen();
		for (int i = 1; i <= topTen.size(); i++) {
			books.put(i + "", allBooks.get(topTen.get(i + "").getBid()));
		}
		return books;
	}

	public Map<String, BookBean> getByDates(Date d1, Date d2) throws SQLException {
		Map<String, BookBean> allBooks = bookDAO.retrieveAll();
		Map<String, BookBean> books = new HashMap<String, BookBean>();
		Map<String, TopTenBean> topTen = poDAO.retrieveBetweenDates(d1, d2);
		for (int i = 1; i <= topTen.size(); i++) {
			books.put(i + "", allBooks.get(topTen.get(i + "").getBid()));
		}

		return books;
	}

	public Map<String, AnalyticsBean> getAnalytics() throws SQLException {
		Map<String, AnalyticsBean> bean = analyticsDAO.retrieve();

		return bean;
	}

	// ____________________________________USER__________________________________

	public String checkPassword(String email) throws SQLException {
		return userDAO.getPassword(email);
	}

	public boolean addUser(String fname, String lname, String email, String password, String privilege)
			throws SQLException {

		UserBean user = new UserBean(0, fname, lname, email, password, privilege);
		return userDAO.addUser(user);
	}

	public UserBean getUser(int UID) throws SQLException {
		return userDAO.getUserBean(UID);
	}

	public AddressBean getShippingID(int uid) throws SQLException {
		Map<String, AddressBean> addresses = addressDAO.retrieve(uid);
		AddressBean bean = null;
		if (addresses.size() > 0) {
			for (Map.Entry<String, AddressBean> pair : addresses.entrySet()) {
				bean = pair.getValue();
				if (bean.getAddressType().equals("Shipping")) {
					break;
				}
			}
		}
		return bean;
	}

//TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING 
	public void test() throws SQLException, ClassNotFoundException, IOException {
//		System.out.println(this.getAllBooks());
//		System.out.println(this.getBookbyName("Keyboard"));
//		System.out.println(this.getBookbyName("Mouse"));
//		System.out.println(this.getBookByBID("b001"));
//		System.out.println(this.addBook("b105", "Adventures of an IT Leader", "./res/images/it.jpg", 215.95, "EECS1", "431",
//				"IT Leadership", "good book"));
//
//		System.out.println(this.getBookByBID("b105"));
//		System.out.println(this.updateBook("b105", "Adventures of an IT Freak", "./res/images/it.jpg", 215.95, "EECS1", "431",
//				"IT Leadership", "good book"));
//
//		System.out.println(this.getBookByBID("b105"));
//		System.out.println(this.updateBook("b105", "Adventures of an IT Leader", "./res/images/it.jpg", 215.95, "EECS1", "431",
//				"IT Leadership", "good book"));
//		System.out.println(this.getBookByBID("b105"));
//		System.out.println("DROP BOOK=" + this.removeBook("b105"));
//		System.out.println("PO ADDED.  PO# is: "
//				+ this.createPO("test1@mailcatch.com", "Test1", "Admin", "DENIED", 2, "20191002"));
//		Map<String, BookBean> TopTen = this.getTopTen();
//		System.out.println(TopTen.size());
//		for (int i = 1; i <= TopTen.size(); i++) {
//			BookBean book = TopTen.get(i + "");
//			System.out.println(book.getTitle());
//
//		}
//		Date d1 = new Date();
//		Date d2 = new Date();
//		d2.setTime(0);
//		Map<String, BookBean> TopTen2 = this.getByDates(d1, d2);
//		System.out.println(TopTen2.size());
//		for (int i = 1; i <= TopTen2.size(); i++) {
//			BookBean book = TopTen2.get(i + "");
//			System.out.println(book.getTitle());
//
//		}
//
//		String myPassword = "testing123";
//		String salt = PasswordUtils.getSalt(1000);
//		String encPassword = PasswordUtils.generateSecurePassword(myPassword, salt);
//		System.out.println("SALT   " + salt);
//		System.out.println("MyPWORD: " + myPassword);
//		System.out.println("EncrPWD: " + encPassword);
//		System.out.println("Checking password '123': " + PasswordUtils.verifyUserPassword("123", encPassword, salt));
//		System.out.println(
//				"Checking password 'testing123': " + PasswordUtils.verifyUserPassword("testing123", encPassword, salt));
//		System.out.println(this.displayCart(1));

	}

	public void addToCartPlus(String bid, int uid) throws SQLException {
		Map<String, VisitEventBean> visitEvents = null;
		try {
			visitEvents = visitEventDAO.retrieveCartByUID(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ((visitEvents.size() > 0) && (!(visitEvents.get(bid + uid) == null))) {
			VisitEventBean bean = visitEvents.get(bid + uid);

			visitEventDAO.updateCart(bean.getDay(), bid, uid, bean.getQuantity() + 1);

		} else {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String dateString = format.format(new Date());
			VisitEventBean bean = new VisitEventBean(dateString, bid, uid, "cart", 1, bookDAO.getPrice(bid));
			visitEventDAO.addToCart(bean);
		}

	}

	public UserBean getUserFromEmail(String email) throws SQLException {
		return userDAO.getUserBean(email);

	}

	public void changeUsersVisitEvent(int uid, int uid2) throws SQLException {
		visitEventDAO.changeUserVisit(uid, uid2);

	}

//TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING 

}
