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

The application has unit tests for services, converters and validators. The tests can be run in the build phase using command **mvn clean install.**

## Test data 

For testing the application, I've created 3 predefined customers as follows:

1. Customer with id 1: Anna Smith
2. Customer with id 2: John Daniels
3. Customer with id 3: Karina Mackenzie

## Endpoints

The exposed endpoints use REST and JSON. 

1. Endpoint for create account and make subsequent transactions (initial transaction is done when account is first created if initialCredit > 0)
   Path: /account

   Complete URL: http://localhost:8080/account

   Request method: POST

   Request body:
   
   {"customerId":2,
    "initialCredit":1000,
    "accountType":"CURRENT_ACCOUNT"
   }

   Answer:

   {
    "message": {
                "headers": {},
                "body": {
                          "id": 16,
                          "initialCredit": 3000,
                          "accountType": "CURRENT_ACCOUNT",
                          "customerId": 2
                         },
        "statusCode": "OK",
        "statusCodeValue": 200
                }
    }

   The only possible values for "customerId" are 1, 2, 3.
   The only possible values for "accountType" are DEFAULT_ACCOUNT, CURRENT_ACCOUNT, CREDIT, DEBIT.
   
2. Endpoint for checking customer information (name, surname, accounts, transactions) 

   Path: /customer/1 (1 represent the id of customer for which you wish to obtain the information. Possible ids: 1, 2, 3)

   Complete URL: http://localhost:8080/customer/2

   Request Method: GET

   Answer:

   {
    "message": {
        "headers": {},
        "body": {
            "name": "John",
            "surname": "Daniels",
            "accountTransactions": [
                {
                    "accountId": 15,
                    "accountType": "DEFAULT_ACCOUNT",
                    "balance": 2000,
                    "transactions": [
                        {
                            "transactionId": 18,
                            "accountId": 15,
                            "sum": 2000,
                            "date": "2019-08-05T09:06:59"
                        }
                    ]
                },
                {
                    "accountId": 16,
                    "accountType": "CURRENT_ACCOUNT",
                    "balance": 3000,
                    "transactions": [
                        {
                            "transactionId": 19,
                            "accountId": 16,
                            "sum": 2000,
                            "date": "2019-08-05T09:08:14"
                        },
                        {
                            "transactionId": 20,
                            "accountId": 16,
                            "sum": 1000,
                            "date": "2019-08-05T09:08:22"
                        }
                    ]
                }
            ]
        },
        "statusCode": "OK",
        "statusCodeValue": 200
    }
}

The application supports also validation. For example you can't enter a negative initial credit on creatin an account. Also you can't retrieve information for a customer that doesn't exists in the database.

## Assumptions
I've made the following assumptions:

1. For the time being they are only customers identified by ids: 1,2,3. Those customers are predefined and created in the database once the application is started. Only those customers can be used to create accounts and make transactions.
2. There are four types of accounts that can be created for a customer: DEFAULT_ACCOUNT, CURRENT_ACCOUNT, CREDIT, DEBIT.
2. One customer can have one of each type of account. Once a call is fired to POST /account endpoint, this will result in creating a new account (if account isn't already tied to that customer) and make a transaction if initialCredit is greater than 0. Subsequent calls
to POST /account for the same customer will make other deposits (transactions) to the account already created.

