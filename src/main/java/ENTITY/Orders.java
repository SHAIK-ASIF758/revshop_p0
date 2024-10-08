package ENTITY;

import java.sql.Timestamp;

/*order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT not null,
    total_amount DOUBLE not null,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) not null default "placed",
    payment_method VARCHAR(50) not null default "wallet",
    delivery_address TEXT not null*/
public class Orders {
	private int transaction_id;
	private int customer_id;
	private double total_amount;
	private String delivery_address;
	private Timestamp order_date;
	public Orders(int customer_id,double total_amount,String delivery_address) {
		this.customer_id = customer_id;
		this.total_amount = total_amount;
		this.delivery_address = delivery_address;
	}
	public String getDelivery_address() {
		return delivery_address;
	}
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}

}
