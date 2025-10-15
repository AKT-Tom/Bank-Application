# Banking Application

Built with Java Spring Boot, MySQL, Redis, Docker

## To run locally:
1. Install MySQL and create two tables
2. Create table users and records
3. Configure configure.properties file with your database password, name and jdbc routing.
4. Run: mvn spring-boot:run
5. Access at http://localhost:8080

##MySQL schema 
CREATE TABLE USERS(
   UserID PRIMARY KEY,
   FirstName VARCHAR(100) NOT NULL,
   LastName VARCHAR(100) NOT NULL,
   Balance DECIMAL(15,2),
   Address VARCHAR(100) NOT NULL,
   City VARCHAR(50) NOT NULL,
   DateofBirth date NOT NULL,
   Email VARCHAR(50) UNIQUE NOT NULL,
   Password VARCHAR(100) NOT NULL,
)

CREATE TABLE RECORDS(
  ID INT AUTO INCREMENT,
  Email VARCHAR(50) NOT NULL,
  Amount decimal(15,2) NOT NULL,
  Type VARCHAR(1) NOT NULL,
  Transfer_From VARCHAR(50),
  Transfer_To VARCHAR(50)
)
