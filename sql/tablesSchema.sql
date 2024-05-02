CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Phone VARCHAR(20),
    EmailID VARCHAR(255) NOT NULL
);

CREATE TABLE Brand (
    BrandID INT PRIMARY KEY,
    BrandName VARCHAR(100) NOT NULL
);

CREATE TABLE Product (
    ProductID INT PRIMARY KEY,
    BrandID INT,
    ProductName VARCHAR(100),
    Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (BrandID) REFERENCES Brand(BrandID)
);

CREATE TABLE Review (
    ReviewID INT PRIMARY KEY,
    CustomerID INT,
    ProductID INT,
    Customer_Product_Review TEXT,
    FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY(ProductID) REFERENCES Product(ProductID)
);