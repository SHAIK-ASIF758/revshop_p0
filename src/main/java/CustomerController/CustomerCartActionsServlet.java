package CustomerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.Cart_DAO;
import DAO.Favorite_DAO;
import DTO.Customer_DTO;
import SERVICE.Cart_Service;

/**
 * Servlet implementation class CustomerCartActionsServlet
 */
public class CustomerCartActionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerCartActionsServlet() {
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
		String action = request.getParameter("action");
		
        int cart_id = Integer.parseInt(request.getParameter("cart_id"));
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        Cart_Service Cart_Service=new Cart_Service();
        if ("remove".equals(action)) {
        	Cart_Service.removeCartFromList(cart_id);
        } else if ("move_to_favorites".equals(action)) {
        	Cart_Service.moveProductToMyFavorites(cart_id);
        }
        Customer_DTO customer_DTO = (Customer_DTO) session.getAttribute("customer");
		request.setAttribute("customer", customer_DTO);
		request.getRequestDispatcher("/CustomerJSP/CustomerCartView.jsp?").forward(request, response);
		}catch (Exception e) {
	        request.setAttribute("Exception", e);
	        request.getRequestDispatcher("/CustomerJSP/CustomerError_Page.jsp").forward(request, response);
	    }
	}

}