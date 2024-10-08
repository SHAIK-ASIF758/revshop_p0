package SERVICE;

import java.util.ArrayList;
import java.util.List;

import DAO.Cart_DAO;
import DAO.Order_DAO;
import DAO.Product_DAO;
import DAO.Seller_DAO;
import DTO.Customer_DTO;
import ENTITY.Carts;
import ENTITY.Display;
import ENTITY.Order_Details;
import ENTITY.Orders;
import ENTITY.Products;
import ENTITY.Seller;
import UTIL.EmailConfig;

public class Order_Service {
	private Order_DAO order_DAO;
	public Order_Service() {
		order_DAO=new Order_DAO();
		
	}
	public void updateOrderStatus(int order_id,String status) {
		order_DAO.updateOrderStatus(order_id,status);
	}
	/*this.category_id = category_id;
		this.seller_id = seller_id;
		this.product_name = product_name;
		this.product_id = product_id;
		this.image_url = image_url;
		this.quantity = quantity;
		this.price_per_unit = price_per_unit;
		this.customer_id = customer_id;
		this.total_amount = total_amount;
		this.delivery_address = delivery_address;
		this.status = status;
		this.order_date = order_date;*/
	public List<Display> viewOrdersBySeller(int seller_id) {
		List<Display> all = new ArrayList<>();
		List<Order_Details> orders=order_DAO.viewProductOrderedBySeller(seller_id);
		Product_DAO product_DAO=new Product_DAO();
		Order_DAO order_DAO=new Order_DAO();
		for(Order_Details orderdetails:orders) {
			Orders order=order_DAO.viewByOrdersTransactionID(orderdetails.getTransaction_id());
				Products productby=product_DAO.getProductDetails(orderdetails.getProduct_id());

				Display display=new Display(productby.getCategory_id(),productby.getSeller_id(),productby.getProduct_name()
						,orderdetails.getProduct_id(),productby.getImage_url(),orderdetails.getQuantity(),orderdetails.getPrice_per_unit()
						,order.getCustomer_id(),order.getTotal_amount(),order.getDelivery_address(),orderdetails.getStatus(),order.getOrder_date());
				display.setOrder_id(orderdetails.getOrder_detail_id());
				all.add(display);

			}
			
		return all;
	}
	public List<Display> viewOrdersByCustomer(int customer_id){
		List<Display> all = new ArrayList<>();
		List<Orders> orders=order_DAO.viewProductOrderedByCustomer(customer_id);
		Product_DAO product_DAO=new Product_DAO();
		for(Orders order:orders) {
			List<Order_Details> orderdetailslist=order_DAO.viewProductOrdered_DetailsByCustomer(order.getTransaction_id());
			for(Order_Details oneorder:orderdetailslist) {
				Products productby=product_DAO.getProductDetails(oneorder.getProduct_id());
				Display display=new Display(productby.getCategory_id(),productby.getSeller_id(),productby.getProduct_name()
						,oneorder.getProduct_id(),productby.getImage_url(),oneorder.getQuantity(),oneorder.getPrice_per_unit()
						,order.getCustomer_id(),order.getTotal_amount(),order.getDelivery_address(),oneorder.getStatus(),order.getOrder_date());
				all.add(display);
			}
			
		}
		return all;
	}
	public void PlaceSuccessfulOrderByCustomer(Customer_DTO customer_DTO,double totalvalue,String address) {
		order_DAO.addToOrders(customer_DTO, totalvalue, address);
		int customer_id=customer_DTO.getCustomer_id();
		Orders order=order_DAO.getLastOrderID(customer_id);
		Cart_DAO cart=new Cart_DAO();
		Product_DAO product_DAO=new Product_DAO();
		Seller_DAO seller=new Seller_DAO();
		List<Carts>mycart=cart.viewCartByCustomer_id(customer_id);
		String to=customer_DTO.getEmail();
		String subject = "Hey, " + customer_DTO.getName() + " Order Placed Successfully!";
		StringBuilder message = new StringBuilder();
	    message.append("Hello ").append(customer_DTO.getName()).append(",\n");
	    message.append("Your order has been placed successfully! Here are the details of your order:\n\n");
	    message.append("Delivery Address: ").append(address).append("\n\n");
	    message.append("Order Details:\n");
	    message.append(String.format("%-20s %-10s %-10s %-15s\n", "Product Name", "Quantity", "Price/Unit", "Total"));
	    double grandTotal = 0;
		for(Carts product:mycart) {
			/*order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT not null,
    product_id INT not null,
    quantity INT not null,
    price_per_unit DOUBLE not null*/
			
			
			Products productby=product_DAO.getProductDetails(product.getProduct_id());
			double total=productby.getPrice()-((productby.getPrice()*productby.getMax_discount())/100);
			Order_Details order_details=new Order_Details(order.getTransaction_id(),product.getProduct_id(),product.getQuantity(),total,productby.getSeller_id());
			order_DAO.addToOrderDetails(order_details);
			message.append(String.format("%-20s %-10d %-10.2f %-15.2f\n", productby.getProduct_name(), product.getQuantity(), total, total*product.getQuantity()));
			int stock=productby.getStock_quantity()-product.getQuantity();
			cart.DeleteFromMyCart(product.getCart_id());
			seller.updateSellerProductStock(stock,product.getProduct_id());
			grandTotal += total;
			Products productbys=product_DAO.getProductDetails(product.getProduct_id());
			if(productbys.getStock_quantity()<=productbys.getThreshold()) {
				Seller_DAO seller_DAO=new Seller_DAO();
				Seller seller_DTO=seller_DAO.getSellerByID(productbys.getSeller_id());
				String Sto=seller_DTO.getSeller_email();
				String Ssubject="hey, "+seller_DTO.getSeller_name()+" need to refill the products";
				String Smessage = "Product ID: " + productbys.getProduct_id() + "     Name: " + productbys.getProduct_name() +
                        "\nLeft in stock: " + productbys.getStock_quantity() + 
                        "\nPlease! Refill the stock soon, having huge demand.\n\n-Revshop";
				EmailConfig.sendEmail(Sto, Ssubject, Smessage.toString());
			}
			

		}
		message.append("\n");
	    message.append("Total Order Value: ₹").append(String.format("%.2f", grandTotal));
		EmailConfig.sendEmail(to, subject, message.toString());
		

	}
}
