# groceries
A web application that allows users to search for grocery products in an inventory

This application consists of an Angular-based frontend, a RESTful Java service, and a MySQL database.

## Setup Instructions

### Setting Up the Database Config File

The database config file specifies the database URL, driver, username, and password. It should be named `dao.properties` and stored in the home folder of the user that will run the service. An example properties file is included in the `config` folder at the root of this repository.

Its entries look similar to the following:

```
groceriesdb.jdbc.url = jdbc:mysql://localhost:3306/groceriesdb?useSSL=false
groceriesdb.jdbc.driver = com.mysql.cj.jdbc.Driver
groceriesdb.jdbc.username = dev
groceriesdb.jdbc.password = secret_password
```

Each entry is a key-value pair. Note that the prefix for the keys is in the form `<database_name>.jdbc`.

### Initializing the Inventory

The inventory file is a comma-separated values list of products that would be inserted into the database. This file should be named `products_inventory.txt` and placed in the home folder of the user that will run the service. An example inventory file is included in the `config` folder at the root of this repository.

Entries look similar to the following:

```
ID,Description,lastSold,ShelfLife,Department, Price ,Unit,xFor, Cost
753542,banana,9/5/2017,4d,Produce, $2.99 ,lb,1, $0.44
321654,apples,9/6/2017,7d,Produce, $1.49 ,lb,1, $0.20
```

Use these steps to initialize your own MySQL database with the inventory from this file: 

1. Ensure that the database is already created using the name specified in the JDBC URL found in the `dao.properties` file. For example, you would name the database `groceriesdb` to correspond with the URL `jdbc:mysql://localhost:3306/groceriesdb?useSSL=false`
2. In your Java workspace, locate the test class `com.heb.groceries.dao.GroceriesDBInitializerTest`.
3. Adjust the database prefix as needed in the named constant. This represents the prefix used in the `dao.properties` file.
4. Execute the test method and then you should see a message printed to the console similar to the following: `Total rows affected: 20`
5. Verify that the products are in the database by executing the following from a `mysql` prompt:

```
mysql> DESCRIBE groceriesdb.Product;
mysql> SELECT * FROM groceriesdb.Product;
```

## Deployment Instructions

### Building and Deploying the Service

Use these instructions to build and deploy the Groceries service using Maven and Tomcat.

1. Navigate to the service's project folder `groceries-service/groceries/`. (Note: this is the folder with `pom.xml` in it).
2. Use the following command to create the WAR file:
```
$ mvn package
```
3. Use your favorite Java EE container to deploy the WAR file. For example, with Tomcat running, open the management page (http://localhost:8080); select "Manager App" > WAR file to deploy > "Choose file" > select the WAR file > Deploy

### Building and Deploying the Client

Use these instructions to build and deploy the Groceries client.

1. Navigate to the project folder `groceries-client/`
2. Use the following command to build the project:
```
$ ng build --prod
```
3. You should see a new folder created within the project folder called `dist/`, and inside of that you should see a folder with the project's name. You can host the contents of the `dist/groceries-client/` folder using your favorite HTTP server. For example using the `http-server` application, you can host the client to be reachable on port 8081 using:
```
$ http-server dist/groceries-client/ -p 8081
```

## API Specifications

The RESTful service allows the following calls:

* `GET /groceries/api/products`
* `GET /groceries/api/products/{product-id}`

### GET /products

The `/products` resource allows several query parameters that may be used in combination to search for products in the inventory.

* **id:** find products with the specified ID (e.g., `/products?id=1112`).

* **descriptionContains:** find products with the specified substring (e.g., `/products?descriptionContains=medicine`).

* **lastSoldDateFrom, lastSoldDateUntil:** find products with last-sold dates within a range of days (e.g., `/products?lastSoldDateFrom=2017-09-07&lastSoldDate=2017-09-17`). Note that both query parameters must be specified in the URL when filtering by last-sold date.

* **shelfLifeMin, shelfLifeMax:** find products with a shelf life that exists within a range of days (e.g., `/products?shelfLifeMin=1&shelfLifeMax=20`). Note that both query parameters must be specified in the URL when filtering by shelf life.

* **department:** find products with the specified department (e.g, `/products?department=produce`)

* **priceMin, priceMax:** find products with a price that exists within a range of prices (e.g., `/products?priceMin=0.5&priceMax=2.5`). Note that both query parameters must be specified in the URL when filtering by price.

* **unit:** find products with the specified unit (e.g., `/products?unit=each`)

* **xForMin, xForMax:** find products with an xFor value that exists within a range of xFor values (e.g., `/products?xForMin=1&xForMax=1`). Note that both query parameters must be specified in the URL when filtering by xFor.

* **costMin, costMax:** find products with a cost that exists within a range of costs (e.g., `/products?costMin=0.1costMax=0.4`). Note that both query parameters must be specified in the URL when filtering by cost.

Typical successful responses look similar to the following: 

```
[
    {
        "XFor": 1,
        "cost": 1.9,
        "department": "Pharmacy",
        "description": "headache medicine",
        "id": 9000001,
        "lastSold": "2017-09-20",
        "price": 4.89,
        "shelfLifeDays": 365,
        "unit": "Each"
    },
    {
        "XFor": 1,
        "cost": 1.9,
        "department": "Pharmacy",
        "description": "Migraine Medicine",
        "id": 9000002,
        "lastSold": "2017-09-21",
        "price": 5.89,
        "shelfLifeDays": 365,
        "unit": "Each"
    }
]
```
The JSON response is a collection of `Product` objects. This collection may contain several, one, or zero objects.

### GET /products/{product-id}

The products resource allows you to reference a product by its ID. For example, `/products/135` references the product with the ID value of `135`. If a product with that ID does not exist, then the service returns a `404: Not Found` response.

Typical successful responses look similar to the following:

```
{
    "XFor": 1,
    "cost": 1.2,
    "department": "Produce",
    "description": "grapes",
    "id": 11122,
    "lastSold": "2017-09-10",
    "price": 4,
    "shelfLifeDays": 7,
    "unit": "lb"
}
```
The JSON response is a `Product` object.

