package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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
import beans.POBean;
import beans.POitemBean;
import beans.TopTenBean;
import beans.VisitEventBean;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private adminModel aModel;
	
	POBean poBean;
	POitemDAO pd;
	PODAO podao;
	TopTenBean topTen;
	VisitEventBean visitBean;
	

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
			
			try {
				if ((model.isLoggedIn()) || (model.checkID(email, pword))) {

					request.getRequestDispatcher("./Admin.jspx").forward(request, response); 

				} else {
					
					request.setAttribute("message", "Invalid Credentials");
					request.getRequestDispatcher("./AdminLogin.jspx").forward(request, response);
				}
			} catch (SQLException e) {

				request.setAttribute("message", "Invalid Credentials");
				request.getRequestDispatcher("./AdminLogin.jspx").forward(request, response);

			}
		} else if(request.getParameter("zip") != null){
			try {
				zipCode(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(request.getParameter("mon") != null){
			try {
				popular(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {

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
	
	
	protected void popular(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		Map<String, TopTenBean> topTen = pd.retrieveTen();
		//Map<String, TopTenBean> retrieveMonths = podao.retrieveBetweenDates(d1, d2);
		//request.getSession().setAttribute("retrieveMonths", retrieveMonths);
		request.getSession().setAttribute("topTen", topTen);
	}
	
	protected void zipCode (HttpServletRequest request, HttpServletResponse response) throws SQLException {
			Map<String, POBean> retrieveByUID = podao.retrieveByUID(poBean.getUid()) ;
			System.out.println(retrieveByUID);
			request.setAttribute("zipCode", retrieveByUID);
	}
	


}
