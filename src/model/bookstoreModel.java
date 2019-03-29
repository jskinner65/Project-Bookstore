package model;

import java.sql.SQLException;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DAO.AddressDAO;
import DAO.BookDAO;
import DAO.PODAO;
import DAO.POitemDAO;
import DAO.VisitEventDAO;
import DAO.ReviewDAO;
import beans.AddressBean;
import beans.BookBean;
import beans.POBean;
import beans.ReviewBean;

public class bookstoreModel {
	private AddressDAO addressDAO;
	private BookDAO bookDAO;
	private PODAO poDAO;
	private POitemDAO poitemDAO;
	private VisitEventDAO visitEventDAO;
	private ReviewDAO reviewDAO;
	DataSource ds;

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
		this.visitEventDAO = new VisitEventDAO(ds);

	}

// ___________________________DISPLAYING THE BOOKS ________________________________________
	public String displayBooks(Map<String, BookBean> rv) {
		String result = "";
		result = "<table>";
		if (rv == null) {
			result = result + "<tr><td>Sorry, no books match your search</td></tr>";
		} else {
			for (Map.Entry<String, BookBean> pair : rv.entrySet()) {

				result = result + "<tr><td>" + pair.getValue().getBid() + "</td><td><img src='"
						+ pair.getValue().getPicture() + "' /></td><td>" + pair.getValue().getTitle() + "</td><td>"
						+ pair.getValue().getPrice() + "</td></tr>";
			}
		}
		result = result + "</table>";
		return result;
	}

	//Added by Mash for displaying reviews	
		public String displayReviews(Map<String, ReviewBean> rv) {
			String result = "";
			result = "<table>";
			for (Map.Entry<String, ReviewBean> pair : rv.entrySet()) {

				result = result + "<tr><td>" + pair.getValue().getBid() + "</td><td>" + pair.getValue().getReviewtext()
						+ "</td></tr>";
			}
			result = result + "</table>";
			return result;
		}	
	
	
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
		Map<String, BookBean> rv = bookDAO.retrieve("");
		result = displayBooks(rv);
		return result;
	}

	public String getAllBooks() throws SQLException {
		String result = "";
		Map<String, BookBean> rv = bookDAO.retrieveAll();
		result = displayBooks(rv);
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

// ____________________________ADDRESS____________________________________________________
	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public boolean addAddress(int id, String email, String street, String province, String country, String zip,
			String phone) throws SQLException {
		AddressBean ab = new AddressBean(id, email, street, province, country, zip, phone);
		return addressDAO.addAddress(ab);
	}

//_____________________________________GETTING REVIEWS________________________________________
	public String getReviews(String bid) throws SQLException {
		String result = "";
		Map<String, ReviewBean> rv = reviewDAO.getReviews(bid);
		result = displayReviews(rv);
		return result;
	}

//______________________________________PO_____________________________________________________

	public PODAO getPoDAO() {
		return poDAO;
	}

// _____________________________________POITEMS_______________________________________________
	public int addPO(String email, String lname, String fname, String status, int address, String day)
			throws SQLException {
		int id = 0;
		POBean po = new POBean(id, email, lname, fname, status, address, day);
		return poDAO.addPO(po);

	}

	public POitemDAO getPoitemDAO() {

		return poitemDAO;
	}

//______________________________________VISIT EVENTS_________________________________________
	public VisitEventDAO getVisitEventDAO() {
		return visitEventDAO;
	}

//TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING 
	public void test() throws SQLException {
		System.out.println(this.getAllBooks());
		System.out.println(this.getBookbyName("How to use a Mou"));
		System.out.println(this.getBookByBID("b001"));
		System.out.println(this.addBook("b105", "Adventures of an IT Leader", "./res/it.jpg", 215.95, "EECS1", "431",
				"IT Leadership", "good book"));

		System.out.println(this.getBookByBID("b105"));
		System.out.println(this.updateBook("b105", "Adventures of an IT Freak", "./res/it.jpg", 215.95, "EECS1", "431",
				"IT Leadership", "good book"));

		System.out.println(this.getBookByBID("b105"));
		System.out.println(this.updateBook("b105", "Adventures of an IT Leader", "./res/it.jpg", 215.95, "EECS1", "431",
				"IT Leadership", "good book"));
		System.out.println(this.getBookByBID("b105"));

		System.out.println(
				"PO ADDED.  PO# is: " + this.addPO("test1@mailcatch.com", "Test1", "Admin", "DENIED", 2, "20191002"));
	}
//TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING 

}
