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

/**
 * Servlet implementation class CustomerMainServlet
 */
public class CustomerMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerMainServlet() {
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
        request.getRequestDispatcher("/CustomerJSP/CustomerMainProductDisplay.jsp").forward(request, response);
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
		try {
		HttpSession session =request.getSession();
		Customer_DTO customer = (Customer_DTO) session.getAttribute("customer");
        request.setAttribute("customer", customer);	
        request.getRequestDispatcher("/CustomerJSP/CustomerMainProductDisplay.jsp").forward(request, response);
		}catch (Exception e) {
	        request.setAttribute("Exception", e);
	        request.getRequestDispatcher("/CustomerJSP/CustomerError_Page.jsp").forward(request, response);
	    }
	}

}
