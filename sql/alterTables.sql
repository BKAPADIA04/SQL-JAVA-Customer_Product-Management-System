ALTER TABLE Review
ADD CONSTRAINT fk_customerid
FOREIGN KEY (CustomerID)
REFERENCES Customer(CustomerID)
ON DELETE CASCADE;


ALTER TABLE Review
ADD CONSTRAINT fk_productid
FOREIGN KEY (ProductID)
REFERENCES Product(ProductID)
ON DELETE CASCADE;


ALTER TABLE Product
ADD CONSTRAINT fk_brandid
FOREIGN KEY (BrandID)
REFERENCES Brand(BrandID)
ON DELETE CASCADE;