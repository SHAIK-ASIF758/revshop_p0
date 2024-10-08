package CustomerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.Cart_DAO;
import DTO.Customer_DTO;
import ENTITY.Carts;

/**
 * Servlet implementation class CustomerUpdateCartServlet
 */
public class CustomerUpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdateCartServlet() {
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
		int cart_id = Integer.parseInt(request.getParameter("cart_id"));
        String action = request.getParameter("action");
        Cart_DAO cartDAO = new Cart_DAO();
        Carts cart = cartDAO.getByCart_id(cart_id);
        if (cart != null) {
            if ("increase".equals(action)) {
                cartDAO.updateProductQuantityByCartID(cart_id, cart.getQuantity() + 1);
            } else if ("decrease".equals(action) && cart.getQuantity() > 1) {
                cartDAO.updateProductQuantityByCartID(cart_id, cart.getQuantity() - 1);
            }
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