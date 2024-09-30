package SERVICE;
import java.util.List;

import DAO.Categories_DAO;
import DAO.Product_DAO;
import DTO.Customer_DTO;
import DTO.Seller_DTO;
import ENTITY.Categories;
import ENTITY.Products;

public class Product_Service {
	private Product_DAO product_DAO;
	public Product_Service() {
		product_DAO=new Product_DAO();
	}
//	public void AllProductsPerCategoryMain(Categories cat,Customer_DTO customer_dto) {
//		List<List<String>>products=product_DAO.AllProductsPerCategory(cat.getCategory_id());
//		System.out.println("List of product availabe in "+cat.getCategory_name()+ " are :");
//    	System.out.println("PID SID     Product_Name   Description                   Price(Rs.)     image_url");
//    	for (int i = 0; i < products.size(); i++) {
//            List<String> product = products.get(i);
//            System.out.println(product.get(0) + "  " + product.get(1)+"     "+product.get(3)+"     "+product.get(4)+"     "+product.get(5)+"       "+product.get(2));
//        }
//    	System.out.println("-----END-----");
//		
//	}
	public List<Products> allProductsCategorybycustomer(int seller_id) {
		List<Products> productList=product_DAO.allProductsByCategory(seller_id);
		return productList;
	}
	public List<Products> allProductsbycustomer() {
		List<Products> productList=product_DAO.allProducts();
		return productList;
	}
	public void addProductBySeller(Products product) {
		product_DAO.addProduct(product);
	}
	public void ShowCategories() {
		Categories_DAO categories=new Categories_DAO();
    	List<String> categoriesList=categories.showCategories();
    	System.out.println("List of Categories availabe:");
    	for(int i=1;i<=categoriesList.size();i++) {
    		System.out.println(i+". "+categoriesList.get(i-1));
    	}
	}
	public void SellerAddNewProduct(int seller_id,int category_id,Products product) {
		product.setCategory_id(category_id);
		product.setSeller_id(seller_id);
		product_DAO.addProduct(product);
	}
	public List<Products> AllProductsbySellerid(int seller_id) {
		List<Products> productList=product_DAO.ShowAllMyProductperSeller(seller_id);
		return productList;
	}

	public void displayProduct(Products product) {
		Categories_DAO category_DAO=new Categories_DAO();
		System.out.println("category_id    :"+product.getCategory_id());
		System.out.println("category_name  :"+category_DAO.getCategoryById(product.getCategory_id()));
		System.out.println("product_name   :"+product.getProduct_name());
		System.out.println("description    :"+product.getDescription());
		System.out.println("price          :"+product.getPrice());
		System.out.println("stock_quantity :"+product.getStock_quantity());
		System.out.println("image_url      :"+product.getImage_url());
	}
	public void displayProductByProductId(int product_id) {
		Categories_DAO category_DAO=new Categories_DAO();
		Products product=product_DAO.getProductDetails(product_id);
		System.out.println("category_id      :"+product.getCategory_id());
		Categories cat=category_DAO.getCategoryById(product.getCategory_id());
		System.out.println("category_name    :"+cat.getCategory_name());
		System.out.println("product_name     :"+product.getProduct_name());
		System.out.println("description      :"+product.getDescription());
		System.out.println("price            :"+product.getPrice());
		System.out.println("stock_quantity   :"+product.getStock_quantity());
		System.out.println("image_url        :"+product.getImage_url());
	}
	
	public void SellerUpdateProduct(Products product) {
		product_DAO.updateProductUsingID(product);
	}
	public boolean SellerdeleteProduct(int product_id) {
		boolean val=product_DAO.SellerdeleteProduct(product_id);
		return val;
	}
	public Products getProductDetailsForCustomer(int product_id) {
		Products product=product_DAO.getProductDetails(product_id);
		return product;
	}
}
