# Todo-list

[![Build Status](https://travis-ci.org/marcin007/todo-list.svg?branch=master)](https://travis-ci.org/github/marcin007/todo-list)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.marcinwo.todolist%3Atodo-list&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.marcinwo.todolist%3Atodo-list)

## About

This is a demo for simply application for organizing your work with 
**[Spring Boot](https://spring.io/projects/spring-boot)**
and
**[Vaadin](https://vaadin.com/docs/)**.
The backend of API is complete and secured via
**[Json Web Token](https://jwt.io/)**.
For this time you can:
- register new users
- login 
- create tasks board and assing another users to this tasks board 

To make this project complete is only need to write missing forntend.

## Backend

There are three user accounts present to demonstrate the different levels of access to the endpoints in the API and the different authorization exceptions:

```
Admin - m:123
User - z:123
Disabled - disabled:123 (this user is deactivated)
```

Endpoints for JWT:
```
/api/authenticate - authentication endpoint with unrestricted access
```

Endpoints for Tasks Boad:
```
GET:
/users/{username}/boards - for current user or admin
/user/boards - for current user
/user/boards/{id} - for current user

/boards/{id} - for admin

POST:
/user/boards

PATCH:
/user/boards/{id} - for current user
/boards/{id} - for admin

DELETE:
/user/boards/{id} - for current user
/boards/{id} - for admin

```


## Documentation

The documentation was generated via Swagger 2
