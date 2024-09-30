package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ENTITY.Customer;
import ENTITY.Products;
import UTIL.DataBaseConnection;
 public class Product_DAO {
	private Connection connection;
	public Product_DAO() {
		connection=DataBaseConnection.getInstance().getConnection();
	}
	public  boolean SellerdeleteProduct(int productId) {
        boolean deleted = false;
        try {
            String query = "DELETE FROM products WHERE product_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
	public void addProduct(Products product) {
		try {
			String query="INSERT INTO products (category_id,seller_id,product_name,description,price,stock_quantity,image_url,threshold,max_discount) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, product.getCategory_id());
			ps.setInt(2, product.getSeller_id());
            ps.setString(3,product.getProduct_name());
            ps.setString(4, product.getDescription());
            ps.setDouble(5, product.getPrice());
            ps.setInt(6, product.getStock_quantity());
            ps.setString(7, product.getImage_url());
            ps.setInt(8, product.getThreshold());
            ps.setDouble(9, product.getMax_discount());
            ps.executeUpdate();	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public Products getProductDetails(int product_id) {
		Products product = null;
        try {
            String query = "SELECT * FROM products WHERE product_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Products(
                        rs.getString("product_name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("image_url"),
                        rs.getDouble("max_discount"));
                product.setSeller_id(rs.getInt("seller_id"));
                product.setProduct_id(rs.getInt("product_id"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setThreshold(rs.getInt("threshold"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
	}
	public List<Products> allProducts(){
		ResultSet rs = null;
		List<Products> products = new ArrayList<>();
		try {
	        String query = "SELECT * FROM products";
	        PreparedStatement ps = connection.prepareStatement(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	        	Products product=new Products(rs.getString("product_name"),rs.getString("description"),rs.getDouble("price"),rs.getInt("stock_quantity"),rs.getString("image_url"),rs.getDouble("max_discount"));
	        	product.setProduct_id(rs.getInt("product_id"));
	        	product.setCategory_id(rs.getInt("category_id"));
	        	product.setSeller_id(rs.getInt("seller_id"));
	        	product.setThreshold(rs.getInt("threshold"));
	            products.add(product);	
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	        
	    return products;
	}
	public List<Products> allProductsByCategory(int category_id){
		ResultSet rs = null;
		List<Products> products = new ArrayList<>();
		try {
	        String query = "SELECT * FROM products where category_id=?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1, category_id);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	        	Products product=new Products(rs.getString("product_name"),rs.getString("description"),rs.getDouble("price"),rs.getInt("stock_quantity"),rs.getString("image_url"),rs.getDouble("max_discount"));
	        	product.setProduct_id(rs.getInt("product_id"));
	        	product.setCategory_id(rs.getInt("category_id"));
	        	product.setSeller_id(rs.getInt("seller_id"));
	        	product.setThreshold(rs.getInt("threshold"));
	            products.add(product);	
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	        
	    return products;
	}
//	public List<List<String>> AllProductsPerCategory(int category_id){
//		ResultSet rs = null;
//	    List<List<String>> products = new ArrayList<>();
//	    try {
//	        String query = "SELECT * FROM products where category_id=?";
//	        PreparedStatement ps = connection.prepareStatement(query);
//	        ps.setInt(1, category_id);
//	        rs = ps.executeQuery();
//	        while (rs.next()) {
//	        	List<String> product = new ArrayList<>();
//	        	product.add(rs.getString("product_id"));
//	        	product.add(rs.getString("seller_id"));
//	        	product.add(rs.getString("image_url"));
//	            product.add(rs.getString("product_name"));
//	            product.add(rs.getString("description"));
//	            product.add(rs.getString("price"));
//	            product.add(rs.getString("max_discount"));
//	            products.add(product);
//	        }
//	        
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	        
//	    return products;
//	}
	public List<Products> ShowAllMyProductperSeller(int Seller_id) {
		ResultSet rs = null;
	    List<Products> products = new ArrayList<>();
	    try {
	        String query = "SELECT product_name,description,price,stock_quantity,image_url,product_id,category_id,seller_id,threshold,max_discount FROM products where seller_id=?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1, Seller_id);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	        	//System.out.println(rs.getString("product_name")+rs.getString("description")+rs.getDouble("price")+rs.getInt("stock_quantity")+rs.getString("image_url"));
	        	Products product=new Products(rs.getString("product_name"),rs.getString("description"),rs.getDouble("price"),rs.getInt("stock_quantity"),rs.getString("image_url"),rs.getDouble("max_discount"));
	        	product.setProduct_id(rs.getInt("product_id"));
	        	product.setCategory_id(rs.getInt("category_id"));
	        	product.setSeller_id(rs.getInt("seller_id"));
	        	product.setThreshold(rs.getInt("threshold"));
	        	
	            products.add(product);	
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return products;
	}
	
	public void updateProductUsingID(Products product) {
		try {
            String query = "UPDATE products SET product_name = ?, description = ?,price=?,stock_quantity=?,image_url=?,threshold=?,max_discount=? WHERE product_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            
            ps.setString(1, product.getProduct_name());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock_quantity());
            ps.setString(5, product.getImage_url());
            ps.setInt(6, product.getThreshold());
            ps.setDouble(7,product.getMax_discount());
            ps.setInt(8, product.getProduct_id());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
}
