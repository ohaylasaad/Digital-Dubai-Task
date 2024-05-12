"# Digital-Dubai-Task" 

Read me file :

This repository contains automated tests for testing SwagLabsAutomtion.


Setup and Execution:
Clone the Repository using :
git clone https://github.com/ohaylasaad/Digital-Dubai-Task

Navigate to the Project Directory using :
cd repository

Run Tests :-
Execute the following Maven command to run the tests and generate Allure reports:
mvn clean test
View Test Reports:
After the tests complete, you can generate and view Allure test reports by running:
allure serve target/allure-results
If you encounter any issues, ensure that your environment meets the prerequisites(Java,TesNg, Maven and allure) and dependencies are correctly configured in the pom.xml

If you are using editor for example Eclipse IDE :
Navigate to src/Tests/TestAutomation.java then right click and choose Run As (TestNG Test,Maven Test).

After running you should be able to get running report using  multiple ways:
Editor return Test Passed cases in console.
also Results of running class TestAutomation. 
You can navigate to test-output then right click on (index.html or emailable-report.html) and open with web Browser. 

