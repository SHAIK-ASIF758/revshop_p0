package SERVICE;

import DAO.Seller_DAO;
import DTO.Seller_DTO;
//import ENTITY.Products;
import ENTITY.Seller;
import Exceptions.UserNotFoundException;

public class Seller_Service {
	private Seller_DAO seller_DAO;
	public Seller_Service() {
		seller_DAO=new Seller_DAO();
	}//String seller_name,String seller_email,long seller_phone_number,String seller_address,double total_earning,String seller_password,double current_month_earning
	public void registerSeller(Seller seller2) {
		Seller seller=new Seller(seller2.getSeller_name(),seller2.getSeller_email(),seller2.getSeller_phone_number(),seller2.getSeller_address(),seller2.getTotal_earning(),seller2.getSeller_password(),seller2.getCurrent_month_earning());
		seller_DAO.addSeller(seller);
	}

	public Seller_DTO login(String email,String password) throws UserNotFoundException{
		Seller seller=seller_DAO.getSellerByEmail(email);
		if(seller==null) {
			throw new UserNotFoundException("Invalid Email not found");
		}
		else {
			if(!seller.getSeller_password().equals(password)) {
				throw new UserNotFoundException("Wrong Password");
			}
		}
		return new Seller_DTO(seller.getSeller_id(),seller.getSeller_name(),seller.getSeller_email(),seller.getSeller_phone_number(),seller.getSeller_address(),seller.getTotal_earning(),seller.getCurrent_month_earning(),seller.getTotal_item_sold(),seller.getCurrent_month_item_sold());
	}
	public Seller_DTO updateSellerProfile(Seller seller) {
		seller_DAO.updateSeller(seller);
		return new Seller_DTO(seller.getSeller_id(),seller.getSeller_name(),seller.getSeller_email(),seller.getSeller_phone_number(),seller.getSeller_address(),seller.getTotal_earning(),seller.getCurrent_month_earning(),seller.getTotal_item_sold(),seller.getCurrent_month_item_sold());
	}
	public Seller_DTO updateSellerProfile(Seller_DTO seller) {
		seller_DAO.updateSellerbyDTO(seller);
		return new Seller_DTO(seller.getSeller_id(),seller.getSeller_name(),seller.getSeller_email(),seller.getSeller_phone_number(),seller.getSeller_address(),seller.getTotal_earning(),seller.getCurrent_month_earning(),seller.getTotal_item_sold(),seller.getCurrent_month_item_sold());
	}

}
