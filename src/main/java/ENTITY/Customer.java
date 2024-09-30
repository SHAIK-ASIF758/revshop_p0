

package ENTITY;

public class Customer {
	private int customer_id;
	private String name;
	private long phone_number;
	private String email;
	private String password;
	private double wallet_balance;
	private String address;
	
	public Customer(String name,long phone_number,String address,String email,String password,double wallet_balance) {
		this.name=name;
		this.phone_number=phone_number;
		this.address=address;
		this.email=email;
		this.password=password;
		this.wallet_balance=wallet_balance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address=address;
	}
	public long getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(long phone_number) {
		this.phone_number=phone_number;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public double getWallet_balance() {
		return wallet_balance;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public void setWallet_balance(double balance) {
		this.wallet_balance=balance;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int cust_id) {
		this.customer_id = cust_id;
	}
	
}

