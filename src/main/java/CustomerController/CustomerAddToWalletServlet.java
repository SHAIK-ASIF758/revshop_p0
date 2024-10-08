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

/**
 * Servlet implementation class CustomerAddToWalletServlet
 */
public class CustomerAddToWalletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAddToWalletServlet() {
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
		Customer_DTO customer = (Customer_DTO) session.getAttribute("customer");
        request.setAttribute("customer", customer);	

        request.getRequestDispatcher("/CustomerJSP/CustomerWallet.jsp").forward(request, response);
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
		
		HttpSession session = request.getSession();
        Customer_DTO customer = (Customer_DTO) session.getAttribute("customer");
        try {
        double addAmount = Double.parseDouble(request.getParameter("addAmount"));
        Customer_DAO customer_DAO=new Customer_DAO();
        double currentBalance = customer.getWallet_balance();
        double totalAmount=currentBalance+addAmount;
        customer.setWallet_balance(totalAmount);
        customer_DAO.updateCustomerWallet(totalAmount,customer.getCustomer_id());
        request.setAttribute("customer", customer);	
        request.getRequestDispatcher("/CustomerMainServlet").forward(request, response);
		}catch (Exception e) {
	        request.setAttribute("Exception", e);
	        request.getRequestDispatcher("/CustomerJSP/CustomerError_Page.jsp").forward(request, response);
	    }
	}

}
