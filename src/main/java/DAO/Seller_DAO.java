package DAO;

import java.sql.*;

import DTO.Seller_DTO;
//import ENTITY.Categories;
import ENTITY.Customer;
import ENTITY.Products;
//import ENTITY.Products;
import ENTITY.Seller;
import UTIL.DataBaseConnection;

public class Seller_DAO {
	
	private Connection connection;
	public Seller_DAO() {
		connection=DataBaseConnection.getInstance().getConnection();
	}

//	public void addProductToManage_inventory(Products product,Seller seller) {
//		try {
//			String query="insert into manage_inventory(seller_id,product_id,price,stock_quantity) values(?,?,?,?)";
//			PreparedStatement ps=connection.prepareStatement(query);
//			ps.setInt(1, product.get);
//            ps.setString(2, seller.getSeller_email());
//            ps.setLong(3, seller.getSeller_phone_number());
//            ps.setString(4, seller.getSeller_address());
//            ps.setString(5, seller.getSeller_password());
//            ps.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public void addSeller(Seller seller) {
		try {
			String query="INSERT INTO sellers (seller_name, email,phone_number,address, password) VALUES (?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, seller.getSeller_name());
            ps.setString(2, seller.getSeller_email());
            ps.setLong(3, seller.getSeller_phone_number());
            ps.setString(4, seller.getSeller_address());
            ps.setString(5, seller.getSeller_password());
            ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public Seller getSellerByEmail(String email) {
        Seller seller = null;
        try {
            String query = "SELECT * from sellers WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                seller = new Seller(
                        rs.getString("seller_name"),
                        rs.getString("email"),
                        rs.getLong("phone_number"),
                        rs.getString("address"),
                        rs.getDouble("total_earning"),
                        rs.getString("password"),
                        rs.getDouble("current_month_earning"));
                seller.setSeller_id(rs.getInt("seller_id"));
                seller.setCurrent_month_item_sold(rs.getInt("current_month_item_sold"));
                seller.setTotal_item_sold(rs.getInt("total_item_sold"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seller;
    }
	public void updateSeller(Seller seller) {
        try {
            String query = "UPDATE sellers SET seller_name = ?, email = ?,phone_number=?,address=?,total_earning=?, password = ?,current_month_earning=? WHERE seller_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, seller.getSeller_name());
            ps.setString(2, seller.getSeller_email());
            ps.setLong(3, seller.getSeller_phone_number());
            ps.setString(4, seller.getSeller_address());
            ps.setDouble(5, seller.getTotal_earning());
            ps.setString(6,seller.getSeller_password());
            ps.setDouble(7, seller.getCurrent_month_earning());
            ps.setInt(8, seller.getSeller_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public void updateSellerbyDTO(Seller_DTO seller) {
        try {
            String query = "UPDATE sellers SET seller_name = ?, email = ?,phone_number=?,address=?,total_earning=? WHERE seller_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, seller.getSeller_name());
            ps.setString(2, seller.getSeller_email());
            ps.setLong(3, seller.getSeller_phone_number());
            ps.setString(4, seller.getSeller_address());
            ps.setDouble(5, seller.getTotal_earning());
            ps.setInt(6, seller.getSeller_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public void updateSellerProductStock(int stock,int product_id) {
		try {
            String query = "UPDATE products SET stock_quantity=? WHERE product_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,stock);
            ps.setInt(2, product_id);
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public Seller getSellerByID(int id) {
        Seller seller = null;
        try {
            String query = "SELECT * from sellers WHERE seller_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	seller = new Seller(
                        rs.getString("seller_name"),
                        rs.getString("email"),
                        rs.getLong("phone_number"),
                        rs.getString("address"),
                        rs.getDouble("total_earning"),
                        rs.getString("password"),
                        rs.getDouble("current_month_earning"));
                seller.setSeller_id(rs.getInt("seller_id"));
                seller.setCurrent_month_item_sold(rs.getInt("current_month_item_sold"));
                seller.setTotal_item_sold(rs.getInt("total_item_sold"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seller;
    }

}
