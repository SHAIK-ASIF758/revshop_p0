package SellerController;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DTO.Seller_DTO;

/**
 * Servlet implementation class SellerMainServlet
 */
public class SellerMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerMainServlet() {
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
        request.setAttribute("seller", sellermain);
		request.getRequestDispatcher("/SellerJSP/Seller_Main.jsp").forward(request, response);
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
		Seller_DTO sellermain = (Seller_DTO) session.getAttribute("sellerForm");
        request.setAttribute("sellerForm", sellermain);
        request.setAttribute("seller", sellermain);
		request.getRequestDispatcher("/SellerJSP/Seller_Main.jsp").forward(request, response);
		}catch (Exception e) {
            request.setAttribute("Exception", e);
            request.getRequestDispatcher("/SellerJSP/ErrorPage.jsp").forward(request, response);
        }
		
	}

}