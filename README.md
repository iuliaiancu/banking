# Project Title

A sandbox application for banking operations. Currently there are 2 operations supported:

- creating an account for existing customers and make initial transaction if initial credit is greater than 0
- see information of a customer (firstname, surname, balance, list of transactions)

## Getting Started

To get a copy you can either download the zip archive or clone the repository using https://github.com/iuliaiancu/banking.git.

### Prerequisites

The application runs with two profile: the implicit one and the dev one. The dev profile uses MySQL as a database. If MySQL isn't installed, it can install it using the steps described below:

1.  Download mysql installer from <https://dev.mysql.com/downloads/installer/> depending on the underlying operating system you have.
2. Run the installer using the wizard  step by step. When you're asked what products do you want select MySQL Server and MySQL Workbench. You should see a screen similar with:
3. For MySQL Sever local instance set root as default username and root as default password. This are the defaults used by the application. If you wish to set something else, check in the Installing section what should be modified so the application can run.
4. Finish the installation. If you left the defaults during installing and you're running on Windows, MySQL Server & Workbench should be installed under C:\Program Files\My SQL.

### Installing

The application run with two profiles: the default profile (is the implicit one) and the dev-profile (need to be specified).

**Start the application with default profile:**

1. Go to where you downloaded the folder containing the application. The folder name is banking_account. Open a command prompt and run 

   **mvn clean install -DskipTests**. This will create the target folder inside banking_account containing the executable jar.

2. Go the the target folder and start the application using:

   **java -jar bankingAccount-0.0.1-SNAPSHOT.jar**

   **The application started with the default profile uses a in-memory database called H2.** The in-memory database gets destroyed every time the application is closed and recreated when the application is started.  The server starts by default on port 8080. If you need to change this port you can either modify in the application.properties file the line starting with server.port or specify the port when you start the application using the command:

   **java -jar -Dserver.port=8082 bankingAccount-0.0.1-SNAPSHOT.jar**

   Now the applications runs. The H2 console can be viewed at (if server started on 8080, if not change port accordingly): <http://localhost:8080/h2-console>. A window similar with this will be displayed 

   ![H2Console](https://github.com/iuliaiancu/banking/blob/master/images/H2Console.png)

   Ensure JDBC URL is jdbc:h2:mem:testdb and username is sa. Press connect. You will see something similar with this:
   
   ![H2Panel](https://github.com/iuliaiancu/banking/blob/master/images/H2Panel.png)

**Start the application with the dev profile:**

The dev profile tries to connect to a MySQL database, so MySQL need to be installed following the steps described in Prerequisites section. After that, you need to create a database empty schema called **banking_db**. You can use MySQL Workbench to create, or the command line. The application when starting with this profile, uses Flyway to create the tables and add initial data. The Flyway scripts are run automatically at application start-up.

After creating database schema, please follow the next steps:

1. Go to where you downloaded the folder containing the application. The folder name is banking. Open a command prompt and run 

   **mvn clean install -DskipTests**. This will create the target folder inside banking containing the executable jar.

2. Go the the target folder and start the application using the dev profile:

   **java -jar -Dspring.profiles.active=dev bankingAccount-0.0.1-SNAPSHOT.jar**

After starting you should see three tables get created inside banking_db schema, as below:

![MySQLTables](https://github.com/iuliaiancu/banking/blob/master/images/MySQLTables.png)

## Running the tests

The application has two types of tests:

1. Unit tests for services, converters and validators. The tests can be run in the build phase using command **mvn clean install.**
2. An end to end test (SendMoneyTest) that send money from one account to another and verifies resulting account balances are correct. This test will also be run when using mvn clean install command.

## Test data 

For testing the application, I've created 3 predefined customers as follows:

1. Customer with id 1: Anna Smith
2. Customer with id 2: John Daniels
3. Customer with id 3: Karina Mackenzie

## Set-up front environemnt

I've created a simple front using angular. Because the application is hosted on Angular side, you need to have Node.js and Angular Cli installed. 
1. Install Node.js server: You can download and install Node.js server using this link: https://nodejs.org/en/. Toghether with Node.js is installed the npm client command line interface.
2. Install Angular CLI: from command line run: npm install -g @angular/cli

## Install front

Front can be downloaded from github using this link:

1. Install Angular dependencies: go to where you downloaded folder, open a command prompt and type: **npm install**.
2. Start the Node.js server: **ng serve --watch --open**. By default it will launch the server listening on port 4200. This should open a browser window: http://localhost:4200

## Endpoints

The exposed endpoints are REST and use JSON. 

1. Endpoint for sending money between the two predefined accounts described above.

   Path: /sendMoney

   Complete URL: http://localhost:8080/sendMoney

   Request method: POST

   Request body:

   {"idAccountFrom":1,
   "idAccountTo":2,
   "sum":20,
   "customerId":1
   }

   Answer:

   {
       "message": {
           "headers": {},
           "body": "Transaction succesfull. Moved 20 from account with id 1 to account with id 2",
           "statusCode": "OK",
           "statusCodeValue": 200
       }
   }

   The only possible values for "idAccountFrom" and "idAccountTo" are 1 or 2. Also "idAccountFrom" and "idAccountTo" should have different values.

2. Endpoint for checking account balance and obtain list of transactions. 

   Path: /accountStatement/1 (1 represent the id of account for which you wish to obtain the statement)

   Complete URL: http://localhost:8080/accountStatement/1

   Request Method: GET

   Answer:

   {
       "message": {
           "headers": {},
           "body": {
             "balance": 180,
             "transactions": [
                    {
                        "id": 1,
                        "fromAccountId": 1,
                        "toAccountId": 2,
                        "sum": 20
                     }
                  ]
              },
              "statusCode": "OK",
              "statusCodeValue": 200
          }
   }

The application supports also validation. For example you can't input an account that doesn't exists or isn't in the expected format (a number) or input a negative value for the sum. Also, you will receive an error message if you try to make a transaction using an account in which you don't have enough money. 

## Assumptions
I've made the following assumptions:

1. For the time being they are only customers identified by ids: 1,2,3. Those customers are predefined and created in the database once the application is started. Only those customers can be used to create accounts and make transactions.
2. There are four types of accounts that can be created for a customer: DEFAULT_ACCOUNT, CURRENT_ACCOUNT, CREDIT, DEBIT.
2. One customer can have one of each type of account. Once a call is fired to POST /account endpoint, this will result in creating a new account (if account isn't already tied to that customer) and make a transaction if initialCredit is greater than 0. Subsequent calls
to POST /account for the same customer will make other deposits (transactions) to the account already created.

