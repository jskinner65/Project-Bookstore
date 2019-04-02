package model;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DAO.AddressDAO;
import DAO.BookDAO;
import DAO.PODAO;
import DAO.POitemDAO;
import DAO.ReviewDAO;
import DAO.VisitEventDAO;
import beans.AddressBean;
import beans.BookBean;
import beans.POBean;
import beans.ReviewBean;
import beans.TopTenBean;
import beans.VisitEventBean;

public class bookstoreModel {
	private AddressDAO addressDAO;
	private BookDAO bookDAO;
	private PODAO poDAO;
	private POitemDAO poitemDAO;
	private ReviewDAO reviewDAO;
	private VisitEventDAO visitEventDAO;
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
		this.reviewDAO = new ReviewDAO(ds);
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
		return bookDAO.retrieve(category);
	}

	public Map<String, BookBean> retrieveAllMap() throws SQLException {
		return bookDAO.retrieveAll();
	}

	public BookBean getByBIDBean(String bid) throws SQLException {
		Map<String, BookBean> book = bookDAO.retrieveByBID(bid);
		return book.get(bid);
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

	public Map<String, AddressBean> getAddressByEmail(String email) throws SQLException {
		return addressDAO.retrieve(email);
	}

	public boolean updateAddress(int id, String email, String street, String province, String country, String zip,
			String phone) throws SQLException {
		return addressDAO.updateAddress(id, email, street, province, country, zip, phone);
	}

	public boolean addAddress(int id, String email, String street, String province, String country, String zip,
			String phone) throws SQLException {
		AddressBean ab = new AddressBean(id, email, street, province, country, zip, phone);
		return addressDAO.addAddress(ab);
	}

//_____________________________________GETTING REVIEWS________________________________________

	public Map<String, ReviewBean> getReviewsByBIDMap(String bid) throws SQLException {
		return reviewDAO.getReviews(bid);
	}

	public boolean addReivew(int reviewID, int rating, String bid, int uid, String reviewtext) throws SQLException {
		ReviewBean bean = new ReviewBean(reviewID, rating, bid, uid, reviewtext);
		return reviewDAO.addReivew(bean);
	}

//______________________________________PO_____________________________________________________

	public PODAO getPoDAO() {
		return poDAO;
	}

	public POBean getPOByID(int ID) throws SQLException {
		Map<String, POBean> po = poDAO.retrieveByID(ID);
		return po.get(ID + "");
	}

	public Map<String, POBean> getPOByEmail(String email) throws SQLException {
		return poDAO.retrieveByEmail(email);
	}

// _____________________________________POITEMS_______________________________________________
	public int addPO(String email, String lname, String fname, String status, int address, String day)
			throws SQLException {
		int id = 0;
		POBean po = new POBean(id, email, lname, fname, status, address, day);
		return poDAO.addPO(po);

	}

	public Map<String, POBean> getPOitemsByPO(int poID) throws SQLException {
		return poDAO.retrieveByID(poID);
	}

	public POitemDAO getPoitemDAO() {

		return poitemDAO;
	}

//______________________________________VISIT EVENTS_________________________________________
	public VisitEventDAO getVisitEventDAO() {
		return visitEventDAO;
	}

	public Map<String, VisitEventBean> getAllVisits() throws SQLException {
		return visitEventDAO.retrieve();
	}

	public Map<String, VisitEventBean> getVisitsByUID() throws SQLException {
		return visitEventDAO.retrieve();
	}
//_______________________________________ANALYTICS_______________________________________________

	public Map<String, BookBean> getTopTen() throws SQLException {
		Map<String, BookBean> allBooks = bookDAO.retrieveAll();
		Map<String, BookBean> books = new HashMap<String, BookBean>();
		Map<String, TopTenBean> topTen = poitemDAO.retrieveTen();
		System.out.println("TOP TEN SIZE     " + topTen.size());
		for (int i = 1; i <= topTen.size(); i++) {
			books.put(i + "", allBooks.get(topTen.get(i + "").getBid()));
			System.out.println("TTS  " + topTen.size());
			System.out.println("I    " + i);
		}
		System.out.println("Books SIZE     " + books.size());

		return books;
	}

	public Map<String, BookBean> getByDates(Date d1, Date d2) throws SQLException {
		Map<String, BookBean> allBooks = bookDAO.retrieveAll();
		Map<String, BookBean> books = new HashMap<String, BookBean>();
		Map<String, TopTenBean> topTen = poDAO.retrieveBetweenDates(d1, d2);
		System.out.println("TOP TEN SIZEDate     " + topTen.size());
		for (int i = 1; i <= topTen.size(); i++) {
			books.put(i + "", allBooks.get(topTen.get(i + "").getBid()));
			System.out.println("TTS  " + topTen.size());
			System.out.println("I    " + i);
		}
		System.out.println("Books SIZEDate     " + books.size());

		return books;
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
		Map<String, BookBean> TopTen = this.getTopTen();
		System.out.println(TopTen.size());
		for (int i = 1; i <= TopTen.size(); i++) {
			BookBean book = TopTen.get(i + "");
			System.out.println(book.getTitle());

		}
		Date d1 = new Date();
		Date d2 = new Date();
		d2.setTime(0);
		Map<String, BookBean> TopTen2 = this.getByDates(d1, d2);
		System.out.println(TopTen2.size());
		for (int i = 1; i <= TopTen2.size(); i++) {
			BookBean book = TopTen2.get(i + "");
			System.out.println(book.getTitle());

		}
	}
//TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING 

}
