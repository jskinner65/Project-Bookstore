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
import beans.BookBean;

public class bookstoreModel {
	private AddressDAO addressDAO;
	private BookDAO bookDAO;
	private PODAO poDAO;
	private POitemDAO poitemDAO;
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
		this.visitEventDAO = new VisitEventDAO(ds);

	}

//____________________________ADDRESS____________________________________________________
	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

// ___________________________DISPLAYING THE BOOKS ________________________________________
	public String displayBooks(Map<String, BookBean> rv) {
		String result = "";
		result = "<table>";
		for (Map.Entry<String, BookBean> pair : rv.entrySet()) {

			result = result + "<tr><td>" + pair.getValue().getBid() + "</td><td>" + pair.getValue().getTitle()
					+ "</td><td>" + pair.getValue().getPrice() + "</td></tr>";
		}
		result = result + "</table>";
		return result;
	}

// _________________________GETTING BOOKS___________________________________________________
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

//_____________________________________GETTING REVIEWS________________________________________
	public String getReviews(String bid) {
		String results = "";

		return results;
	}

//______________________________________PO_____________________________________________________

	public PODAO getPoDAO() {
		return poDAO;
	}

// _____________________________________POITEMS_______________________________________________
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
	}
//TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING TESTING 

}
