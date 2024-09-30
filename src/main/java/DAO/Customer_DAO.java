package DAO;
import java.sql.*;

import DTO.Customer_DTO;
import ENTITY.Customer;
import UTIL.DataBaseConnection;



public class Customer_DAO {
	private Connection connection;
	public Customer_DAO() {
		connection=DataBaseConnection.getInstance().getConnection();
	}
	public void addCustomer(Customer customer) {
		try {
			String query="INSERT INTO customers (name, email,phone_number,address, password,Wallet_balance) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setLong(3, customer.getPhone_number());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getPassword());
            ps.setDouble(6, customer.getWallet_balance());
            ps.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public Customer getCustomerByEmail(String email) {
        Customer customer = null;
        try {
            String query = "SELECT * FROM customers WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(
                        rs.getString("name"),
                        rs.getLong("phone_number"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getDouble("wallet_balance"));
                customer.setCustomer_id(rs.getInt("customer_id"));
}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
	public void updateCustomerWallet(double balance,int customer_id) {
		try {
            String query = "UPDATE customers SET Wallet_balance = ? WHERE customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDouble(1, balance);
            ps.setInt(2, customer_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public void updateCustomer(Customer customer) {
        try {
            String query = "UPDATE customers SET name = ?, email = ?,address=?,phone_number=?, password = ?, Wallet_balance = ? WHERE customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getAddress());
            ps.setLong(4, customer.getPhone_number());
            ps.setString(5, customer.getPassword());
            ps.setDouble(6, customer.getWallet_balance());
            ps.setInt(7, customer.getCustomer_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public void updateCustomerbyDTO(Customer_DTO customer) {
        try {
        	String query = "UPDATE customers SET name = ?, email = ?,address=?,phone_number=?, password = ?, Wallet_balance = ? WHERE customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getAddress());
            ps.setLong(4, customer.getPhone_number());
            ps.setDouble(5, customer.getWallet_balance());
            ps.setInt(6, customer.getCustomer_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
