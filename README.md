[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/rahulsuvarna/marketplace)

[![Build Status](https://travis-ci.org/dsrahul/marketplace.svg?branch=master)](https://travis-ci.org/dsrahul/marketplace) [![Coverage Status](https://coveralls.io/repos/github/dsrahul/marketplace/badge.svg?branch=master)](https://coveralls.io/github/dsrahul/marketplace?branch=master) [ ![Codeship Status for dsrahul/marketplace](https://app.codeship.com/projects/4a0f6040-8ee3-0135-0d60-3a7a536cf0eb/status?branch=master)](https://app.codeship.com/projects/249830)
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

## UNIT & Integration Tests.

Unit tests for the controller and service classes exits along with the following integration tests.

```
OfferRepositoryIntegrationTest : Integration Test for the OfferRepository. Runs against the H2 in memory database.
OfferControllerIntegrationTest : Integration Test for the Rest Controller. Runs against a random available port.
```

## Documentation
API Documentation available under on application startup:

http://localhost:8080/swagger-ui.html




