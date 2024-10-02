package CustomerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.Customer_DTO;
import SERVICE.Favorite_Service;

/**
 * Servlet implementation class CustomerFavoriteActionsServlet
 */
public class CustomerFavoriteActionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerFavoriteActionsServlet() {
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
		request.getRequestDispatcher("/CustomerJSP/CustomerFavoritesView.jsp?").forward(request, response);
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
		String action = request.getParameter("action");
        int favorite_id = Integer.parseInt(request.getParameter("favorite_id"));
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        Favorite_Service favorite_service=new Favorite_Service();
        if ("remove".equals(action)) {
        	favorite_service.removeFavoriteFromList(favorite_id);
        } else if ("move_to_cart".equals(action)) {
        	favorite_service.moveProductToMyCart(favorite_id);
        }
		request.setAttribute("customer", customer_DTO);
		request.getRequestDispatcher("/CustomerJSP/CustomerFavoritesView.jsp?").forward(request, response);
		}catch (Exception e) {
	        request.setAttribute("Exception", e);
	        request.getRequestDispatcher("/CustomerJSP/CustomerError_Page.jsp").forward(request, response);
	    }
	}

}
