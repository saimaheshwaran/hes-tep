[![Tests](https://github.com/ey-advisory-technology-testing/ngtp-trunk/actions/workflows/tests.yml/badge.svg)](https://github.com/ey-advisory-technology-testing/ngtp-trunk/actions/workflows/tests.yml)

# Test Elevate Platform

Test Elevate Platform is a Junit based test automation framework to test Web, API and Database.
It enables you to write and execute automated acceptance/unit tests.
Automate your test cases with minimal coding.

## Table of Contents
<ul>
	<li>
		<a href = "#introduction">Introduction</a>
	</li>
	<li>
		<a href = "#installation">Installation</a>
		<ol>
			<li><a href = "#version-control-System-GitHub">Version control System GitHub</a></li>	
			<li><a href = "#install-java-development-kit">Java Development Kit</a></li>
			<li><a href = "#download-and-setup-maven">Download and setup Maven</a></li>
			<li><a href = "#setup-environment-variables">Setup Environment Variables</a></li>
			<li><a href = "#intellij">IntelliJ</a></li>
			<li><a href = "#configure-dependencies-and-addons-libraries">Configure dependencies and addons Libraries</a></li>
		</ol>
	</li>
	<li>
		<a href = "#usage">Core Components</a>
		<ol>
			<li><a href = "#writing-a-test">Api</a>
				<ul>
					<li><a href = "#git">Creating a API Test</a></li>
				</ul>
				<ul>
					<li><a href = "#git">Input Data</a></li>
				</ul>
				<ul>
					<li><a href = "#git">Running API Test</a></li>
				</ul>
				<ul>
					<li><a href = "#git">Viewing API Test result</a></li>
				</ul>
			</li>
			<li><a href = "#object-repositories">Web</a></li>
			<li><a href = "#reusable-steps">Database</a></li>
		</ol>	
	</li>
</ul>
	


## Introduction

JUnit is an essential unit testing framework for Java, streamlining the development process with simple annotations, assertions, and test runners. It facilitates test-driven development, enabling consistent and efficient testing practices that integrate seamlessly with build tools and CI/CD pipelines. Adopting a JUnit-based automation framework enhances software quality by enabling thorough regression testing and rapid identification of bugs.

## Installation

## Version control System GitHub

1. **Installation**: Download and install GitHub Desktop from the official website. Log in with your GitHub credentials to connect your repositories.
2. **Clone a Repository**: Start by cloning a repository from GitHub to your local machine, creating a local copy to work on.
3. **Make Changes**: Edit files in your preferred text editor or IDE. GitHub Desktop will track these changes and show them as unstaged changes.
4. **Commit Changes**: Stage your changes and write a commit message that describes the updates. Committing saves your changes to the local repository.
5. **Sync with GitHub**: Push your commits to GitHub to update the remote repository. You can also pull changes made by others to keep your local repository in sync.

## Java Development Kit
Install Java JDK
Windows
Install OpenJDK 17 or above. Go for a stable build which ever version you opt for. Once you downloaded the zip file, right click on it, select ‘Extract All’, and extract the files onto your computer under a folder Java.

## Download and setup Maven
Install Maven
Windows- 
Download Required version: 3.9.9 of apache-maven. Download the link against the row that says Binary zip archive Once you downloaded the zip file, right-click on the zip file, select ‘Extract All’, and extract the files onto your computer under a folder named ‘Maven’.

## Setup Environment Variables
Windows- 
How to set new environment variables
1. Click the file explorer icon on your computer
2. Right click on "This PC" and select the "Properties" option
3. Click on advanced system settings
4. On the Advanced tab, select System Environment Variables in the bottom pane, and then click new
5. Furnish the variable name and path. Click OK

Note: If your Administrator does not allow you access to System Variables you can select User Environment Variables on the top pane in Step 4 above.

Setup new variables – Click New
JAVA_HOME: <path to your JDK folder containing bin>
  > e.g. C:\softwares\jdk-17.0.2
> 
MAVEN_HOME: <Path to your Maven folder>
> e.g. C:\maven\apache-maven-3.9.9

## IntelliJ
Windows- 
Download IntelliJ Community Version
Click next through all the setup instructions. Click finish at the end. Required version: 2019.2.2 or later

## Configure dependencies and addons Libraries

IntelliJ IDEA requires a Java Development Kit (JDK) to develop Java applications.

Configure the JDK in IntelliJ IDEA- 

1. Open IntelliJ IDEA and either create a new project or open an existing one.
2. Go to `File` > `Project Structure` or press `Ctrl` + `Alt` + `Shift` + `S` on your keyboard to open the Project Structure dialog.
3. In the Project Structure dialog, select the `SDKs` under the Platform Settings section.
4. Click the `+` button to add a new JDK, and a file chooser will appear.
5. Navigate to the JDK installation path you located earlier, select the JDK's root directory, and click `OK`.
6. IntelliJ IDEA will load the selected JDK and display its version in the list of SDKs.

![image](https://github.com/user-attachments/assets/50081139-7f10-40ca-b598-692d252bb5b2)

Configure Maven in IntelliJ IDEA- 

1. Open IntelliJ IDEA and either create a new project with Maven or open an existing Maven project.
2. Go to `File` > `Settings` (on Windows/Linux) or `IntelliJ IDEA` > `Preferences` (on macOS).
3. In the Settings/Preferences dialog, navigate to `Build, Execution, Deployment` > `Build Tools` > `Maven`.
4. In the Maven settings, you'll see the 'Maven home directory' field. Click the browse button (the folder icon) next to it.
5. Navigate to the Maven installation path you located earlier and select the Maven directory. Click `OK`.
6. Optionally, you can configure other Maven settings such as user settings file, local repository, and importing options according to your project needs.
7. Click `Apply` and then `OK` to save the changes.

![image](https://github.com/user-attachments/assets/d3e21040-0536-485a-b729-1bda762c2514)
   
## Core Components

In the core components we can execute all the tests from Api, web, and Database by using the respective Jar dependencies added in Pom.xml.

e.g.. Below jar for Api components

    <groupId>com.tep</groupId>
    <artifactId>api</artifactId>
    <version>1.0.5</version>

### API

In the Test-Elevate framework, test cases are written in Java and are located in the `test` directory of your project. Each test case should be annotated with `@Test`.

To get started with writing tests using the JUnit framework, you can explore the test cases at api/src/test . This test case file contains test methods that demonstrate how to verify API request.

### Creating an API test

We can create tests in java class under api/src/test/java/ location and every test should have @Test annotation
Following are the different scenarios where you can update the data for the respective tests


Scenario1: Test for GET request without query parameters 

Scenario2: Test for GET request with query parameters 

Scenario3: Test for POST request by adding Cookies, Headers and Query parameters 

Scenario4: Test for POST request by updating Cookies, Headers parameters and validating the response

Scenario5: Test for POST request by deleting the Cookies parameters and validating the response

Scenario6: Test for POST request by manipulating(Set,Update and Delete) form parameters and validating the response

Scenario7: Test for POST request by updating the request body and validating the response


### Input Data

In Test-Elevate framework, page. You can store all your GetRequest, PostRequest, BaseURI, Endpoint, QueryParams, PathParams, Headers in YAMLformat. 
File location - "api/src/test/resources/testProject/input.api"

In YAML,

````
"catfact_facts":
    baseUri: https://catfact.ninja
    endpoint: facts
    queryParams:
      limit: 2
    
"base_param_post":
    baseUri: https://httpbin.org/anything
    headers:
      Authorization: Bearer ${DEV_BEARER_TOKEN}
    basePath: user/userpath
    body: |
      {
          "my_integer": 123,
          "my_string": "string",
          "my_boolean": true,
          "my_double": 12.34,
          "my_string_array": ["str1", "str2", "str3"],
          "my_number_array": [1, 2, 3, 4.4, 10.1],
          "my_boolean_array": [true, true, true],
          "body_placeholder": "${my_saved_integer}",
          "my_unset_variable": "",
          "nest" : {
              "a": 1,
              "b": true,
              "c": [1, 2, 3, 4, 5]
          }
      }   
````

### Running API Test
"api.properties file", is used in our frameworks to set up and manage environment-specific properties.

 File Location- "api/src/main/resources/api.properties"
![image](https://github.com/user-attachments/assets/75cbbffb-3de9-4e85-9d26-9ffd741c4e08)

 "tep.properties file" is used to manage and configure various aspects of the test environment.
   api=true - This line indicates that API testing is enabled. When the testing framework reads this property, it will 
   include API tests in the test execution.
   
   File Location- "api/src/main/resources/tep.properties"
   ![image](https://github.com/user-attachments/assets/680e830e-4fd5-4653-8aa5-1dfc25e2df2a)


### Viewing API Test Result

