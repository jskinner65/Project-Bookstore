package model;

import DAO.AddressDAO;
import DAO.BookDAO;
import DAO.PODAO;
import DAO.POitemDAO;
import DAO.VisitEventDAO;

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

	public BookDAO getBookDAO() {
		return bookDAO;
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
