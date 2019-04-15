package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDAO;
import DAO.PODAO;
import DAO.POitemDAO;
import DAO.ReviewDAO;
import model.adminModel;
import beans.BookBean;
import beans.POitemBean;
import beans.TopTenBean;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private adminModel aModel;
	
	BookDAO b;
	ReviewDAO br;
	PODAO po;
	POitemDAO pd;

	public void init() {
		//b = new BookDAO(null);
		
	}
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		adminModel model = null;
		try {
			model = new adminModel();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (page == null) {
			page = "home";
		}
		if (page.equals("analytics")) {
			String email = request.getParameter("email");
			String pword = request.getParameter("pword");
			request.getParameter("jan");
			try {
				if ((model.isLoggedIn()) || (model.checkID(email, pword))) {
					// general template:
					// if request.getparameter("janary value in uri") != null:
					// request.setAttribute("jan", aModel.getAnalyticsbyMonth("jan")) 
					//--> make a function in adminModel that retrieves analytics by month, 
					//similar to getCategorybyMap in bookstoreModel. Called getAnalyticsbyMonth. 
					// This function will call AnalyticsDAO and query all the data by the month you specify, will need to write an sql query...
					// Finally you will have a function in adminModel called displayAnalytics
					//(similar to display books but instead you simply display analytics info, 
					//can be plain html. To get a specific month you need to map it -- with getAnalyticsbyMonth.
					// within admin.jspx, call ${jan} in a separate <tr> tag with <td> in it, ex for Jan should be 
					//called after line 208 (not sure about this tho)
					//if request.getParameter("january)
					if (request.getParameter("jan") != null) {
						
					}
					request.getRequestDispatcher("./Admin.jspx").forward(request, response); 

				} else {
					
					request.setAttribute("message", "Invalid Credentials");
					request.getRequestDispatcher("./AdminLogin.jspx").forward(request, response);
				}
			} catch (SQLException e) {

				request.setAttribute("message", "Invalid Credentials");
				request.getRequestDispatcher("./AdminLogin.jspx").forward(request, response);

			}
		} else {

			request.getRequestDispatcher("./AdminLogin.jspx").forward(request, response);
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
	
	
	protected void populatePopular(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Map<String, TopTenBean> mostPop = pd.retrieveTen();
		request.setAttribute("popular", mostPop);
		System.out.println(mostPop);
	}
	
//	private void populateByMonth(HttpServletRequest request, HttpServletResponse response) {
//
//		//StringBuilder monthTable = new StringBuilder();
//		int month = 0;
//		if(request.getParameter("jan")!=null) {
//			month=Integer.parseInt(request.getParameter("day"));
//		
//		}
//
//		try {
//			Map<String, POitemBean> report = pd.retrieveBookSold(month);
//	/*		Iterator<BookBean> iteReport = report.iterator();
//				while (iteReport.hasNext())
//					System.out.println(iteReport.next().getTitle());*/
//				request.setAttribute("monthly", report);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

}
