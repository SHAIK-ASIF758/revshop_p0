package SellerController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.Customer_DAO;
import DAO.Seller_DAO;
import DTO.Seller_DTO;
import ENTITY.Customer;
import ENTITY.Seller;
import Exceptions.UserNotFoundException;

/**
 * Servlet implementation class SellerLoginServlet
 */
//@WebServlet("/sample")
//@WebServlet("/productDisplay")
public class SellerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session =request.getSession();
			Seller_DTO seller_DTO = (Seller_DTO) session.getAttribute("sellerForm");
			request.setAttribute("sellerForm", seller_DTO);
		  request.getRequestDispatcher("/SellerJSP/Seller_Main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		String email = request.getParameter("sellEmail");
	    String password = request.getParameter("sellPassword");
	    Seller_DAO seller_DAO = new Seller_DAO();
	    Seller seller = seller_DAO.getSellerByEmail(email);
	    if (seller == null) {
	        response.setContentType("text/html");
	        response.getWriter().write("<script>alert('Email does not exist! Please check your email or register.'); window.history.back();</script>");
	    } else {
			String storedPassword = seller.getSeller_password();
	        if (!storedPassword.equals(password)) {
	            response.setContentType("text/html");
	            response.getWriter().write("<script>alert('Wrong Password'); window.history.back();</script>");
	        } else {
	        	Seller_DTO seller_DTO=new Seller_DTO(seller.getSeller_id(),seller.getSeller_name(),seller.getSeller_email(),seller.getSeller_phone_number(),seller.getSeller_address(),seller.getTotal_earning(),seller.getCurrent_month_earning(),seller.getTotal_item_sold(),seller.getCurrent_month_item_sold());
	            session.setAttribute("seller", seller_DTO);
	            request.setAttribute("sellerForm", seller_DTO);
	            request.getRequestDispatcher("/SellerJSP/Seller_Main.jsp").forward(request, response);
	        }

		}
	}

}