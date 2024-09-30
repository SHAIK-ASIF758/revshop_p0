package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ENTITY.Carts;
import ENTITY.Categories;
import UTIL.DataBaseConnection;

public class Cart_DAO {
	private Connection connection;
	public Cart_DAO() {
		connection=DataBaseConnection.getInstance().getConnection();
	}

	public Carts getByCart_id(int cart_id) {
		Carts cart=null;
		try {
            String query = "SELECT * from cart WHERE cart_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cart_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	 cart = new Carts(
                        rs.getInt("seller_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("product_id"),
                        rs.getInt("category_id"),
                        rs.getInt("quantity"));
            	cart.setCart_id(cart_id);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return cart;
		
	}
	public void updateProductQuantityByCartID(int cart_id, int quantity) {
		try {
            String query = "UPDATE cart SET quantity = ? WHERE cart_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, quantity);
            ps.setInt(2, cart_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public void DeleteFromMyCart(int cart_id) {
		try {
            String query = "DELETE FROM cart WHERE cart_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,cart_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public void addToCart(Carts cart) {
		try {
			String query="INSERT INTO cart (customer_id,seller_id,product_id,category_id,quantity) VALUES (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cart.getCustomer_id());
            ps.setInt(2, cart.getSeller_id());
            ps.setInt(3, cart.getProduct_id());
            ps.setInt(4,cart.getCategory_id());
            ps.setInt(5, cart.getQuantity());
            ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public Boolean checkProductByCustomerCart(int customer_id,int product_id) {
		Carts cart=null;
		try {
            String query = "SELECT * from cart WHERE customer_id = ? and product_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customer_id);
            ps.setInt(2,product_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	 cart = new Carts(
                        rs.getInt("seller_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("product_id"),
                        rs.getInt("category_id"),
                        rs.getInt("quantity"));
            	cart.setCart_id( rs.getInt("cart_id"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return cart!=null;
	}
	public List<List<String>> viewCart(int customer_id) {
		 ResultSet rs = null;
		 List<List<String>> cart = new ArrayList<>();
		 try {
			 	String query = "SELECT * FROM cart where customer_id=?";
		        PreparedStatement ps = connection.prepareStatement(query);
		        ps.setInt(1, customer_id);
		        rs = ps.executeQuery();
		        while (rs.next()) {
		        	List<String> product = new ArrayList<>();
		        	product.add(rs.getString("seller_id"));
		        	product.add(rs.getString("product_id"));
		        	product.add(rs.getString("category_id"));
		        	product.add(rs.getString("quantity"));
		        	product.add(rs.getString("cart_id"));
		            cart.add(product);	
		            System.out.println();
		        }
		 }catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return cart;
	}
	public List<Carts> viewCartByCustomer_id(int customer_id) {
		 ResultSet rs = null;
		 List<Carts> cart = new ArrayList<>();
		 try {
			 	String query = "SELECT * FROM cart where customer_id=? ORDER BY cart_id DESC";
		        PreparedStatement ps = connection.prepareStatement(query);
		        ps.setInt(1, customer_id);
		        rs = ps.executeQuery();
		        while (rs.next()) {

		        	/*this.setSeller_id(seller_id);
		this.setCustomer_id(customer_id);
		this.product_id=product_id;
		this.setCategory_id(category_id);
		this.setQuantity(quantity);*/
		        	Carts product=new Carts(rs.getInt("seller_id"),customer_id,rs.getInt("product_id"),rs.getInt("category_id"),rs.getInt("quantity"));
		        	product.setCart_id(rs.getInt("cart_id"));
		            cart.add(product);	
		        }
		 }catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return cart;
	}
}
