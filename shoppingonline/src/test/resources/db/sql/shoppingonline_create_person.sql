CREATE TABLE IF NOT EXISTS Person (
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
