# CucumberRestAssured-Java

REST API automation framework using Cucumber, Rest Assured

Project Description
This project is to implement a framework for REST API automation tests with Cucumber and Rest Assured for sample restful-booker APIs. Project was developed using:

Cucumber - 6.10.4
rest-assured.version - 5.5.1 

Jackson Core Databind - Object Mapper - to serialize objects to json and to deserialize json to objects
Note: RestAssured, the widely used testing library for API automation is a wrapper built on Apache Http Client

Design
POJO Classes:
To enable easy test creation and maintenance, the request and response json fields are modeled as POJO classes
Use of Gson/Jackson annotations helps simplify the creation of classes as only the fields need to be declared
Request: To create request JSON, it is sufficient to create object(s) for the corresponding request model classes and set values according to the tests. Then Object Mapper (Jackson - databind) can be used to serialize the object(s) to json before submitting API calls
Response: To enable ease of access and parsing response JSON, object mapper can parse the response JSON string as object(s) of response model classes
POJO classes can be found at src/main/java/com/automation/api/request/dto and  src/main/java/com/automation/api/response/dto package
Acceptance Tests:
Acceptance Tests written as Cucumber feature file can be found at src/test/resources/features/Booking.feature
The tests perform a Get and Post call to APIs hosted at https://restful-booker.herokuapp.com/
Assertions:
JUnit assertion methods are used to verify response JSON data
Prerequisites to Run the project
Environment Requirements: Maven v3.5.4 or later, Java 1.8 or later, Eclipse or IntelliJ IDE
Execution Instructions
After cloning the project, compile using the IDE Maven plugin or mvn clean compile
Review the code to understand the flow
src/test/java - has the code for Cucumber tests implementation
src/test/resources - has the feature file
Command Line:
mvn clean test - This will run the scenarios in Cucumber feature files

Run Configuration:
Alternative way to run the project is by use of: TestRunner at src/test/java/com/automation/demo/TestRunner.java or Using IDE Run configuration, for IntelliJ:

Right click project and click Run as
Select Maven Build
Enter goals - clean test
Click Run
Reports Location:
After execution, reports can be accessible via the link displayed in the maven logs (provided by Cucumber latest version).

When this project is integrated in a Jenkins pipeline, cucumber.json file in target/cucumber-reports can be integrated in the Build using Jenkins Cucumber plugin