package CustomerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.Customer_DAO;
import ENTITY.Customer;
import SERVICE.Customer_Service;

/**
 * Servlet implementation class CustomerRegistrationServlet
 */
public class CustomerRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRegistrationServlet() {
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
		String name = request.getParameter("custName");
        String email = request.getParameter("custEmail");
        Customer_DAO customer_DAO = new Customer_DAO();
        
        if (customer_DAO.getCustomerByEmail(email) != null) {
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('Email already exists! Please use a different email.'); window.history.back();</script>");
        } else {
            String number = request.getParameter("custPhone");
            long phone_number = Long.parseLong(number);
            String address = request.getParameter("custAddress");
            String password = request.getParameter("custPassword");
            Customer customer = new Customer(name, phone_number, address, email, password, 0);
            Customer_Service customer_service = new Customer_Service();
            customer_service.registerCustomer(customer);
            response.sendRedirect("/RevShop_Project_P0/JSP/Login_page.jsp");
        }
    }


}
