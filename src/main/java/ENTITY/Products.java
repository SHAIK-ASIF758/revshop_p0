package ENTITY;

public class Products {
	private int product_id;
	private int category_id;
    private int seller_id;
	private String product_name;
	private String description;
	private double price;
	private int stock_quantity;
	private String image_url ;
	private int threshold;
	private  Double max_discount;
	public Products(String product_name,String description,double price,int stock_quantity,String image_url,Double max_discount) {
		this.product_name=product_name;
		this.description=description;
		this.price=price;
		this.stock_quantity=stock_quantity;
		this.image_url=image_url;
		this.setMax_discount(max_discount);
	} 
	


    public String getProduct_name() {
        return product_name;
    }


    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public int getStock_quantity() {
        return stock_quantity;
    }


    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }



	public int getProduct_id() {
		return product_id;
	}



	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getCategory_id() {
		return category_id;
	}



	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}



	public int getSeller_id() {
		return seller_id;
	}



	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}



	public String getImage_url() {
		return image_url;
	}



	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}



	public int getThreshold() {
		return threshold;
	}



	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public Double getMax_discount() {
		return max_discount;
	}



	public void setMax_discount(Double max_discount) {
		this.max_discount = max_discount;
	}
}
