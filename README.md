[![Build Status](https://travis-ci.org/dsrahul/marketplace.svg?branch=master)](https://travis-ci.org/dsrahul/marketplace) [![Coverage Status](https://coveralls.io/repos/github/dsrahul/marketplace/badge.svg?branch=master)](https://coveralls.io/github/dsrahul/marketplace?branch=master)
# Coding Challenge "Market Place" Project

This is a Java / Maven / Spring Boot application which exposes some REST services for merchants to create Offers. 
An offer can only be created for an existing Merchant and Offer types. An attempt to create one for a non-existing merchant or offer type returns an error message in the response.

## About the Service
The service is a simple *Merchant Offer* creation REST service. It uses an in-memory database to store the data. The REST endpoints are defined in ```com.marketplace.offer.controller.OfferController``` on **port 8080**. (see below)

API documentation is available after application startup
http://localhost:8080/swagger-ui.html

The H2 DB console is available under
http://localhost:8080/dbconsole/

Features in this application : 
* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single war with embedded container (tomcat 7): No need to install a container separately on the host just run using the ``java -jar`` command
* Use of Annotations to write services
* Exception handling through a *GlobalExceptionHandler*. Returns meaningful message in case of unecoverable exceptions.
* *Spring Data* Integration with JPA/Hibernate. 
* Spring *Repository* pattern used for automatic CRUD functionality against the data source.
* Use of Mockito and MockMVC test framework for unit and integration testing.
* All APIs are "self-documented" by Swagger using annotations 

## How to Run 

This application is packaged as a war which has Tomcat 7 embedded.

* Developed using JDK 1.8
* The Project can be built and tests run using the command ```mvn clean package```
* Once successfully built, the service can be run by one of these two methods:
```
        java -jar target/marketplace-0.0.1-SNAPSHOT.war
or
        mvn spring-boot:run
```
* There should be no exceptions in the stdout

Once the application runs you should see something like this

```
[INFO ] 2017-01-31 14:30:48,309 org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer - Tomcat started on port(s): 8080 (http)
[INFO ] 2017-01-31 14:30:48,313 com.marketplace.MarketPlaceApplication - Started MarketPlaceApplication in 4.097 seconds (JVM running for 4.535)
```

### DATABASE
Applications consists of three tables :
* TMERCHNT : Holds Merchant Information.  
* TOFFTYPE :  The Offer Type table e.g. '2 For 1' or '% Off' or '£ Off'
* TOFFER   : The Offer Information table. Has foreign key constraint with the TMERCHNT and TOFFTYPE table

An offer can only be created for an existing TMERCHNT.ID and TOFFTYPE.ID.  

Merchant and OfferType data are pre-populated into the database using *import.sql* during launch of the application.
*TABLE NAME :* **TMERCHNT**

|ID |   CODE|   CURRENCY_CODE   |   NAME                |  
|---|-------|-------------------|-----------------------|
|1  |ANZ001 |GBP                |   BINGO REPUBLIC      |
|2  |DMD001 |GBP                |   DRAMA STUDIOS       |
|3  |MRG001 |GBP                |   TOURS AND OPERATOR  |
|4  |PHT001 |GBP                |   THE COFFEE SHOP     |

*TABLE NAME :* **TOFFTYPE**

|ID     |OFFER_TYPE         |
|-------|-------------------|
|100057 |% off              |
|100058 |2 for 1            |
|100059 |FREE               |
|100060 |BUY AND SAVE       |


## Endpoints to call:
### Create an Offer resource
```
POST http://localhost:8080/offers/add
Content-Type: application/json

[
    {
      "title": "Save 20%",
      "description": "Save 20% with your Merchant Card",
      "typeId": 100057,
      "merchantId": 2,
      "validFrom": "2017-01-11",
      "validTo": "2017-02-11"
    }
]

RESPONSE: HTTP 201 (Created)
```

### Retrieve all Offers
```
GET http://localhost:8080/offers/find/all
Content-Type: application/json

Response: HTTP 200 (OK)
Content: list 
[
  {
    "id": 1,
    "title": "Save 20%",
    "description": "Save 20% when you shop online",
    "typeId": 100057,
    "merchantId": 2,
    "validFrom": "2017-01-11",
    "validTo": "2017-02-11"
  },
  {
    "id": 2,
    "title": "Save 20%",
    "description": "Save 20% with your Merchant card",
    "typeId": 100057,
    "merchantId": 2,
    "validFrom": "2017-01-11",
    "validTo": "2017-02-11"
  }
]
```
A Response: HTTP 204 (No Content) is returned when no offers are available.

### Retrieve a list of offers for a Merchant using Merchant Id
```
GET http://localhost:8080/offers/find/{id}
Content-Type: application/json

Response: HTTP 200 (OK)
Content: list 
[
  {
    "id": 1,
    "title": "Save 20%",
    "description": "Save 20% when you shop online",
    "typeId": 100057,
    "merchantId": 2,
    "validFrom": "2017-01-11",
    "validTo": "2017-02-11"
  },
  {
    "id": 2,
    "title": "Save 20%",
    "description": "Save 20% with your Merchant card",
    "typeId": 100057,
    "merchantId": 2,
    "validFrom": "2017-01-11",
    "validTo": "2017-02-11"
  }
]
```
A Response: HTTP 204 (No Content) is returned when no offers are available.

### Error Scenario Response
An Error condition e.g. invalid Merchant Id or Offer Type id will result in a standard error returned as response indicating what next action the consumer needs to perform.
```
Response: HTTP 500 (Internal Server Error)

{
  "status": "INTERNAL_SERVER_ERROR",
  "message": "There was an error while processing the request",
  "date": "31/01/2017 16:37:57",
  "errors": [
    "Please contact Administrator"
  ]
}
````
## UNIT & Integration Tests.

Unit tests for the controller and service classes exits along with the following integration tests.

```
OfferRepositoryIntegrationTest : Integration Test for the OfferRepository. Runs against the H2 in memory database.
OfferControllerIntegrationTest : Integration Test for the Rest Controller. Runs against a random available port.
```

## Documentation
API Documentation available under on application startup:

http://localhost:8080/swagger-ui.html




