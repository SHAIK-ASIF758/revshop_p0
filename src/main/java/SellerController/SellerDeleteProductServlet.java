package SellerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.Seller_DTO;
import SERVICE.Product_Service;

/**
 * Servlet implementation class SellerDeleteProductServlet
 */
public class SellerDeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerDeleteProductServlet() {
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
		Seller_DTO sellermain = (Seller_DTO) session.getAttribute("sellerForm");
        request.setAttribute("sellerForm", sellermain);
        request.setAttribute("seller", sellermain);
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
		String productIdStr = request.getParameter("productId");
        int productId = Integer.parseInt(productIdStr);
        Product_Service product_service=new Product_Service();
        Seller_DTO seller_DTO = (Seller_DTO) session.getAttribute("sellerForm");
		request.setAttribute("sellerForm", seller_DTO);
        boolean deleted=product_service.SellerdeleteProduct(productId);
        if (deleted) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("failure");
        }
	}catch (Exception e) {
        request.setAttribute("Exception", e);
        request.getRequestDispatcher("/JSP/ErrorPage.jsp").forward(request, response);
    }

	}

}
