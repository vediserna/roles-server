# Roles-Server

Spring Boot Server 


## Project approach
This project was done as a test for a job application.
I got the project's description in an e-mail and took a few days thinking about how to approach it. My first choice was to create a Flask server(which I did) since I had been working a lot with Python for the past months. When I was about to start writing my the tests for the Flask server I decided to step out of the comfort zone and use Java instead to create the API. I have a fair Java progamming experience but this is the first API I write in Java. The project description suggested I would use Spring Boot so I went ahead and did that.
Since I very little experience with the framework I designed the API using [swagger](http://editor.swagger.io/) and generated a spring Server from there. I have since then changed about 80% of what was generated.

#### Project Design
I usually layer my applications and I did that for this project, i.e. use the 3 tier architecture. I think that helps me to write a cleaner code and more modular as well as that design makes it easier for me to refactor. I then also used a tiny bit of dependency injection to help me test the service layer.
When it came to data I got confused on how to approach. I ended up retrieving the data from the Heroku website and created a SQLite database which I populated with the Heroku data. The database contains 4 tables. Users(id, username name), Teams(id, name, lead), Roles(teamId, role) and Membership(memberId, teamId, role).

The program can be downloaded from [GitHub](https://github.com/vediserna/roles-server).

#### Clone with SSH:
1. `git clone git@github.com:vediserna/roles-server.git`
2. `cd roles-server`

#### Clone with HTTPS:
1. `git clone https://github.com/vediserna/roles-server.git`
2. `cd roles-server`

## API documentation
You can view the api documentation in swagger-ui by pointing to `http://localhost:8080` after you have started the server. (`192.168.99.100:8080` if you are using Docker toolbox for windows)  

## How to run the program
#### Locally:
You will need:
* [Java 8 or higher](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) You can run `java --version` to check your current version.
* An IDE (I used [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows) but other IDE's should be fine)

After you have opened up the project you can either run it through your IDE or through command line

In command line, locate to the project's root folder (`/spring-server`). 

Then run:

1. `mvn clean`
2. `mvn install`
3. `mvn spring-boot:run`

The server should now be running on `localhost:8080`
#### With Docker:
You will need to have [Docker installed](https://docs.docker.com/)

In command line, locate to the project's root folder (`/spring-server`).

Then run:
 
`docker run -p 8080:8080 docker-roles-server`

The server should now be running on your computer. If you are using Docker toolbox for windows, the server can most likely be accessed on `192.168.99.100:8080`. If you are not using the docker toolbox for windows you should access the server through `localhost:8080`.

## Tests
To run unit tests in command line;
1. `mvn clean` (If the command has not been run before)
2. `mvn install` (If the command has not been run before)
3. `mvn test`
