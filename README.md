# Games-Service
A SpringBoot REST API which returns stored information from MongoDB about games.

## Prerequisites
[Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) - Language of Choice is Java 8
[Maven](https://maven.apache.org/) - Dependency Management
[MongoDB](https://www.mongodb.com/) - Uses MongoRepository to access data

## Using the program
Start MongoDB on port 27017 by typing the following command
```
mongod
```
Create a database in MongoDB called 'gameDB' with a collection called 'games'

The program runs by default at localhost:8080 and the endpoints are
```
localhost:8080/games/{id}
```
and
```
localhost:8080/games/report
```
These can be changed in the application.properties folder found in src/main/resources.

The endpoints are mapped for GET requests and will return data in JSON format.

## Running the tests
The project contains unit tests which can be run. In IntelliJ Idea, open the test folder and right click java, then click 'Run All Tests'. Your preferred IDE may differ.

Alternatively, to run through the terminal, type the following at the root of the project:
```
mvn test
```


