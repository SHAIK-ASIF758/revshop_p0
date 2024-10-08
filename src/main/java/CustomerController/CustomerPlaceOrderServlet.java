package CustomerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.Customer_DTO;

/**
 * Servlet implementation class CustomerPlaceOrderServlet
 */
public class CustomerPlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerPlaceOrderServlet() {
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
	        //request.getRequestDispatcher("/CustomerJSP/CustomerMainProductDisplay.jsp").forward(request, response);
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
        double totalValue = Double.parseDouble(request.getParameter("total_value"));
        request.setAttribute("total_amount", totalValue);
        Customer_DTO customer = (Customer_DTO) session.getAttribute("customer");
        request.setAttribute("customer", customer);	
        request.getRequestDispatcher("/CustomerJSP/OrderProcced.jsp").forward(request, response);
//        String[] productIdArray = productIds.split(",");
//        for (String productIdStr : productIdArray) {
//            int productId = Integer.parseInt(productIdStr);
//            // Handle each product (e.g., store order details in the database)
//        }
		}catch (Exception e) {
	        request.setAttribute("Exception", e);
	        request.getRequestDispatcher("/CustomerJSP/CustomerError_Page.jsp").forward(request, response);
	    }
	}

}
