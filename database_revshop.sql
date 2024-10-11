drop database revshop0;
create database revshop0;
use Revshop;
CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) not null,
    email VARCHAR(100) unique not null,
    phone_number long not null,
    address TEXT,
    wallet_balance double default 0.00,
    password varchar(25) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for storing product information
CREATE TABLE Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT  not null,
        seller_id INT not null,
    product_name VARCHAR(100) not null,
    description TEXT not null,
    price DECIMAL(10, 2) not null default 0.00,
    stock_quantity INT not null default 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    image_url varchar(5000) not null,
    threshold int not null default 0,
    max_discount double not null default 0
);

CREATE TABLE Cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT not null,
    seller_id int not null,
    product_id INT not null,
    category_id int not null,
    quantity INT not null default 1,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) not null
);
CREATE TABLE Favorites (
    favorite_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT not null,
    product_id INT not null,
    seller_id int not null, category_id int not null,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);




CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT not null,
    total_amount DOUBLE not null,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method VARCHAR(50) not null default "wallet",
    delivery_address TEXT not null
);

CREATE TABLE Order_Details (
    order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT not null,
    product_id INT not null,
    quantity INT not null,
    seller_id int not null,
    status VARCHAR(50) not null default "placed",
    price_per_unit DOUBLE not null
);


CREATE TABLE Sellers (
    seller_id INT AUTO_INCREMENT PRIMARY KEY,
    seller_name VARCHAR(100) not null,
    email VARCHAR(100) not null,
    phone_number MEDIUMTEXT not null,
    address TEXT not null,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_earning DOUBLE not null DEFAULT 0,
    password VARCHAR(20) NOT NULL,
    current_month_earning DOUBLE DEFAULT 0.00,
    total_item_sold int not null default 0,
    current_month_item_sold int not null default 0,
    is_active TINYINT(1) DEFAULT 1
);

alter table products add FOREIGN KEY (category_id) REFERENCES Category(category_id) on update cascade on delete cascade;
alter table products add FOREIGN KEY (seller_id) REFERENCES Sellers(seller_id) on update cascade on delete cascade;
alter table Cart add FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) on update cascade on delete cascade;
alter table Cart add FOREIGN KEY (product_id) REFERENCES Products(product_id) on update cascade on delete cascade;
alter table Order_Details add FOREIGN KEY (order_id) REFERENCES Orders(order_id) on update cascade on delete cascade;
alter table Order_Details add  FOREIGN KEY (product_id) REFERENCES Products(product_id) on update cascade on delete cascade;
alter table Orders add FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) on update cascade on delete cascade;
alter table Favorites add FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) on update cascade on delete cascade;
alter table Favorites add FOREIGN KEY (product_id) REFERENCES Products(product_id) on update cascade on delete cascade;
alter table Favorites add FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) on update cascade on delete cascade;
alter table Favorites add FOREIGN KEY (product_id) REFERENCES Products(product_id) on update cascade on delete cascade;