package ENTITY;

public class Order_Details {
/*order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT not null,
    product_id INT not null,
    quantity INT not null,
    price_per_unit DOUBLE not null*/
	private int order_detail_id;
	private int transaction_id;
	private int product_id;
	private int quantity;
	private double price_per_unit;
	private int seller_id;
	private String status;
	public Order_Details(int transaction_id,int product_id,int quantity,double price_per_unit,int seller_id) {
		this.transaction_id = transaction_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price_per_unit = price_per_unit;
		this.seller_id = seller_id;
	}
	public int getOrder_detail_id() {
		return order_detail_id;
	}
	public void setOrder_detail_id(int order_detail_id) {
		this.order_detail_id = order_detail_id;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice_per_unit() {
		return price_per_unit;
	}
	public void setPrice_per_unit(double price_per_unit) {
		this.price_per_unit = price_per_unit;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
