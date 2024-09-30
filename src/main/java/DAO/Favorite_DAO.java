package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ENTITY.Carts;
import ENTITY.Favorites;
import UTIL.DataBaseConnection;

public class Favorite_DAO {
	private Connection connection;
	public Favorite_DAO() {
		connection=DataBaseConnection.getInstance().getConnection();
	}
	public void addToFavorite(Favorites favorite) {
		try {
			String query="INSERT INTO favorites (customer_id,seller_id,product_id,category_id) VALUES (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, favorite.getCustomer_id());
            ps.setInt(2, favorite.getSeller_id());
            ps.setInt(3, favorite.getProduct_id());
            ps.setInt(4,favorite.getCategory_id());
            ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public Boolean checkProductByCustomerWishlist(int customer_id,int product_id) {
		Favorites favorite=null;
		try {
            String query = "SELECT * from Favorites WHERE customer_id = ? and product_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customer_id);
            ps.setInt(2,product_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	/*favorite_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT not null,
    product_id INT not null,
    seller_id int not null, category_id int not null,*/
            	 favorite = new Favorites(
                        rs.getInt("seller_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("product_id"),
                        rs.getInt("category_id"));
            	 favorite.setFavorite_id(product_id);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return favorite!=null;
	}
	public List<List<String>> viewFavorites(int customer_id) {
		 ResultSet rs = null;
		 List<List<String>> cart = new ArrayList<>();
		 try {
			 	String query = "SELECT * FROM favorites where customer_id=?";
		        PreparedStatement ps = connection.prepareStatement(query);
		        ps.setInt(1, customer_id);
		        rs = ps.executeQuery();
		        while (rs.next()) {
		        	List<String> product = new ArrayList<>();
		        	product.add(rs.getString("seller_id"));
		        	product.add(rs.getString("product_id"));
		        	product.add(rs.getString("category_id"));
		        	product.add(rs.getString("favorite_id"));
		            cart.add(product);	
		        }
		 }catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return cart;
	}
	public void DeleteFromMyFavorites(int favorite_id) {
		try {
            String query = "DELETE FROM favorites WHERE favorite_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,favorite_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public Favorites getByFavorite_id(int favorite_id) {
		Favorites favorite=null;
		try {
            String query = "SELECT * from favorites WHERE favorite_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, favorite_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	 favorite = new Favorites(
                        rs.getInt("seller_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("product_id"),
                        rs.getInt("category_id"));
            	favorite.setFavorite_id(favorite_id);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return favorite;
		
	}
	public List<Favorites> viewFavoriteByCustomer_id(int customer_id) {
		 ResultSet rs = null;
		 List<Favorites> favorite = new ArrayList<>();
		 try {
			 	String query = "SELECT * FROM Favorites where customer_id=? ORDER BY favorite_id DESC";
		        PreparedStatement ps = connection.prepareStatement(query);
		        ps.setInt(1, customer_id);
		        rs = ps.executeQuery();
		        while (rs.next()) {

		        	/*favorite_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT not null,
    product_id INT not null,
    seller_id int not null, category_id int not null,*/
		        	/*this.setSeller_id(seller_id);
		this.setCustomer_id(customer_id);
		this.product_id=product_id;
		this.setCategory_id(category_id);*/
		        	Favorites product=new Favorites(rs.getInt("seller_id"),customer_id,rs.getInt("product_id"),rs.getInt("category_id"));
		        	product.setFavorite_id(rs.getInt("favorite_id"));
		            favorite.add(product);	
		            System.out.println();
		        }
		 }catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return favorite;
	}
}
