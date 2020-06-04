# Spring Boot Booking System
**Powered by** 
<a href="https://spring.io">
<img src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg" alt="Spring" width="90px">
</a>

Spring Boot Booking System is an example application, which processes booking request and change-requests to that initial requests.
The application takes care to process these requests in the proper order.
In general the application is based on Java 11, Spring Boot 2.3.0 and Spring Cloud Hoxton.

## Architecture

The application consists of several services and technical solutions.
- **import-service** is used to import requests and to take care about their order.
- **consumer-service** is used to process the requests.
- **discovery-service** is used to have a central service discovery server.
- **gateway-service** is used to distribute requests on different instances of import-service.
- **RabbitMQ** is used as messaging service to deliver requests to consumer-service.
- **PostgreSQL** is used to store requests.

<img src="https://github.com/lhaidacher/spring-boot-booking-system/blob/master/files/images/architecture.png?raw=true" alt="Architecture" width="600px">

## Usage
To start the application, use following commands.

```bat
docker-compose up
```
