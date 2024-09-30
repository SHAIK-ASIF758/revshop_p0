package CustomerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.Customer_DAO;
import DTO.Customer_DTO;
import DTO.Seller_DTO;
//import DTO.Seller_DTO;
import ENTITY.Customer;
//import Service.InstaMart_Customer;

/**
 * Servlet implementation class CustomerLoginServlet
 */
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		Seller_DTO sellermain = (Seller_DTO) session.getAttribute("sellerForm");
        request.setAttribute("sellerForm", sellermain);
        request.setAttribute("seller", sellermain);
//		request.getRequestDispatcher("/JSP/Seller_Main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		String email = request.getParameter("custEmail");
	    String password = request.getParameter("custPassword");
	    Customer_DAO customer_DAO = new Customer_DAO();
	    Customer customer = customer_DAO.getCustomerByEmail(email);
	    if (customer == null) {
	        response.setContentType("text/html");
	        response.getWriter().write("<script>alert('Email does not exist! Please check your email or register.'); window.history.back();</script>");
	    } else {
			String storedPassword = customer.getPassword();
	        if (!storedPassword.equals(password)) {
	            response.setContentType("text/html");
	            response.getWriter().write("<script>alert('Wrong Password'); window.history.back();</script>");
	        } else {
	            Customer_DTO customer_DTO=new Customer_DTO(customer.getCustomer_id(),customer.getName(),customer.getPhone_number(),customer.getAddress(),customer.getEmail(),customer.getWallet_balance());
	            session.setAttribute("customer", customer_DTO);
	            request.setAttribute("Customer", customer_DTO);
	            request.getRequestDispatcher("/CustomerMainServlet").forward(request, response);
	        }

		}
	}

}
//
