# spring-java-users
Spring Boot Java app with Users service

Before running, be sure to update the .env file, and have Docker, Docker Compose, & Postman already installed.

Start the Config, Discovery, Gateway, and User services. If using a terminal, go to each services respective folder and run:
```
mvn spring-boot:run
```

Once all service have started, use Docker Compose from the root project directory to launch the additional services:
```
docker compose up
```
To create a new user, use the following POST request:
```
http://localhost:8082/users-service/users
```
and be sure the body of the request looks something like this:
```
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "test4@test.com",
    "password": "12345678"
}
```
Once the user has been created, you can login using the following POST request:
```
http://localhost:8082/users-service/login
```
and be sure the body of the request looks something like this:
```
{
    "email": "test@test.com",
    "password": "12345678"
}
```
The response body after successfully logging in will provide a token. You can then use this token to fetch information about the user per the following GET request:
```
http://localhost:8082/users-service/users/add_your_user_token_here
```
