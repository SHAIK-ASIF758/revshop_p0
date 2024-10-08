package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DTO.Customer_DTO;
import ENTITY.Order_Details;
import ENTITY.Orders;
import ENTITY.Products;
import UTIL.DataBaseConnection;

public class Order_DAO {
	private Connection connection;
	public Order_DAO() {
		connection=DataBaseConnection.getInstance().getConnection();
	}
/*order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT not null,
    total_amount DOUBLE not null,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) not null default "placed",
    payment_method VARCHAR(50) not null default "wallet",
    delivery_address TEXT not null*/
	public void updateOrderStatus(int order_detail_id,String Status) {
		try {
	        String query = "update Order_Details set status=? where order_detail_id=?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, Status);
	        ps.setInt(2, order_detail_id);
	        
	        
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void addToOrders(Customer_DTO customer_DTO, double total_amount, String delivery_address) {
	    try {
	        String query = "INSERT INTO Orders (customer_id, total_amount, delivery_address) VALUES (?, ?, ?)";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1, customer_DTO.getCustomer_id());
	        ps.setDouble(2, total_amount);
	        ps.setString(3, delivery_address);
	        
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public Orders getLastOrderID(int customer_id) {
		Orders lastOrder = null;
	    try {
	        String query = "SELECT * FROM Orders WHERE customer_id = ? ORDER BY order_date DESC LIMIT 1";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1, customer_id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	        	/*this.customer_id = customer_id;
		this.total_amount = total_amount;
		this.delivery_address = delivery_address;*/
	            lastOrder = new Orders(rs.getInt("customer_id"),rs.getDouble("total_amount"),rs.getString("delivery_address"));
	            lastOrder.setTransaction_id(rs.getInt("order_id"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lastOrder;	
	}
	/*order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT not null,
    product_id INT not null,
    quantity INT not null,
    price_per_unit DOUBLE not null*/
	public void addToOrderDetails(Order_Details order) {
	    try {
	        String query = "INSERT INTO Order_Details (order_id, product_id, quantity, price_per_unit,seller_id) VALUES (?,?,?,?,?)";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1, order.getTransaction_id());
	        ps.setInt(2, order.getProduct_id());
	        ps.setInt(3, order.getQuantity());
	        String formattedPrice = String.format("%.2f", order.getPrice_per_unit());
	        double pricePerUnit = Double.parseDouble(formattedPrice);
	        ps.setDouble(4, pricePerUnit);
	        ps.setInt(5, order.getSeller_id());
	        ps.executeUpdate();  
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public List<Order_Details> viewProductOrdered_DetailsByCustomer(int order_id){
		ResultSet rs = null;
		List<Order_Details> products = new ArrayList<>();
		try {
	        String query = "SELECT * FROM Order_Details where order_id=? ORDER BY order_detail_id DESC";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1,order_id);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	        	/*order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT not null,
    product_id INT not null,
    quantity INT not null,
    price_per_unit DOUBLE not null*/
	       
	        	Order_Details order=new Order_Details(rs.getInt("order_id"),rs.getInt("product_id"),rs.getInt("quantity"),rs.getDouble("price_per_unit"),rs.getInt("seller_id"));
	        	order.setOrder_detail_id(rs.getInt("order_detail_id"));
	        	order.setStatus(rs.getString("status"));
	            products.add(order);	
	            
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	        
	    return products;
	}
	public List<Orders> viewProductOrderedByCustomer(int customer_id){
		ResultSet rs = null;
		List<Orders> products = new ArrayList<>();
		try {
	        String query = "SELECT * FROM orders where customer_id=? ORDER BY order_id DESC";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1,customer_id);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	        	/*this.customer_id = customer_id;
		this.total_amount = total_amount;
		this.delivery_address = delivery_address;*/
	        	
	        	Orders order=new Orders(rs.getInt("customer_id"),rs.getDouble("total_amount"),rs.getString("delivery_address"));
	        	order.setTransaction_id(rs.getInt("order_id"));
	        	order.setOrder_date( rs.getTimestamp("order_date"));
	            products.add(order);	
	            
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	        
	    return products;
	}
	public Orders viewByOrdersTransactionID(int order_id) {
		ResultSet rs = null;
		Orders order1=null;
		try {
	        String query = "SELECT * FROM orders where order_id=?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1,order_id);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	        	/*this.customer_id = customer_id;
		this.total_amount = total_amount;
		this.delivery_address = delivery_address;*/
	        	
	        	order1=new Orders(rs.getInt("customer_id"),rs.getDouble("total_amount"),rs.getString("delivery_address"));
	        	order1.setTransaction_id(rs.getInt("order_id"));
	        	order1.setOrder_date( rs.getTimestamp("order_date"));
	            
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	        
	    return order1;
	}
	public List<Order_Details> viewProductOrderedBySeller(int seller_id){
		ResultSet rs = null;
		List<Order_Details> products = new ArrayList<>();
		try {
	        String query = "SELECT * FROM Order_Details where seller_id=? ORDER BY order_detail_id DESC";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1,seller_id);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	        	/*this.customer_id = customer_id;
		this.total_amount = total_amount;
		this.delivery_address = delivery_address;*/
	        	Order_Details order=new Order_Details(rs.getInt("order_id"),
	        			rs.getInt("product_id"),rs.getInt("quantity"),
	        			rs.getDouble("price_per_unit"),rs.getInt("seller_id"));
	        	order.setStatus(rs.getString("status"));
	        	order.setOrder_detail_id(rs.getInt("order_detail_id"));
	            products.add(order);	
	            
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	        
	    return products;
		
	}
	
}
