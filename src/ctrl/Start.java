package ctrl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import DAO.*;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String searchField = "";
	private int sCartSize = 0;
	private bookstoreModel bModel;
	private userModel uModel;
	private String currPage;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Start() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		// ServletContext context = getServletContext();

		try {
			uModel = new userModel();
			bModel = uModel.getbookStoreModel();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		getServletContext().setAttribute("bookModel", bModel);
		getServletContext().setAttribute("userModel", uModel);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

//		String uri = request.getRequestURI();
		String uri = request.getRequestURI();
//		System.out.println("URI string is: " + uri);
		currPage = request.getParameter("currPage");
		if (currPage == null) {
			currPage = "home";
		}
		if (currPage.equals("home")) {

			request.getRequestDispatcher("./index.html").forward(request, response);
		} else if (currPage.equals("categories")) {

		} else if (currPage.equals("cart")) {

		} else if (currPage.equals("team")) {

		} else if (currPage.equals("login")) {

		} else {
			request.getRequestDispatcher("./index.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
