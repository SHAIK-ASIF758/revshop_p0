package DTO;

public class Customer_DTO {
	private int customer_id;
	private String name;
	private long phone_number;
	private String email;
	private double wallet_balance;
	private String address;
	public Customer_DTO(int customer_id,String name,long phone_number,String address,String email,double wallet_balance) {
		this.customer_id=customer_id;
		this.name=name;
		this.phone_number=phone_number;
		this.address=address;
		this.email=email;
		this.wallet_balance=wallet_balance;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id=customer_id;
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
	public double getWallet_balance() {
		return wallet_balance;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public void setWallet_balance(double balance) {
		this.wallet_balance=balance;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
}
