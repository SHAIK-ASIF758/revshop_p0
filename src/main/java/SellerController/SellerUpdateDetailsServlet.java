package SellerController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.Seller_DAO;
import DTO.Seller_DTO;
import ENTITY.Seller;
import SERVICE.Seller_Service;

/**
 * Servlet implementation class SellerUpdateDetailsServlet
 */
public class SellerUpdateDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerUpdateDetailsServlet() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("SellerJSP/SellerProfileUpdate.jsp");
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
		Seller_DTO seller_DTO =(Seller_DTO) session.getAttribute("seller");
		String name = request.getParameter("seller_name");
		String email = request.getParameter("seller_email");
		String phone_Number = request.getParameter("seller_phone_number");
		long phoneNumber = Long.parseLong(phone_Number);
		String address = request.getParameter("seller_address");
	    String password = request.getParameter("seller_password");
	    Seller_Service instamart_seller=new Seller_Service(); 
	    if(password!=null && !password.trim().isEmpty()) {
	    	Seller seller1=new Seller(name,email,phoneNumber,address,seller_DTO.getTotal_earning(),password,seller_DTO.getCurrent_month_earning());
	    	seller1.setSeller_id(seller_DTO.getSeller_id());
	    	seller1.setCurrent_month_item_sold(seller_DTO.getCurrent_month_item_sold());
	    	seller1.setTotal_item_sold(seller_DTO.getTotal_item_sold());
	    	Seller_DTO sellermain=instamart_seller.updateSellerProfile(seller1);
	    	session.setAttribute("seller", sellermain);
	        request.setAttribute("sellerForm", sellermain);
	        request.getRequestDispatcher("/SellerJSP/Seller_Main.jsp").forward(request, response);
	    	
	    }else {
	    	Seller_DTO seller2=new Seller_DTO(seller_DTO.getSeller_id(),name,email,phoneNumber,address,seller_DTO.getTotal_earning(),seller_DTO.getCurrent_month_earning(),seller_DTO.getTotal_item_sold(),seller_DTO.getCurrent_month_item_sold());
	    	Seller_DTO sellermain=instamart_seller.updateSellerProfile(seller2);
	    	session.setAttribute("seller", sellermain);
	        request.setAttribute("sellerForm", sellermain);
	        request.getRequestDispatcher("/SellerJSP/Seller_Main.jsp").forward(request, response);
	    }
		}catch (Exception e) {
            request.setAttribute("Exception", e);
            request.getRequestDispatcher("/JSP/ErrorPage.jsp").forward(request, response);
        }
	    
	}

}
