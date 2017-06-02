# Starter project

The intent of this project is to provide a starting point for a stateless RESTful API server using the technologies that I like and find most productive.
 
The hard/tedious parts that have been 'solved' here include provising full auditing of every version of an entity, using Hibernate Envers, full logging of all API requests (except GET), a security web filter that uses the X_AUTH_TOKEN header to enforce permissions at the individual API level. 

To extend this project for your own purposes, just add new Controller classes to return the data you want.

This project could server as the back end for a SPA built in PolymerJS, VueJS or React etc.

Built on:
* Spring Boot
* Spring Data JPA
* Hibernate Envers
* H2 embedded db for testing
* Postgres production profile
* API Call logging
* API Security filtering

# Building the project

```mvn package```

# Running the app

```cd target```

```java -jar -Dspring.profiles.active=prod starter.jar```