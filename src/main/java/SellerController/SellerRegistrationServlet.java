package SellerController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.Customer_DAO;
import DAO.Seller_DAO;
import ENTITY.Customer;
import ENTITY.Seller;
import SERVICE.Seller_Service;


/**
 * Servlet implementation class SellerRegistrationServlet
 */
public class SellerRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("sellName");
		String email=request.getParameter("sellEmail");
		Seller_DAO seller_DAO=new Seller_DAO();
		if(seller_DAO.getSellerByEmail(email)!=null) {
			 response.setContentType("text/html");
		     response.getWriter().write("<script>alert('Email already exists! Please use a different email.'); window.history.back();</script>");

		}else {
			String number=request.getParameter("sellPhone");
			long phone_number=Long.parseLong(number);
			String address=request.getParameter("sellAddress");
			String password=request.getParameter("sellPassword");
			Seller seller = new Seller(name,email,phone_number,address, 0, password, 0);
			Seller_Service instamart_seller=new Seller_Service();
	        instamart_seller.registerSeller(seller);
	        response.sendRedirect("/Project_P0/JSP/Login_page.jsp");
	        // Optionally redirect to a success page or login page
	        //response.sendRedirect("success.jsp");
		}
	}

}
