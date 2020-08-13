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
Just start the application with the Spring Boot maven plugin (`mvn spring-boot:run`).
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
/api/authenticate - authentication endpoint with unrestricted access. In response you will get generated token.
```
Endpoints for Tasks Board:
```
GET:
/api/users/{username}/boards - access for current user or admin. In response you will get list of tasks boards given user.
/api/user/boards - access for current user. In response you will get list of tasks boards logged user.
/api/user/boards/{id} - access for current user. In response you will get specific tasks board logged user.
/api/boards/{id} - access for admin. In response you will get specific tasks board.

POST:
/api/user/boards - access for current user. By this endpoint you can add new tasks board.

PATCH:
/api/user/boards/{id} - access for current user. By this endpoint you can edit specific tasks board.
/api/boards/{id} - access for admin. By this endpoint you can edit specific tasks board.

DELETE:
/api/user/boards/{id} - access for current user. By this endpoint you can delete specific tasks board.
/api/boards/{id} - access for admin. By this endpoint you can delete specific tasks board.

```
Endpoints for Tasks Controller:
```
GET:
/api/tasks/{id} - access for current user. In response you will get specific task logged user.
/api/user/tasks - access for current user. In response you will get list of tasks logged user.
/api/users/{username}/tasks - access for current user or admin. In response you will get list of tasks specific user.
/api/boards/{id}/tasks - access for current user or admin. In response you will get list of tasks specific board.

POST:
/api/tasks - access for current user or admin. By this endpoint you can add new task.

PATCH:
/api/tasks/{id} - access for current user. By this endpoint you can update specific task by id.
/api/users/{username}/tasks/{id} - access for current user or admin. By this endpoint you can update specific task by username.

DELETE:
/api/tasks/{id} - access for current user or admin. By this endpoint you can delete specific task by id.
```
Endpoints for User Controller:
```
GET:
/api/user - returns detail information for an authenticated user (a valid JWT token must be present in the request header)
/api/users - access for admin. In response you will get list of users.
/api/users/{id} - access for current user or admin. In response you will get specific user by id.

POST:
/api/users - access for unauthenticated user. By this endpoint you can add new user.

PATCH:
/api/users/{id} - access for current user or admin. By this endpoint you can update specific user by id.

DELETE:
/api/users/{id} - access for current user or admin. By this endpoint you can delete specific user by id.
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
