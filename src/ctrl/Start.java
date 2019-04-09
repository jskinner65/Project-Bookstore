package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AddressBean;
import beans.POBean;
import beans.UserBean;
import model.bookstoreModel;
import model.userModel;

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
	NumberFormat formatter;
	private static final double tax = 0.13;
	private int cardTries = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Start() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		// ServletContext context = getServletContext();
		formatter = NumberFormat.getCurrencyInstance();

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
			try {
				String category = request.getParameter("category");
				if (category == null) {
					request.setAttribute("results", (bModel.getAllBooks()));

				} else {
					request.setAttribute("results", bModel.displayBooks(bModel.getByCategoryMap(category)));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("./Browse.jspx").forward(request, response);

		} else if (currPage.equals("search")) {
			try {
				String search = request.getParameter("searches");
				if (search == null) {
					request.setAttribute("results", (bModel.getAllBooks()));

				} else {
					request.setAttribute("results", bModel.displayBooks(bModel.getByNameMap(search)));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("./Browse.jspx").forward(request, response);

		} else if (currPage.equals("cart")) {
			try {

				request.setAttribute("sSize", uModel.getCartSize());
				double subtotal = uModel.getSubtotal();
				request.setAttribute("subtotal", formatter.format(subtotal));
				request.setAttribute("tax", formatter.format(subtotal * tax));
				request.setAttribute("total", formatter.format(subtotal * (1 + tax)));
				request.setAttribute("displaycart", uModel.getCart());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("./ShoppingCart.jspx").forward(request, response);

		} else if (currPage.equals("payment")) {
			try {

				request.setAttribute("sSize", uModel.getCartSize());
				double subtotal = uModel.getSubtotal();
				request.setAttribute("subtotal", formatter.format(subtotal));
				request.setAttribute("tax", formatter.format(subtotal * tax));
				request.setAttribute("total", formatter.format(subtotal * (1 + tax)));
				request.setAttribute("displaysimple", uModel.getCartSimple());
				System.out.println(uModel.getCartSimple());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("./Payment.jspx").forward(request, response);

		} else if (currPage.equals("receipt")) {
			cardTries++;
			if (cardTries == 3) { // Denies third try on credit card
				cardTries = 0;
				try {
					request.setAttribute("validity",
							uModel.getCartSize() + " <h1>Card Not Valid! Please try again.</h1>");
					request.getRequestDispatcher("./Payment.jspx").forward(request, response);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else { // Allows credit card to go through
				UserBean user = null;
				POBean poID = uModel.createPO();
				int uid = poID.getUid();
				try {
					user = bModel.getUser(uid);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("validity", " ");
				request.setAttribute("receiptNumber", uid);
				request.setAttribute("name", user.getFname() + " " + user.getLname());
				request.setAttribute("receiptDate", poID.getDay());
				double subtotal = 0;
				try {
					subtotal = uModel.getSubtotal();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				request.setAttribute("subtotal", formatter.format(subtotal));
				request.setAttribute("tax", formatter.format(subtotal * tax));
				request.setAttribute("total", formatter.format(subtotal * (1 + tax)));
				try {
					AddressBean addr = bModel.getShippingID(uid);
					request.setAttribute("address", addr.getStreet() + ", " + addr.getProvince() + "<br />"
							+ addr.getZip() + "<br />" + addr.getCountry() + ", " + addr.getPhone());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					request.setAttribute("address", "Shipping Address not on file.  Please update your profile");

				}
				try {
					request.setAttribute("booksBought", uModel.getCartSimple());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// change from cart to purchase
				request.getRequestDispatcher("./Receipt.jspx").forward(request, response);

			}
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
