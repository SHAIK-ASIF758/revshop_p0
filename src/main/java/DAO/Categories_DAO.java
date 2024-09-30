package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ENTITY.Categories;
import ENTITY.Products;
import ENTITY.Seller;
import UTIL.DataBaseConnection;

public class Categories_DAO {
	private Connection connection;
	public Categories_DAO() {
		connection=DataBaseConnection.getInstance().getConnection();
	}
	public List<Categories> allCategories(){
		ResultSet rs = null;
		Categories category=null;
	    List<Categories> categories = new ArrayList<>();
	    try {
	        String query = "SELECT category_id,category_name  FROM category";
	        PreparedStatement ps = connection.prepareStatement(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	        	category = new Categories(
                        rs.getInt("category_id"),
                        rs.getString("category_name"));
	        	categories.add(category);	
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return categories;
	}
	public List<String> showCategories() {
	    ResultSet rs = null;
	    List<String> categories = new ArrayList<>();
	    
	    try {
	        String query = "SELECT category_name FROM category";
	        PreparedStatement ps = connection.prepareStatement(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            categories.add(rs.getString("category_name"));
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	        
	    return categories;
	}

	public Categories getCategoryByName(String name) {
		Categories category=null;
		try {
            String query = "SELECT * from category WHERE category_name = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	category = new Categories(
                        rs.getInt("category_id"),
                        rs.getString("category_name"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
	public Categories getCategoryById(int id) {
		Categories category=null;
		try {
            String query = "SELECT * from category WHERE category_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	category = new Categories(
                        rs.getInt("category_id"),
                        rs.getString("category_name"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
		
}

