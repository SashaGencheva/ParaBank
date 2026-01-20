# Project Title
ParaBank

## Table of contents
* [Description](#description)
* [Technologies, languages and frameworks](#technologies-languages-and-frameworks)
* [Setup](#setup)

## Description
This simple project demonstrates the automation of Web and API testing for the banking web application https://parabank.parasoft.com
The tests included in the project verify the following features:

- User registration (Web)
- User authentication (Web and API)
  - valid test data
  - invalid test data
- User logout (Web)
- Retrieve user details (API)
- Retrieve user accounts (API)
- Bill payment (API)

## Technologies, languages and frameworks
Project is created using:
* Selenium WebDriver
* Rest Assured
* Java
* Maven
* TestNG
* POM with PageFactory

## Setup
### Prerequisites
Before running the program install:
* Java 17 or higher
* IntelliJ IDEA
* Set up the environment variable for Chrome Driver

### Run instructions
* Clone the repo https://github.com/SashaGencheva/ParaBank.git
* Open the project in IntelliJ IDEA
* Run testng.xml file or TestRunner class