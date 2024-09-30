package DTO;
public class Seller_DTO {
	private int seller_id;
	private String seller_name;
	private String seller_email;
	private long seller_phone_number;
	private String seller_address;
	private double total_earning;
	private double current_month_earning;
	private int total_item_sold;
	private int current_month_item_sold;
	
	public Seller_DTO(int seller_id,String seller_name,String seller_email,long seller_phone_number,String seller_address,double total_earning,double current_month_earning,int total_item_sold,int current_month_item_sold) {
		this.seller_id=seller_id;
		this.seller_name=seller_name;
		this.seller_email=seller_email;
		this.seller_phone_number=seller_phone_number;
		this.seller_address=seller_address;
		this.total_earning=total_earning;
		this.current_month_earning=current_month_earning;
		this.total_item_sold=total_item_sold;
		this.current_month_item_sold=current_month_item_sold;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id=seller_id;
	}
	public double getTotal_earning() {
		return total_earning;
	}
	public double getCurrent_month_earning() {
		return current_month_earning;
	}
	public void setTotal_earning(double total_earning) {
		this.total_earning=total_earning;
	}
	public void setCurrent_month_earning(double current_month_earning) {
		this.current_month_earning=current_month_earning;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public long getSeller_phone_number() {
		return seller_phone_number;
	}
	public String getSeller_address() {
		return seller_address;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name=seller_name;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email=seller_email;
	}
	public void setSeller_phone_number(long seller_phone_number) {
		this.seller_phone_number=seller_phone_number;
	}
	public void setSeller_address(String seller_address) {
		this.seller_address=seller_address;
	}
	public int getTotal_item_sold() {
		return total_item_sold;
	}
	public void setTotal_item_sold(int total_item_sold) {
		this.total_item_sold = total_item_sold;
	}
	public int getCurrent_month_item_sold() {
		return current_month_item_sold;
	}
	public void setCurrent_month_item_sold(int current_month_item_sold) {
		this.current_month_item_sold = current_month_item_sold;
	}
}

