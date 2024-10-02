package SERVICE;

import java.util.List;
import DAO.Cart_DAO;
import DAO.Favorite_DAO;
import DTO.Customer_DTO;
import ENTITY.Carts;
import ENTITY.Favorites;

public class Cart_Service {
	private Cart_DAO cart_DAO;
	public Cart_Service() {
		cart_DAO=new Cart_DAO();
	}
	public void ShowCartProductById(int cart_id) {
		Carts cart=cart_DAO.getByCart_id(cart_id);
//		Product_DAO product_DAO=new Product_DAO();
//		Products product=product_DAO.getProductDetails(cart.getProduct_id());
//		InstaMart_Product instamart_product=new InstaMart_Product();
//		instamart_product.displayProduct(product);
		Product_Service instamart_product=new Product_Service();
		instamart_product.displayProductByProductId(cart.getProduct_id());
		System.out.println("Current Quantity in Cart: "+cart.getQuantity());
	}
	public void updateProductQuantityInCart(int cart_id,int quantity) {
		cart_DAO.updateProductQuantityByCartID(cart_id, quantity);
	}
	public void addToCartByCustomer(Carts cart) {
		cart_DAO.addToCart(cart);
	}
	public void removeCartFromList(int cart_id) {
		cart_DAO.DeleteFromMyCart(cart_id);
	}
	public void viewCartByCustomer(Customer_DTO customer_DTO) {
		List<List<String>> cart=cart_DAO.viewCart(customer_DTO.getCustomer_id());
		System.out.println("------------------My Cart :------------------");
		Product_Service instamart_product=new Product_Service();
    	for (int i = 0; i < cart.size(); i++) {
            List<String> product = cart.get(i);
            int product_id=Integer.parseInt(product.get(1));
    		System.out.println("Cart_id          :"+product.get(4));
            instamart_product.displayProductByProductId(product_id);
    		System.out.println("Quantity         :"+product.get(3));
    		System.out.println("");
    		System.out.println("");
        }
    	System.out.println("--------------------END----------------------");
	}
	public void moveProductToMyFavorites(int cart_id) {
		Carts cart=cart_DAO.getByCart_id(cart_id);
		/*this.setSeller_id(seller_id);
		this.setCustomer_id(customer_id);
		this.product_id=product_id;
		this.setCategory_id(category_id);
		this.setQuantity(quantity);c*/
		Favorites product=new Favorites(cart.getSeller_id(),cart.getCustomer_id(),cart.getProduct_id(),cart.getCategory_id());
		Favorite_DAO favorite_DAO=new Favorite_DAO();
		if(!favorite_DAO.checkProductByCustomerWishlist(cart.getCustomer_id(),cart.getProduct_id())) {
			favorite_DAO.addToFavorite(product);
		}
		cart_DAO.DeleteFromMyCart(cart_id);
	}
}
