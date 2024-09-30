package SellerController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import DTO.Seller_DTO;
import ENTITY.Products;
import SERVICE.Product_Service;
//import Service.InstaMart_Product;

/**
 * Servlet implementation class SellerShowProducts
 */
public class SellerShowProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerShowProducts() {
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
		Seller_DTO seller_DTO=(Seller_DTO) session.getAttribute("seller");
		Product_Service product_service=new Product_Service();
		List<Products> productList=product_service.AllProductsbySellerid(seller_DTO.getSeller_id());
		request.setAttribute("productList", productList);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/SellerJSP/SellerShowProducts.jsp");
	    dispatcher.forward(request, response);
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
		try{
			HttpSession session =request.getSession();
		Seller_DTO seller_DTO=(Seller_DTO) session.getAttribute("seller");
		Product_Service product_service=new Product_Service();
		List<Products> productList=product_service.AllProductsbySellerid(seller_DTO.getSeller_id());
		request.setAttribute("productList", productList);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/SellerJSP/SellerShowProducts.jsp");
	    dispatcher.forward(request, response);
		}catch (Exception e) {
            request.setAttribute("Exception", e);
            request.getRequestDispatcher("/JSP/ErrorPage.jsp").forward(request, response);
        }
	}

}
