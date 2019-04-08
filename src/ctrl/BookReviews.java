package ctrl;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import DAO.BookDAO;
import DAO.ReviewDAO;
import beans.BookBean;
import beans.ReviewBean;
import DAO.ReviewDAO;

/**
 * Servlet implementation class BookReviewAdd
 */
@WebServlet("/BookReviewAdd")
public class BookReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DAO.BookDAO bookDao;
       ReviewDAO reviewDao;
       Map<String, BookBean> book;
       ReviewBean bookReview;
       Date now;
       DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookReviews() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
		
		try {
			reviewDao = new ReviewDAO(ds);
			bookDao =new BookDAO(ds);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//book=new BookBean();
		now=new Date();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * This takes attributes that are set throughout the site
	 * then  deals with the book id by taking it in and addint it to the book review bean
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid=request.getSession().getAttribute("uid").toString();
		String bid = request.getSession().getAttribute("bid").toString();
		String bookID = null;
		String reviewComment=request.getParameter("reviewtext");
		Map<String, ReviewBean> reviews;
		String bookname="";
		float rating=-1;
		
		//Date format to match that accepted by MySQL
		String datePattern="yyyy-MM-dd";
		SimpleDateFormat formatter=new SimpleDateFormat(datePattern);
		String finalDate=formatter.format(now);
		
		try {
			rating=Float.parseFloat(request.getParameter("rating"));
		} catch(Exception e) {
			rating=-1;
		}
		try {
			book=bookDao.retrieveByBID(bid);
			reviews = reviewDao.getReviews(bid);
			
			bookname=((BookBean) book).getTitle();
			request.getSession().setAttribute("review", reviews);
			request.getSession().setAttribute("bid", bid);
			request.getSession().setAttribute("uid", uid);
			request.getSession().setAttribute("bookname", bookname);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		java.sql.Date date= new java.sql.Date(System.currentTimeMillis()); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}