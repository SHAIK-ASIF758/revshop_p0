package SellerController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.Seller_DTO;
import ENTITY.Products;
import SERVICE.Product_Service;

/**
 * Servlet implementation class SellerAddProductServlet
 */
public class SellerAddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerAddProductServlet() {
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
		request.getRequestDispatcher("/SellerJSP/AddProductsBySeller.jsp").forward(request, response);
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
		HttpSession session =request.getSession();
		Seller_DTO sellermain = (Seller_DTO) session.getAttribute("sellerForm");
        request.setAttribute("sellerForm", sellermain);
		try {
		String name= request.getParameter("product_name");
		String desc=request.getParameter("description");
		String maxdiscount=request.getParameter("max_discount");
		Double max_discount=Double.parseDouble(maxdiscount);
		double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock_quantity"));
		String img=request.getParameter("image_url");
		int threshold = Integer.parseInt(request.getParameter("threshold"));
		int cid = Integer.parseInt(request.getParameter("category_id"));	
		int sid = Integer.parseInt(request.getParameter("seller_id"));
		Products addProduct = new Products(name, desc, price, stock, img,max_discount);
		addProduct.setCategory_id(cid);
        addProduct.setSeller_id(sid);
        addProduct.setThreshold(threshold);
        Product_Service product_service=new Product_Service();
        product_service.addProductBySeller(addProduct);
        request.getRequestDispatcher("/SellerMainServlet").forward(request, response);
		}catch (Exception e) {
            request.setAttribute("Exception", e);
            request.getRequestDispatcher("/JSP/ErrorPage.jsp").forward(request, response);
		}
	}

}
