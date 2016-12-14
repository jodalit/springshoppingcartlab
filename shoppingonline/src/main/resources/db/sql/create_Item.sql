CREATE TABLE Item (
	itemId BIGINT(8) NOT NULL, 
	itemName VARCHAR(60),
	itemDescription VARCHAR(250),
	itemPrice DOUBLE,
	PRIMARY KEY (itemId)
);