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
import ENTITY.Carts;
import SERVICE.Cart_Service;

/**
 * Servlet implementation class CustomerCartServlet
 */
public class CustomerCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerCartServlet() {
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
		request.getRequestDispatcher("/CustomerJSP/CustomerCartView.jsp?").forward(request, response);
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
		Customer_DTO customer_DTO = (Customer_DTO) session.getAttribute("customer");
		String pid=request.getParameter("product_id");
		String sid=request.getParameter("seller_id");
		String cid=request.getParameter("category_id");
		String id=request.getParameter("id");
		int m_id=Integer.parseInt(id);
		int product_id=Integer.parseInt(pid);
		int seller_id=Integer.parseInt(sid);
		int category_id=Integer.parseInt(cid);
		if(m_id!=1) {
			Cart_Service instamart_cart=new Cart_Service();
			Carts cart=new Carts(seller_id,customer_DTO.getCustomer_id(),product_id,category_id,1);
			instamart_cart.addToCartByCustomer(cart);
		}

		/*this.setSeller_id(seller_id);
		this.setCustomer_id(customer_id);
		this.product_id=product_id;
		this.setCategory_id(category_id);
		this.setQuantity(quantity);*/
		request.setAttribute("customer", customer_DTO);
		request.getRequestDispatcher("/CustomerJSP/ProductDetails.jsp?product_id="+product_id).forward(request, response);
		}catch (Exception e) {
	        request.setAttribute("Exception", e);
	        request.getRequestDispatcher("/CustomerJSP/CustomerError_Page.jsp").forward(request, response);
	    }
	}

}
