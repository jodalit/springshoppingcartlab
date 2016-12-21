CREATE TABLE Item (
	temId BIGINT(8) NOT NULL AUTO_INCREMENT, 
	itemName VARCHAR(60) NOT NUL,
	itemDescription VARCHAR(250) NOT NUL,
	itemPrice DOUBLE NOT NULL, 
	itemExpireDate DATE
);