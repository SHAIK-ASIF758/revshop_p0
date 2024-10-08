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
import SERVICE.Order_Service;

/**
 * Servlet implementation class CustomerPlaceOrderSuccessServlet
 */
public class CustomerPlaceOrderSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerPlaceOrderSuccessServlet() {
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
			//request.getRequestDispatcher("/CustomerJSP/CustomerCartView.jsp?").forward(request, response);
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
		try {
		Customer_DTO customer_DTO = (Customer_DTO) session.getAttribute("customer");
		double totalValue = Double.parseDouble(request.getParameter("total_value"));
		String deliveryAddress = request.getParameter("deliveryAddress");
		Order_Service Order_Service=new Order_Service();
		Order_Service.PlaceSuccessfulOrderByCustomer(customer_DTO,totalValue,deliveryAddress);
		double wallet=customer_DTO.getWallet_balance()-totalValue;
		customer_DTO.setWallet_balance(wallet);
		Customer_DAO customer=new Customer_DAO();
		//System.out.print(customer_DTO.getWallet_balance() + " " + customer_DTO.getCustomer_id() + " hello");
		customer.updateCustomerWallet(customer_DTO.getWallet_balance(),customer_DTO.getCustomer_id());
		request.setAttribute("customer", customer_DTO);
		boolean orderSuccess=true;
		request.setAttribute("orderSuccess", orderSuccess);
        request.getRequestDispatcher("/CustomerJSP/CustomerMainProductDisplay.jsp").forward(request, response);
		}catch (Exception e) {
	        request.setAttribute("Exception", e);
	        request.getRequestDispatcher("/CustomerJSP/CustomerError_Page.jsp").forward(request, response);
	    }
		
	}

}
