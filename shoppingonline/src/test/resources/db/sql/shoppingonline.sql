CREATE TABLE Item (
	itemId BIGINT(8) NOT NULL, 
	itemName VARCHAR(60),
	itemDescription VARCHAR(250),
	itemPrice DOUBLE,
	PRIMARY KEY (itemId)
);

CREATE TABLE Profile (
  profileId  INT NOT NULL,
  profileName (255) NOT NULL,
  PRIMARY KEY (profileId)
);

CREATE TABLE Person (
	personId BIGINT(8) NOT NULL, 
	personName VARCHAR(50),
	personSex VARCHAR(10),
	personBirthDate DATE,
	personAddress VARCHAR(100),
	personTelephone VARCHAR(20),
	personConnectionName VARCHAR(15),
	personPassword VARCHAR(45),
	personProfile INT,
	PRIMARY KEY (personId),
	FOREIGN KEY (personProfile) REFERENCES Profile(profileId)
);

CREATE TABLE Customer (
  customerId BIGINT(8) NOT NULL,
  customerAvailableAmount (255) NOT NULL,
  personId BIGINT(8) NOT NULL,
  PRIMARY KEY (customerId),
  FOREIGN KEY (personId) REFERENCES Person(personId)
);

CREATE VIEW Person_Profile_view AS
    SELECT DISTINCT
        p1.personId AS personId,
        p1.personName AS personName,
        p1.personSex AS personSex,
        p1.personConnectionName AS personConnectionName,
        p1.personPassword AS personPassword,
        p.profileId AS profileId,
        p.profileName AS profileName
    FROM
        (Profile p
        LEFT JOIN Person p1 ON ((p.profileId = p1.personProfile))
        );

CREATE VIEW Person_Customer_view AS
    SELECT 
        C.customerId AS customerId,
        C.personId AS personId,
        P.personName AS customerName,
        P.personConnectionName AS customerConnnectionName,
        P.personPassword AS customerPassword,
        C.customerAvailableAmount AS customerAvailableAmount,
        P.profileId AS profileId
    FROM
        (Customer C
        LEFT JOIN Person_Profile_view P ON ((C.personId = P.personId)))
    WHERE
        (P.profileId = 2);

insert into Profile values(1, 'administrator');
insert into Profile values(2, 'customer');

insert into Item (itemId, itemName, itemDescription, itemPrice) values(3, 'Test', 'Alithya Test Item', 27.01);
insert into Item (itemId, itemName, itemDescription, itemPrice) values(4, 'Alithya Java\'s Training Center', 'Montreal Java', 196.27);

insert into Person (personId, personName, personConnectionName, personPassword, personProfile) values(1, 'administrator', 'admin', 'admin', 1);
insert into Person (personId, personName, personConnectionName, personPassword, personProfile) values(1, 'customer1', 'customer1', 'customer1', 2);
