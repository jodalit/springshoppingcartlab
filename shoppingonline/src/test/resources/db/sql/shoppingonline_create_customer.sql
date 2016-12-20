CREATE TABLE IF NOT EXISTS Customer (
  customerId BIGINT(8) NOT NULL,
  customerAvailableAmount DOUBLE NOT NULL,
  personId BIGINT(8) NOT NULL,
  PRIMARY KEY (customerId),
  FOREIGN KEY (personId) REFERENCES Person(personId)
);