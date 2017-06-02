# Starter project

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