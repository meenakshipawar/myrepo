Create schema ACCOUNTADMIN;
CREATE TABLE ACCOUNTADMIN.Accounts( 
   accountNumber VARCHAR(50) PRIMARY KEY NOT NULL, 
   customerId INT NOT NULL, 
   openingbalance DECIMAL ,
   totalBalance DECIMAL );
   