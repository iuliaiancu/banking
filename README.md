# Project Title

A sandbox application for banking operations. Currently there are 2 operations supported:

- sending money between 2 predefined accounts
- get an account statement with account balance and list of completed transactions

## Getting Started

To get a cop

### Prerequisites

The application runs with two profile: the implicit one and the dev one. The dev profile uses MySQL as a database. If MySQL isn't installed, it can install it using the steps described below:

1.  Download mysql installer from <https://dev.mysql.com/downloads/installer/> depending on the underlying operating system you have.
2. Run the installer using the wizard  step by step. When you're asked what products do you want select MySQL Server and MySQL Workbench. You should see a screen similar with:
3. For MySQL Sever local instance set root as default username and root as default password. This are the defaults used by the application. If you wish to set something else, check in the Installing section what should be modified so the application can run.
4. Finish the installation. If you left the defaults during installing and you're running on Windows, MySQL Server & Workbench should be installed under C:\Program Files\My SQL.

### Installing

The application run with two profiles: the default profile (is the implicit one) and the dev-profile (need to be specified).

**Start the application with default profile:**

1. Go to where you downloaded the folder containing the application. The folder name is banking.  Open a command prompt and run 

   **mvn clean install -DskipTests**. This will create the target folder inside banking containing the executable jar.

2. Go the the target folder and start the application using:

   **java -jar banking-0.0.1-SNAPSHOT.jar**

   **The application started with the default profile uses a in-memory database called H2.** The in-memory database gets destroyed every time the application is closed and recreated when the application is started.  The server starts by default on port 8080. If you need to change this port you can either modify in the application.properties file the line starting with server.port or specify the port when you start the application using the command:

   **java -jar -Dserver.port=8082 banking-0.0.1-SNAPSHOT.jar**

   Now the applications runs. The H2 console can be viewed at (if server started on 8080, if not change port accordingly): <http://localhost:8080/h2-console>. A window similar with this will be displayed 

   ![H2Console](\banking\banking\images\H2Console.png)

   Ensure JDBC URL is jdbc:h2:mem:testdb and username is sa. Press connect. You will see something similar with this:

![H2Panel](\banking\banking\images\H2Panel.png)

**Start the application with the dev profile:**

The dev profile tries to connect to a MySQL database, so MySQL need to be installed following the steps described in Prerequisites section. After that, you need to create a database empty schema called **banking**. You can use MySQL Workbench to create, or the command line. The application when starting with this profile, uses Flyway to create the tables and add initial data. The Flyway scripts are run automatically at application start-up.

After creating database schema, please follow the next steps:

1. Go to where you downloaded the folder containing the application. The folder name is banking. Open a command prompt and run 

   **mvn clean install -DskipTests**. This will create the target folder inside banking containing the executable jar.

2. Go the the target folder and start the application using the dev profile:

   **java -jar -Dspring.profiles.active=dev banking-0.0.1-SNAPSHOT.jar**

After starting you should see three tables get created inside banking schema, as below:

![1555673040295](\banking\banking\images\MySQLTables.png)

## Running the tests

The application has two types of tests:

1. Unit tests mainly for services and validators. The tests can be run in the build phase using command **mvn clean install.**
2. An end to end test (SendMoneyTest) that send money from one account to another and verifies resulting account balances are correct. This test will also be run when using mvn clean install command.

## Test data 

For testing the application, I've created 2 predefined accounts and one predefined customer as follow 

1. First account identified with id 1 with an initial balance of 1000000

2. Second account identified with id 2 with an initial balance of 1000000

3. Customer identified with id 1

   If you want to test the application outside of running the unit and end to end tests, you can send requests to the endpoints described below. For sending requests, you need to have a client installed. I've used POSTMAN, but you can use something else as Advanced REST Client.

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



