# Developers playground

This project is about creating a REST-API with Spring-Boot.
The project follows the following steps (see commit-history):


## init a gradle based Java Spring-Boot project
- Folder structure
- gradle.build

## create the Spring-Boot application and its first API-Endpoint
- main in IvoSDC.java
- DemoController
- Tests

## adding resources folder and application.properties for dev-Profile
- set --spring.profiles.active=dev 
- resources/application.yml
- resources/application-dev.yml

## document your API with Swagger/Swagger-UI
- configuration/Swagger.java
- resources/api/description.md for swagger
- visit swagger-ui localhost:8080/swagger-ui.html

## init database with flyway migration script
- resources/db/migration/V2018_12_19_20_04__initial-db-schema-demo.sql

## API-endpoints use database
- creates DemoService, -Provider, -Repository and Model
