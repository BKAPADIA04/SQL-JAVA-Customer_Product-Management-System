CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Phone VARCHAR(20)
);

CREATE TABLE Product (
    ProductID INT PRIMARY KEY,
    BrandID INT,
    ProductName VARCHAR(100),
    Price DECIMAL(10, 2),
    Quantity INT,
    FOREIGN KEY (BrandID) REFERENCES Brand(BrandID)
);

CREATE TABLE Brand (
    BrandID INT PRIMARY KEY,
    BrandName VARCHAR(100)
);

CREATE TABLE Review (
    ReviewID INT PRIMARY KEY,
    CustomerID INT,
    ProductID INT,
    Customer_Product_Review TEXT,
    FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY(ProductID) REFERENCES Product(ProductID)
);