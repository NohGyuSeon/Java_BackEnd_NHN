show databases;

CREATE DATABASE Module03;

show databases;

SELECT database();

use Module03;

SELECT database();

CREATE TABLE Category (
	CategoryNo int NOT NULL,
    CategoryName varchar(50) NOT NULL, 
    PRIMARY KEY (CategoryNo)
);

CREATE TABLE Product (
	ProductNo int PRIMARY KEY, 
    ProductName varchar(100) NOT NULL,
    UnitPrice int DEFAULT 0 NOT NULL,
    Description text,
    Category int,
    CONSTRAINT fk_Product_Category FOREIGN KEY (CategoryNo) REFERENCES Category(CategoryNo)
);

CREATE TABLE Customer (
	CustomerNo int,
    CustomerName nvarchar(10),
	Email varchar(40),
    Password varchar(16),
    CONSTRAINT pk_Customer PRIMARY KEY (CustomerNo)
) ENGINE = MyISAM CHARSET = utf8mb3;

CREATE TABLE Orders (
	OrderNo int,
    OrderDate date,
    CustomerNo int,
    CONSTRAINT pk_Order PRIMARY KEY (OrderNo),
    CONSTRAINT fk_Order_Customer FOREIGN KEY (CustomerNo) REFERENCES Customer(CustomerNo)
);

ALTER TABLE Customer ENGINE = InnoDB;





    


