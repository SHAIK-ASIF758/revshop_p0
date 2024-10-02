package SERVICE;

import java.util.List;

import DAO.Cart_DAO;
import DAO.Favorite_DAO;
import DTO.Customer_DTO;
import ENTITY.Carts;
import ENTITY.Favorites;

public class Favorite_Service {
	private Favorite_DAO favorite_DAO;
	public Favorite_Service() {
		favorite_DAO=new Favorite_DAO();
	}
	public void addToFavoriteByCustomer(Favorites favorite) {
		favorite_DAO.addToFavorite(favorite);
	}
	public void viewFavoriteByCustomer(Customer_DTO customer_DTO) {
		List<List<String>> cart=favorite_DAO.viewFavorites(customer_DTO.getCustomer_id());
		System.out.println("------------------My Favorites :------------------");
		Product_Service product_service=new Product_Service();
    	for (int i = 0; i < cart.size(); i++) {
            List<String> product = cart.get(i);
            int product_id=Integer.parseInt(product.get(1));
    		System.out.println("Favorite_id          :"+product.get(3));
    		product_service.displayProductByProductId(product_id);
            System.out.println("");
            System.out.println("");
        }
    	System.out.println("--------------------END----------------------");
	}
	public void removeFavoriteFromList(int favorite_id) {
		favorite_DAO.DeleteFromMyFavorites(favorite_id);
	}
	public void moveProductToMyCart(int favorite_id) {
		Favorites favorite=favorite_DAO.getByFavorite_id(favorite_id);
		/*this.setSeller_id(seller_id);
		this.setCustomer_id(customer_id);
		this.product_id=product_id;
		this.setCategory_id(category_id);
		this.setQuantity(quantity);*/
		Carts product=new Carts(favorite.getSeller_id(),favorite.getCustomer_id(),favorite.getProduct_id(),favorite.getCategory_id(),1);
		Cart_Service instamart_cart=new Cart_Service();
		Cart_DAO cart_DAO=new Cart_DAO();
		if(!cart_DAO.checkProductByCustomerCart(favorite.getCustomer_id(), favorite.getProduct_id())) {
			instamart_cart.addToCartByCustomer(product);
		}
		favorite_DAO.DeleteFromMyFavorites(favorite_id);
	}
}
