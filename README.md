# Games-Service
A SpringBoot REST API which returns stored information from MongoDB about games.

## Prerequisites
* [Java 8+](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) - Language of Choice is Java 8
* [Maven](https://maven.apache.org/) - Dependency Management
* [MongoDB](https://www.mongodb.com/) - Uses MongoRepository to access data

## Download/Clone
Clone the develop branch of the project into your chosen location, e.g.
```
git clone https://github.com/abemyatt/Games-Service.git
```

## Using the program
###Importing the project
Import the pom.xml file contained at the root of the project (/Games-Service/pom.xml) into your preferred IDE and import the maven projects.

Choose Java 8 or a higher version for the JDK to properly compile the program.

### MongoDB
Start MongoDB on port 27017 by typing the following command
```
mongod
```
Create a database in MongoDB called 'gameDB' with a collection called 'games'

Populate the collection with documents of the format:
```
{
    "_id" : "1",
    "title" : "Uncharted 4",
    "description" : "For the first time ever in Uncharted history, drive vehicles during gameplay",
    "by" : "Sony",
    "platform" : [ 
        "PS4"
    ],
    "age_rating" : "16",
    "likes" : 100,
    "comments" : [ 
        {
            "user" : "bob",
            "message" : "Cracking game far too much cinematic",
            "dateCreated" : NumberLong(1589932800),
            "like" : 6
        }, 
        {
            "user" : "testingPriest",
            "message" : "Not enough shooting for me,far too easy ",
            "dateCreated" : NumberLong(1589760000),
            "like" : 5
        }
    ]
}
```

### Running the program
Run the GamesServiceApplication main method to start the application


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

### Running the tests
The project contains unit tests which can be run. In IntelliJ Idea, open the test folder and right click java, then click 'Run All Tests'. Your preferred IDE may differ.

Alternatively, to run through the terminal, type the following at the root of the project:
```
mvn test
```


