**Introduction**

>This is a sample Rest API test solution for sample endpoints available in https://jsonplaceholder.typicode.com/. The published APIs represent a blog application where users can publish posts, todos and comment on them.

Tests are written using a combination of 
- RestAssured, 
- Cucumber, 
- TestNG, 
- Allure Reports, 
- Postman & 
- Maven.

**Framework & Design Considerations**
- RestAssured is used in writing this project using the BDD framework of Cucumber for documentation and reporting.
- API calls & validations are made using RestAssured.
- Tests are written in BDD Gherkin format in Cucumber feature files and it is represented as a living documentation in the test report.
- This solution is designed in an SOLID pattern with the code base categorized into domain model packages based on user actions like search, post and comment to understand/validate results.
- These domain models are called from a step-definitions class which are in-turn called from BDD tests. 
- The step definitions files are generated from the feature files, so the feature files invariably act as the inline documentation for each definition file.

**There are 4 scenarios in total to cover which includes**
1. Search for the user with username “Delphine”.
2. Use the details fetched to make a search for the posts written by the user.
3. For each post, fetch the comments and validate if the emails in the comment section are in the proper format.
4. Think of various scenarios for the test workflow, all the things that can go wrong. Add them to test coverage
 

**Project Structure and Explanation**
<img width="319" alt="Screen Shot 2021-07-17 at 4 34 18 PM" src="https://user-images.githubusercontent.com/1056293/126043898-20b78607-a1e5-4a6e-8529-6954738c95e6.png">

>Allure Report is used in generating reports for the project. To use it in this project, the user will download it on their system by following this tutorial - https://docs.qameta.io/allure/ . This link contains the minimal setup for each system config like Mac, Windows, Linux. 

The Cucumber feature files are stored in the feature directory. The step definitions come after the feature files, so they are stored in the stepdefinitions package. The POJO classes for response validations are stored in the POJO packages. Being a simple project, it sounds like an overkill running against all the POJO classes. 

The Init class is used for class initialization of default files that have  to run before others, and the property files where the variables are stored. The TestCases class is where the actual method for the TestCases covered  in this project are stored.

In the resources folder we have the schema.json and unique-property.properties file wherre the variables are stored.


**Executing the tests**
The tests can be executed in 2 ways:
- Clone the project git by issuing git clone https://github.com/ken4ward/MobiquityProject.git
- Open the project in the IDE (Eclispe or Intellij [though Intellij is preferered as it is the IDE usedd in developing it.]) of your choice. 
- Firstly, Locate testng.xml in terh root directory, right click and run.
- Secondly, Locate TestCases.java file and run it from the class level. If only a method is ran, it can't pass the variables other methods epends on to them.
- Run this command to generate the Allure report - allure serve {the allure-report directory in the root directory of the project}. 
Example is this “allure serve /Users/wikiwoo/MobiquityProject/allure-results”


