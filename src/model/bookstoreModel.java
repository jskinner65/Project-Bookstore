package model;

import java.sql.SQLException;
import java.util.Map;

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

	public bookstoreModel() throws ClassNotFoundException {
		super();
		this.addressDAO = new AddressDAO();
		this.bookDAO = new BookDAO();
		this.poDAO = new PODAO();
		this.poitemDAO = new POitemDAO();
		this.visitEventDAO = new VisitEventDAO();
	}

	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public String getBookbyName(String bookName) throws SQLException {
		String result = "";
		Map<String, BookBean> rv = bookDAO.retrieve(bookName);
		result = "<table>";
		for (Map.Entry<String, BookBean> pair : rv.entrySet()) {

			result = result + "<tr><td>" + pair.getValue().getBid() + "</td><td>" + pair.getValue().getTitle()
					+ "</td><td>" + pair.getValue().getPrice() + "</td></tr>";
		}
		result = result + "</table>";
		return result;
	}

	public String getBooks(String category) throws SQLException {
		String result = "";
		Map<String, BookBean> rv = bookDAO.retrieve("");
		result = "<table>";
		for (Map.Entry<String, BookBean> pair : rv.entrySet()) {
			if (pair.getValue().getCategory().equals(category))

				result = result + "<tr><td>" + pair.getValue().getBid() + "</td><td>" + pair.getValue().getTitle()
						+ "</td><td>" + pair.getValue().getPrice() + "</td></tr>";
		}

		result = result + "</table>";
		return result;
	}

	public String getAllBooks() throws SQLException {
		String result = "";
		Map<String, BookBean> rv = bookDAO.retrieveAll();
		result = "<table>";
		for (Map.Entry<String, BookBean> pair : rv.entrySet()) {

			result = result + "<tr><td>" + pair.getValue().getBid() + "</td><td>" + pair.getValue().getTitle()
					+ "</td><td>" + pair.getValue().getPrice() + "</td></tr>";
		}
		result = result + "</table>";
		return result;
	}

	public PODAO getPoDAO() {
		return poDAO;
	}

	public POitemDAO getPoitemDAO() {
		return poitemDAO;
	}

	public VisitEventDAO getVisitEventDAO() {
		return visitEventDAO;
	}

}
