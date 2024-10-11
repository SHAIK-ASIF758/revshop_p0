use revshop0;
insert into category (category_name) values("Phone");
insert into category (category_name) values("Laptops");
insert into category (category_name) values("Tabs");
insert into category (category_name) values("Pods");
insert into sellers (seller_name,email, phone_number,address,password,current_month_earning,total_earning,total_item_sold,current_month_item_sold)values("asif","shaikasif938341@gmail.com","8341756100","chennai","vivo",10000,2300567,51063,525),("sai","test","1234567890","chennai","test",10000,2300567,51063,525);
select * from sellers;
update customers set password="apple" where customer_id=1;
update sellers set email="test@gmail.com" where seller_id=2;

update sellers set email="shaikasif938341@gmail.com" where seller_id=1;
describe cart;
select * from products;
select * from category;
select * from customers;
select * from favorites;
select * from cart;
select * from products;
select * from order_details;
select * from orders;
describe order_details;
describe orders;
describe cart;
use revshop;
-- Category 1: Laptops
INSERT INTO Products (max_discount,category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (15,2, 1, 'Dell XPS 13', '13.3-inch display, 11th Gen Intel Core i7, 16GB RAM, 512GB SSD', 107099.17, 10, 'https://i.dell.com/is/image/DellContent/content/dam/ss2/product-images/dell-client-products/notebooks/xps-notebooks/13-9340/media-gallery/silver/touch/notebook-xps-13-9340-t-sl-gallery-4.psd?fmt=pjpg&pscan=auto&scl=1&wid=3509&hei=2082&qlt=100,1&resMode=sharp2&size=3509,2082&chrss=full&imwidth=5000', 5);

INSERT INTO Products (max_discount,category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (10,2, 1, 'MacBook Air M1', '13.3-inch Retina display, Apple M1 chip, 8GB RAM, 256GB SSD', 82999.17, 15, 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/refurb-macbook-air-space-gray-m1-202010?wid=1144&hei=1144&fmt=jpeg&qlt=90&.v=1634145627000', 3);

INSERT INTO Products (max_discount,category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (5,2, 1, 'HP Spectre x360', '14-inch display, Intel i5, 16GB RAM, 512GB SSD', 91299.17, 7, 'https://img-cdn.tnwcdn.com/image?fit=1200%2C900&height=900&url=https%3A%2F%2Fcdn0.tnwcdn.com%2Fwp-content%2Fblogs.dir%2F1%2Ffiles%2F2021%2F08%2FHP-Spectre-x360-14-1-of-7.jpg&signature=0d2f21c8c102f0b5f8f935cee9d98ef6', 4);

-- Category 2: Tablets
INSERT INTO Products (max_discount,category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (32,3, 1, 'iPad Pro', '12.9-inch Liquid Retina display, Apple M1 chip, 128GB storage', 91299.17, 12, 'https://store.storeimages.cdn-apple.com/1/as-images.apple.com/is/ipad-pro-finish-unselect-gallery-2-202405_FMT_WHH?wid=1280&hei=720&fmt=p-jpg&qlt=80&.v=YXpaUEtKWGhlNnNrVGZkTEo4T0xsN2dzSmpObkZCM3MrNmJ5SkhESlNDaktqSkExTHB4VHJRR1hzOGdBenBuczN0bWR6ME9RYmIrVG9PSXZFalM2aHdBb0pjWml6bllCL0Y5a1RKc2gxZjlIaERUT3FJbHFiWTlmb2lodm1tWE55UjVHcmIzQTc0UDFXY0hsUWdxUDFRPT0=&traceId=1', 4);

INSERT INTO Products (max_discount,category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (5,3, 1, 'Samsung Galaxy Tab S7', '11-inch display, Snapdragon 865+, 6GB RAM, 128GB storage', 53949.17, 20, 'https://m.media-amazon.com/images/I/81fhwFZQiWL.jpg', 6);

-- Category 3: Pods (Wireless Earbuds)
INSERT INTO Products (max_discount,category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (7,4, 1, 'Apple AirPods Pro', 'Active Noise Cancellation, Transparency mode, Wireless charging case', 20774.17, 50, 'https://5.imimg.com/data5/SELLER/Default/2023/8/336242465/TA/JV/NE/121408955/apple-airpods-pro-with-magsafe-charging-case-made-in-china.jpg', 10);

INSERT INTO Products (max_discount,category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (17,4, 1, 'Samsung Galaxy Buds Pro', 'Active Noise Cancelling, Wireless charging', 16599.17, 35, 'https://img.global.news.samsung.com/global/wp-content/uploads/2021/01/Galaxy-Buds-Pro-PR_main1.jpg', 8);

INSERT INTO Products (max_discount,category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (5,4, 1, 'Sony WF-1000XM4', 'Noise Cancelling, 24-hour battery life, Wireless charging', 23239.17, 25, 'https://fdn.gsmarena.com/imgroot/news/22/02/wf-1000xm4-long-term/inline/-1200w5/gsmarena_001.jpg', 5);

-- Category 4: Phones
INSERT INTO Products (max_discount,category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (6,1, 1, 'iPhone 13 Pro', '6.1-inch display, A15 Bionic chip, 128GB storage', 82999.17, 30, 'https://cellbuddy.in/buddy/wp-content/uploads/2022/09/13-Pro-Sierra-Blue-2.png', 7);

INSERT INTO Products (max_discount,category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (30,1, 1, 'Samsung Galaxy S21', '6.2-inch display, Snapdragon 888, 8GB RAM, 128GB storage', 66399.17, 25, 'https://images-cdn.ubuy.co.in/65e052459f1bf005e26a62a7-samsung-galaxy-s21-5g-g996u-128gb.jpg', 5);



-- Category 1: Laptops
INSERT INTO Products (max_discount, category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (10, 2, 2, 'Lenovo ThinkPad X1 Carbon', '14-inch display, Intel i7, 16GB RAM, 512GB SSD', 119999.17, 8, 'https://m.media-amazon.com/images/I/41AW34vZZdL._AC_UF1000,1000_QL80_.jpg', 6);

INSERT INTO Products (max_discount, category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (12, 2, 2, 'Acer Swift 3', '14-inch display, AMD Ryzen 7, 8GB RAM, 512GB SSD', 74999.17, 12, 'https://rukminim2.flixcart.com/image/850/1000/xif0q/computer/e/v/s/sf314-512-thin-and-light-laptop-acer-original-imah3fjdquvhgdrm.jpeg?q=90&crop=false', 4);

-- Category 2: Tablets
INSERT INTO Products (max_discount, category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (20, 3, 2, 'Microsoft Surface Pro 7', '12.3-inch PixelSense display, Intel Core i5, 8GB RAM, 128GB SSD', 74999.17, 10, 'https://cdn-dynmedia-1.microsoft.com/is/image/microsoftcorp/MSFT-RWGaM8-Surface-Pro-7-in-Laptop-Mode?scl=1', 5);

INSERT INTO Products (max_discount, category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (8, 3, 2, 'Lenovo Tab P11', '11-inch display, Snapdragon 750G, 6GB RAM, 128GB storage', 43999.17, 15, 'https://m.media-amazon.com/images/I/51QRtIPG9NL._AC_UF1000,1000_QL80_.jpg', 7);

-- Category 3: Pods (Wireless Earbuds)
INSERT INTO Products (max_discount, category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (12, 4, 2, 'Jabra Elite 75t', 'Active Noise Cancelling, 7.5-hour battery life', 20999.17, 40, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6kOU2ol5T3webI0kHjM1foG-YOowFkwC_9Q&s', 12);

INSERT INTO Products (max_discount, category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (15, 4, 2, 'Bose QuietComfort Earbuds', 'Noise Cancelling, Up to 6 hours battery life', 27999.17, 25, 'https://m.media-amazon.com/images/I/61DUYYk-BOL.jpg', 8);

-- Category 4: Phones
INSERT INTO Products (max_discount, category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (5, 1, 2, 'OnePlus 9', '6.55-inch AMOLED display, Snapdragon 888, 8GB RAM, 128GB storage', 49999.17, 20, 'https://oasis.opstatics.com/content/dam/oasis/page/2021/9-series/spec-image/9-pro/Pine%20green-gallery..png', 10);

INSERT INTO Products (max_discount, category_id, seller_id, product_name, description, price, stock_quantity, image_url, threshold)
VALUES (25, 1, 2, 'Xiaomi Mi 11', '6.81-inch AMOLED display, Snapdragon 888, 8GB RAM, 256GB storage', 57999.17, 18, 'https://images-cdn.ubuy.co.in/63b04c4f26f7ee36833ef202-xiaomi-mi-11-5g-128gb-8gb-ram-china.jpg', 6);
