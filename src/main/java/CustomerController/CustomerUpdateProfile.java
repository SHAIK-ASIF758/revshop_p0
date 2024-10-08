package CustomerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.Customer_DTO;
import DTO.Seller_DTO;
import ENTITY.Customer;
import ENTITY.Seller;
import SERVICE.Customer_Service;

/**
 * Servlet implementation class CustomerUpdateProfile
 */
public class CustomerUpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		HttpSession session =request.getSession();
		Customer_DTO customer_DTO = (Customer_DTO) session.getAttribute("customer");
		request.setAttribute("customer", customer_DTO);
		request.getRequestDispatcher("/CustomerJSP/CustomerProfileUpdate.jsp?").forward(request, response);
		}catch (Exception e) {
	        request.setAttribute("Exception", e);
	        request.getRequestDispatcher("/CustomerJSP/CustomerError_Page.jsp").forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		Customer_DTO customer_DTO = (Customer_DTO) session.getAttribute("customer");
		try {
		String name = request.getParameter("customer_name");
		String email = request.getParameter("customer_email");
		String phone_Number = request.getParameter("customer_phone_number");
		long phoneNumber = Long.parseLong(phone_Number);
		String address = request.getParameter("customer_address");
	    String password = request.getParameter("customer_password");
	    System.out.println("value=>"+password+"<=test-p");
	    Customer_Service instamart_customer=new Customer_Service(); 
	    if(password!=null && !password.trim().isEmpty()) {
	    	/*this.name=name;
		this.phone_number=phone_number;
		this.address=address;
		this.email=email;
		this.password=password;
		this.wallet_balance=wallet_balance;*/
	    	Customer customer1=new Customer(name,phoneNumber,address,email,password,customer_DTO.getWallet_balance());
	    	customer1.setCustomer_id(customer_DTO.getCustomer_id());
	    	Customer_DTO customermain=instamart_customer.updateCustomerProfile(customer1);
	    	session.setAttribute("customer", customermain);
	        request.setAttribute("customer", customermain);
	        request.getRequestDispatcher("/CustomerMainServlet").forward(request, response);
	    	
	    }else {
	    	/*this.customer_id=customer_id;
		this.name=name;
		this.phone_number=phone_number;
		this.address=address;
		this.email=email;
		this.wallet_balance=wallet_balance;*/
	    	Customer_DTO customer2=new Customer_DTO(customer_DTO.getCustomer_id(),name,phoneNumber,address,email,customer_DTO.getWallet_balance());
	    	System.out.println(customer2+"test");
	    	Customer_DTO customermain=instamart_customer.updateCustomerbyDTO(customer2);
	    	session.setAttribute("customer", customermain);
	        request.setAttribute("customer", customermain);
	        request.getRequestDispatcher("/CustomerMainServlet").forward(request, response);
	    }
		}catch (Exception e) {
	        request.setAttribute("Exception", e);
	        request.getRequestDispatcher("/CustomerJSP/CustomerError_Page.jsp").forward(request, response);
	    }
	}

}
