Introduction
This is a sample Rest API test solution for sample endpoints available in https://jsonplaceholder.typicode.com/. The published APIs represent a blog application where users can publish posts, todos and comment on them.
Tests are written using a combination of RestAssured, Cucumber, TestNG, Allure Reports, Postman & Maven.
Framework & Design Considerations
RestAssured is used in writing this project using the BDD framework of Cucumber for documentation and reporting.
API calls & validations are made using RestAssured.
Tests are written in BDD Gherkin format in Cucumber feature files and it is represented as a living documentation in the test report.
This solution is designed in an SOLID pattern with the code base categorized into domain model packages based on user actions like search, post and comment to understand/validate results.
These domain models are called from a step-definitions class which are in-turn called from BDD tests. The step definitions files are generated from the feature files, so the feature files invariably act as the inline documentation for each definition file.
There are 4 scenarios in total to cover which includes 
Search for the user with username “Delphine”.
Use the details fetched to make a search for the posts written by the user.
3. For each post, fetch the comments and validate if the emails in the comment section are in the proper format.
4. Think of various scenarios for the test workflow, all the things that can go wrong. Add them to test coverage
 
 
 
 
Project Structure and Explanation


Allure Report is used in generating reports for the project. t o use it in this project, the user will download it on their system by following this tutorial - https://docs.qameta.io/allure/ . This link contains the minimal setup for each system conf=ig like Mac, Windows, Linux. 

The Cucumber feature files are stored in the feature directory. The step definitions come after the feature files, so they are stored in the stepdefinitions package. The POJO classes for response validations are stored in the POJO packages. Being a simple project, it sounds like an overkill running against all the POJO classes. 

The Init class is used for class initialization of default files that have  to run before others, and the property files where the variables are stored. The TestCases class is where the actual method for the TestCases covered  in this project are stored.

In tge resources folder we have the schema.json and unique-property.properties file wherre the variables are stored.
