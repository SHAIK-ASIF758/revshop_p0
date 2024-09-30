package SERVICE;

import DAO.Customer_DAO;
import DTO.Customer_DTO;
import ENTITY.Customer;
import Exceptions.UserNotFoundException;

public class Customer_Service {
	private Customer_DAO customer_DAO;
	public Customer_Service() {
		customer_DAO=new Customer_DAO();
	}
	public void registerCustomer(Customer customer2) {
//		Customer customer=new Customer(customer2.getName(),customer2.getPhone_number(),customer2.getAddress(), customer2.getEmail(), customer2.getPassword(),customer2.getWallet_balance());
		customer_DAO.addCustomer(customer2);
	}
	public Customer_DTO login(String email,String password) throws UserNotFoundException{
		Customer customer=customer_DAO.getCustomerByEmail(email);
		if(customer==null) {
			throw new UserNotFoundException("Invalid Email not found");
		}
		else {
			if(!customer.getPassword().equals(password)) {
				throw new UserNotFoundException("Wrong Password");
			}
		}//String name,long phone_number,String address,String email,double wallet_balance
		return new Customer_DTO(customer.getCustomer_id() ,customer.getName(),customer.getPhone_number(),customer.getAddress(), customer.getEmail(),customer.getWallet_balance());
	}
}
