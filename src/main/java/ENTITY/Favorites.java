package ENTITY;

public class Favorites {
	private int favorite_id;
	private int customer_id;
	private int seller_id;
	private int product_id;
	private int category_id;
	public Favorites(int seller_id,int customer_id,int product_id,int category_id) {
		this.setSeller_id(seller_id);
		this.setCustomer_id(customer_id);
		this.product_id=product_id;
		this.setCategory_id(category_id);
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getFavorite_id() {
		return favorite_id;
	}
	public void setFavorite_id(int favorite_id) {
		this.favorite_id = favorite_id;
	}
}

