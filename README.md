# Auto-Test Framework

Selenium & Java based frontend automation suite utilising the BDD methodologies of Cucumber and Gherkin

### 1. Install JAVA SDK 8

### 2. Install Maven

### 3. Install Git

### 4. Install Intellij

### 5. Download chromedriver from https://sites.google.com/a/chromium.org/chromedriver/

### Set Path Variables

User Variable Path = directory for java sdk 8 Set Maven home in environment Variables

### For Windows

Create the following System variables

- JAVA_HOME = PAth to java sdk
- M2_HOME = Path to maven installation
- MAVEN_HOME = Path to maven installation

### For Windows Edit Path System variable

- Add %M2_HOME%\bin
- Add directory to your chromedriver.exe

### Software preparatation

- git clone give_path_of the_project 
- mvn clean compile 

Framework Architecture
--------------

	Project-DemoProject
		|
		|_src/main/java/com.gui.cucumber		
		|	|_dataproviders
		|	|	|_ConfigFileReader		
		|	|	|...
		|	|_Helpers
		|	|	|_Driver				
		|	|	
		|	|	|...
		|	|_Pages
		|	|	|_Base
		|	|	|_PageObjects
		|	|	
		|	|_Helpers
		|	|	|_Driver				
		|	|	
		|	|_webdriverwait
		|	|	|_WebdriverUtils.java	
		|	|	

		|_src/test/java/com.gui.cucumber
		|	|_Stepdefinition
		|	|	|_Stepdefinition.java		
		|	|_TestUtils
		|	|	|_BeforeAfter.java
		|	|	|_DemoRunner.java
		|	|	|...
		|_src/test/resources
		|	|_features
		|	|	|_BorrowingCalculator.feature

* **src/main/java/dataproviders - to access property file
* **src/main/java/Helpers - browser setup is done in driver class
* **src/main/java/Pages - Java class whereby the necessary HTML objects are captured as WebElements to be manipulated by the associated model class
* **src/main/java/Webdriverwait - to access wait methods 
* **src/test/resources/features - all the cucumber features files (files .feature ext) goes here.
* **src/test/java/StepDefinition - you can define step defintion under this package for your feature steps.
* **src/test/java/TestUtils/DemoRunner - to run feature files/ scenarios
* ** tool - chromedriver.exe 
* ** Configuration.properties - given properties like URL , credentials and browser 

Steps to write an acceptance test - 

Each Feature file has it’s step defition, pageobject & model class

1-Create Feature file and define feature scenario test & Testrail ID’s
2-Run project and copy method stubs from output
3-Create Step definition class & paste method stubs
4-Create Page Object Class and define objects required
5-Create Model Class & Define actions require with page objects
6-write automation script in Step definition & execute tests required per step
7-Run feature file/scenario using runner class
