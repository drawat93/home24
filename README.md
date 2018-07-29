# Home24 Task

This project has been build using Java with Maven, jars and plugins used are mentioned in pom.xml file which is located on project root folder. Aim of this project is to automate login scenarios of Home24

# Project Structure:

i) Packages

a) src/main/java/pageObjects: This package has classes which contain all the locators and their corrosponding methods for doing 
operation on Home24

b) src/main/java/utilities: This package has classes where custom made java methods are present which we will use for perfoming operations such as Reading Excel, TakingScreenshots, Reading Properties etc.

c) src/main/java/resources: This package has all the resources which will be required to run test cases, it contains webdrivers and configuration files(.properties and .xml)  which are being used in this project.

d) src/test/java/tests: This package will contain all test classes related to Home24. 

e) src/test/java/baseTests: This package contain base classes which contains all the before and after TestNG annotated methods which are required to run test cases in Home24 test cases.

f) src/test/java/testData- This package will have all the test data required for running test cases, currently this package has data in Excel format


ii) CustomTestOutput

a) Logs- Logs will be created under log folder in customTestOutput folder(located in root project foler) in .log and .html format. Path: src/CustomTestOutput/log

b) Automation Test Report: Extent-reports will be generated in extent-reports folder which is present under customtestOutput folder. Path: src/CustomTestOutput/extent-reports

c) Screenshots: Screenshot of failed test cases will get captured in FailedCase_ScreenShots folder. Path: src/CustomTestOutput/FailedCase_ScreenShots



# Pre-Requisite to run this project

    OS: Mac OS
    Java: http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html
    IDE: Eclipse IDE 
    Browser: Google Chrome or Firefox Latest Version

# How to run this project ?

    Clone or download the repository
    Open the project in eclipse IDE
    Import the Maven project and then run pom.xml file to install all dependencies
    Run the testng.xml file given in root project folder as TestNG test
