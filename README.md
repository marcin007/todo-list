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

To make this project complete is only need to write missing forntend.

## Requirements
This demo is build with with Maven 3.6.x and Java 11.

## Usage
Just start the application with the Spring Boot maven plugin (`mvn spring-boot:run`). The application is
running at [http://localhost:8080](http://localhost:8080).
![Screenshot from running application](todo_pic/h2console.png?raw=true "H2 Console")

You can use the **H2-Console** for exploring the database under [http://localhost:8080/h2console](http://localhost:8080/h2console):

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
/api/users/{username}/boards - for current user or admin
/api/user/boards - for current user
/api/user/boards/{id} - for current user
/api/boards/{id} - for admin

POST:
/api/user/boards

PATCH:
/api/user/boards/{id} - for current user
/api/boards/{id} - for admin

DELETE:
/api/user/boards/{id} - for current user
/api/boards/{id} - for admin

```
Endpoints for Tasks Controller:
```
GET:
/api/tasks/{id} -  for current user
/api/user/tasks - for current user
/api/users/{username}/tasks - for current user or admin
/api/boards/{id}/tasks - for current user or admin

POST:
/api/tasks - for current user or admin

PATCH:
/api/tasks/{id} - for current user
/api/users/{username}/tasks/{id} - for current user or admin

DELETE:
/api/tasks/{id} - for current user or admin
```
Endpoints for User Controller:
```
GET:
/api/user - for current user 
/api/users - for admin
/api/users/{id} - for current user or admin

POST:
/api/users - for unauthenticated user

PATCH:
/api/users/{id} - for current user or admin

DELETE:
/api/users/{id} - for current user or admin
```

## Frontend

### For this time you can:
### - register new users
![Screenshot from running application](todo_pic/register.png?raw=true "H2 Console")

### - login 
![Screenshot from running application](todo_pic/login.png?raw=true "H2 Console")

### - create tasks board and assing another users to this tasks board 
![Screenshot from running application](todo_pic/createNewBoard.png?raw=true "H2 Console")


-------------------------------------------------------------------------------------------


also you can pick the color of your task board
![Screenshot from running application](todo_pic/exampleDataForCreateNewBoard.png?raw=true "H2 Console")

How it looks when you add new tasks board:

![Screenshot from running application](todo_pic/boards.png?raw=true "H2 Console")

## Documentation

The documentation was generated via Swagger 2.

![Screenshot from running application](todo_pic/swaggerPic2.png?raw=true "H2 Console")

this is exmaple documentation of ```jwt-controller```

![Screenshot from running application](todo_pic/swaggerPic1.png?raw=true "H2 Console")

## Copyright and license

The code is released under the [MIT license](LICENSE?raw=true).
