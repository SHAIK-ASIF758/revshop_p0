package ENTITY;

import java.sql.Timestamp;

public class Display {
	private int category_id;
    private int seller_id;
	private String product_name;
	private int product_id;
	private String image_url ;
	private int quantity;
	private double price_per_unit;
	private int customer_id;
	private double total_amount;
	private String delivery_address;
	private String status;
	private Timestamp order_date;
	private int order_id;
	public Display(int category_id,int seller_id,String product_name,int product_id,String image_url ,int quantity,double price_per_unit,int customer_id,double total_amount,String delivery_address,String status,Timestamp order_date) {
		this.category_id = category_id;
		this.seller_id = seller_id;
		this.product_name = product_name;
		this.product_id = product_id;
		this.image_url = image_url;
		this.quantity = quantity;
		this.price_per_unit = price_per_unit;
		this.customer_id = customer_id;
		this.total_amount = total_amount;
		this.delivery_address = delivery_address;
		this.status = status;
		this.order_date = order_date;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
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
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public String getDelivery_address() {
		return delivery_address;
	}
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
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
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
}
