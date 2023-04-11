SELECT database();

CREATE DATABASE Module02;

use Module02;

show tables;

CREATE TABLE Category (
CategoryNo INTEGER,
CategoryName VARCHAR(20)
);

show tables;

CREATE TABLE Product (
ProductNo INTEGER,
ProductName NVARCHAR(30),
Price DECIMAL,
CategoryNo INTEGER
);

SELECT * FROM Category;

INSERT INTO Category VALUES (1, "Novel");
INSERT INTO Category VALUES (2, "Poem");

SELECT * FROM Category;

UPDATE Category SET
CategoryName = 'History';

SELECT * FROM Category;

UPDATE Category SET
CategoryName = 'Novel'
WHERE CategoryNo = 1;

UPDATE Category SET
CategoryNo = 3
WHERE CategoryNo = 2;

SELECT * FROM Category;

DESC Category;

DELETE FROM Category
WHERE CategoryNo = 3;

ALTER TABLE Category ADD CONSTRAINT pk_Category PRIMARY KEY(CategoryNo);

DESC Category;

SELECT * FROM Category;

INSERT INTO Category(CategoryNo, CategoryName)
VALUES (5, 'Science');

DESC Product;

ALTER TABLE Product ADD CONSTRAINT pk_Product PRIMARY KEY(ProductNo);

DESC Product;

SELECT ProductNo, ProductName, Price, CategoryNo FROM Product;

INSERT INTO Product(ProductNo, ProductName, Price)
VALUES(20182337, 'The Third NHN Academy Student', 100000000);

SELECT * FROM Product;

ALTER TABLE Product ADD CONSTRAINT fk_Product_Category 
FOREIGN KEY(CategoryNO) REFERENCES Category(CategoryNo);

SELECT * FROM Category;

UPDATE Product SET
CategoryNo = 2
WHERE ProductNo = 20182337;

UPDATE Product SET
CategoryNo = 5
WHERE ProductNo = 20182337;

SELECT * FROM Product;

INSERT INTO Product (ProductNo, ProductName, Price, CategoryNo) 
VALUES (97422537,'Hobbit', 28800, 1);

INSERT INTO Product (ProductNo, ProductName, Price, CategoryNo) 
VALUES (97422515, 'Lord of the Rings 1', 28800, 1);

SELECT * FROM Product;

INSERT INTO Product (ProductNo, ProductName, Price, CategoryNo) 
VALUES (2312211, 'Cosmos', 28800, 2);

INSERT INTO Category VALUES (2, 'Science');

SELECT * FROM Category;

INSERT INTO Product (ProductNo, ProductName, Price, CategoryNo) 
VALUES (2312211, 'Cosmos', 28800, 2);

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE FROM information_schema.table_constraints 
WHERE table_name = 'Product';

ALTER TABLE Product DROP CONSTRAINT fk_Product_Category;

ALTER TABLE Product 
ADD CONSTRAINT fk_Product_Category FOREIGN KEY(CategoryNo)
REFERENCES Category(CategoryNo) ON DELETE CASCADE;

DELETE FROM Category WHERE CategoryNo = 2;

SELECT * FROM Category;

SELECT * FROM Product;

ALTER TABLE Product DROP CONSTRAINT fk_Product_Category;

ALTER TABLE Product ADD CONSTRAINT fk_Product_Category FOREIGN KEY(CategoryNo) 
REFERENCES Category(CategoryNo) ON DELETE SET NULL;

DELETE FROM Category WHERE CategoryNo = 5;

SELECT * FROM Product;

ALTER TABLE Product DROP CONSTRAINT fk_Product_Category;

ALTER TABLE Product ADD CONSTRAINT fk_Product_Category FOREIGN KEY(CategoryNo) 
REFERENCES Category(CategoryNo) ON DELETE NO ACTION;

DELETE FROM Category 
WHERE CategoryNo = 1;

ALTER TABLE Product DROP CONSTRAINT fk_Product_Category;

ALTER TABLE Product ADD CONSTRAINT fk_Product_Category FOREIGN KEY(CategoryNo) 
REFERENCES Category(CategoryNo) ON UPDATE CASCADE;

UPDATE Category SET CategoryNo = 2 
WHERE CategoryNo = 1;

SELECT * FROM Product;
