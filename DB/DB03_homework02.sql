show databases;

CREATE DATABASE Module0302;

show databases;

SELECT database();

use Module0302;

SELECT database();

-- 공급자
CREATE TABLE Supplier (
	SupplierNumber INT, 
	SupplierName NVARCHAR(10) NOT NULL,
	Address NVARCHAR(10),
	CONSTRAINT PRIMARY KEY (SupplierNumber)
);

DESC Supplier;
SELECT * FROM Supplier;



 -- 부품
CREATE TABLE Product (
    ProductID int, 
	ProductName NVARCHAR(20) NOT NULL, 
	ProductColor NVARCHAR(20),
    CONSTRAINT PRIMARY KEY (ProductID)
);

DESC Product;
SELECT * FROM Product;



-- 카탈로그
CREATE TABLE Catalogue (
    SupplierNumber INT, 
	ProductID INT, 
    Price FLOAT, 
	CONSTRAINT FOREIGN KEY(SupplierNumber) REFERENCES Supplier(SupplierNumber),
    CONSTRAINT FOREIGN KEY(ProductID) REFERENCES Product(ProductID)
);

DESC Catalogue;
SELECT * FROM Catalogue;

show tables;





