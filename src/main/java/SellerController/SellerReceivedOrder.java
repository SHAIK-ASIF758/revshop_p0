package SellerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.Seller_DTO;
import SERVICE.Order_Service;

/**
 * Servlet implementation class SellerReceivedOrder
 */
public class SellerReceivedOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerReceivedOrder() {
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
		Seller_DTO seller = (Seller_DTO) session.getAttribute("sellerForm");
        request.setAttribute("seller", seller);
        request.setAttribute("sellerForm", seller);
		request.getRequestDispatcher("/SellerJSP/SellerReceivedOrders.jsp").forward(request, response);
		}catch (Exception e) {
            request.setAttribute("Exception", e);
            request.getRequestDispatcher("/JSP/ErrorPage.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		HttpSession session =request.getSession();
		Seller_DTO seller = (Seller_DTO) session.getAttribute("sellerForm");
        request.setAttribute("seller", seller);
        request.setAttribute("sellerForm", seller);
		int orderId = Integer.parseInt(request.getParameter("orderId"));
	    String status = request.getParameter("status");
	        Order_Service instamart_order = new Order_Service();
	        instamart_order.updateOrderStatus(orderId, status);
	        request.getRequestDispatcher("/SellerJSP/SellerReceivedOrders.jsp").forward(request, response);
		}catch (Exception e) {
            request.setAttribute("Exception", e);
            request.getRequestDispatcher("/JSP/ErrorPage.jsp").forward(request, response);
        }

	}

}
