# Test Elevate Platform

Test Elevate Platform is a test automation framework to test Web, API and Database.
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
			<li><a href = "#configure-dependencies-and-addons-libraries">Configure dependencies and addons Libraries</a>
		<ul>		
		<li><a href = "#intellij">Configure JDK in Intellij IDE</a></li>
		<li><a href = "#intellij">Configure Maven in Intellij IDE</a></li>
			</li>
		</ul>
		</ol>
	</li>
	<li>
		<a href = "#usage"> Purpose of Utilities Project</a>
		<ol>
		<li><a href = "#git">How to build Utilities Artifact</a></li>
			<li><a href = "#git">How to use Utilities Artifact in Api/Web project</a>
			<ul>
           <li><a href = "#Api">Utilities is used by Api project</a></li>
	       <li><a href = "#Web">Utilities is used by Web project</a></li>
           </li>
           </ul>  
			</li>
			<li><a href = "#git">Utilities functions - Quick Read</a>
           <ul>
        	<li><a href = "#Enums">Enums</a></li>
	   		<li><a href = "#ExcelReader">ExcelReader</a></li>
			<li><a href = "#Constants">Constants</a></li>
			<li><a href = "#ExceptionUtils">ExceptionUtils</a></li>
			<li><a href = "#JsonManipulator">JsonManipulator</a></li>
			<li><a href = "#JsonUtils">JsonUtils</a></li>
			<li><a href = "#MapUtils">MapUtils</a></li>
			<li><a href = "#MethodUtils">MethodUtils</a></li>
			<li><a href = "#PlaceHolderReplacer">PlaceHolderReplacer</a></li>
			<li><a href = "#PropUtils">PropUtils</a></li>
			<li><a href = "#StringToType">StringToType</a></li>
			<li><a href = "#StringUtils">StringUtils</a></li>
			<li><a href = "#YamlReader">YamlReader</a></li>
           </li>
           </ul>  
		   	</li>
		</ol>	
	</li>
</ul>
	


## Introduction

Test Elevate is an essential unit testing framework for Java, streamlining the development process with simple annotations, assertions, and test runners. It facilitates test-driven development, enabling consistent and efficient testing practices that integrate seamlessly with build tools and CI/CD pipelines. Adopting a JUnit-based automation framework enhances software quality by enabling thorough regression testing and rapid identification of bugs.

## Installation

Note : Please feel free to skip the Java and Maven installation section if you have it installed.

## Version control System GitHub

Disclaimer : GitHub Desktop is used to pull the latest code from BitBucket repository. Please feel free to use the version control of your choice to pull the latest repo.

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
Download Required version: 3.6.3 of apache-maven. Download the link against the row that says Binary zip archive Once you downloaded the zip file, right-click on the zip file, select ‘Extract All’, and extract the files onto your computer under a folder named ‘Maven’.

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
> e.g. C:\maven\apache-maven-3.6.3

## IntelliJ
Windows- 
Download IntelliJ Community Version
Click next through all the setup instructions. Click finish at the end. Required version: 2019.2.2 or later

## Configure dependencies and addons Libraries

IntelliJ IDEA requires a Java Development Kit (JDK) to develop Java applications.

## Configure JDK in Intellij IDEA- 

1. Open IntelliJ IDEA and either create a new project or open an existing one.
2. Go to `File` > `Project Structure` or press `Ctrl` + `Alt` + `Shift` + `S` on your keyboard to open the Project Structure dialog.
3. In the Project Structure dialog, select the `SDKs` under the Platform Settings section.
4. Click the `+` button to add a new JDK, and a file chooser will appear.
5. Navigate to the JDK installation path you located earlier, select the JDK's root directory, and click `OK`.
6. IntelliJ IDEA will load the selected JDK and display its version in the list of SDKs.


## Configure Maven in Intellij IDE- 

1. Open IntelliJ IDEA and either create a new project with Maven or open an existing Maven project.
2. Go to `File` > `Settings` (on Windows/Linux) or `IntelliJ IDEA` > `Preferences` (on macOS).
3. In the Settings/Preferences dialog, navigate to `Build, Execution, Deployment` > `Build Tools` > `Maven`.
4. In the Maven settings, you'll see the 'Maven home directory' field. Click the browse button (the folder icon) next to it.
5. Navigate to the Maven installation path you located earlier and select the Maven directory. Click `OK`.
6. Optionally, you can configure other Maven settings such as user settings file, local repository, and importing options according to your project needs.
7. Click `Apply` and then `OK` to save the changes.

    
### Utilities

In the Test-Elevate framework, "Utilities" project is a collection of reusable functions or modules that can be shared across multiple projects. 
This approach is common in software development to promote code reuse, maintainability, and to reduce redundancy.

### How to build Utilities Artifact

Steps to create utilities jar in Nexus Repo:-

1. Pull the latest code from your source repository.
     http://git.hestest.com:7990/projects/DEVOPS/repos/test-elevate-utilities/browse
2. In the project directory, run the Maven command to update your project dependencies:
   Run the command: mvn clean install
3. To build the project, navigate to Build >> Build project in Intellij 
4. After making the necessary updates to the utilities framework, update the version number in POM
5. Checkin the latest code in the git repo mentioned in step 1
6. Navigate to Jenkins and open the utilities framework pipeline:
	https://jenkins-devops.hestest.com/job/QA/job/test-elevate/job/Test-Run-Utilities/
7. Click on Build now to run the pipeline
8. Once completed, you can check the latest core jar in the snapshots folder in Nexus repo
	http://co1-rep01sha01.test.healthe:8080/nexus/#browse/browse	

## How to use Utilities Artifact in Api/Web project

### Utilities is used by Api project. 

Utilities dependency is called as part of test-elevate-api project. So no need to explicitly add utility dependency for any API project.
To create any new API project, you need to add API jar available in nexus repository. Details mentioned as part of test-elevate-api Readme file

 
### Utilities is used by Web project. 

Please follow these steps to incorporate utilities in web project.

1. Create a New Maven project >> Enter a name for your project in the "Project name" field.
2. Choose the location where your project will be stored by clicking on the "..." button and selecting a directory. >> Finish
3. Open your project >> open the "pom.xml" file located in the "Project" view on the left-hand side.
4. Confirm that the Utilities project JAR is available in nexus repository.
5. In your Web project's pom.xml, add the Utilities project as a dependency:

		<repositories>
        	<repository>
				<id>internal-nexus</id>
				<url>http://co1-rep01sha01.test.healthe:8080/nexus/content/groups/snapshots/</url>
			</repository>
       </repositories>  

		<dependencies><!-- Utilities Project Dependency -->
			<dependency>
			   <groupId>com.tep</groupId>
			   <artifactId>utilities</artifactId>
			   <version>1.0.0</version>
			   </dependency>
		</dependencies>

6. In the project directory, run the Maven command to update your project dependencies:
   Run the command: mvn clean install		
7. You can now use the functionality provided by the utilities project within your Web project.


# Utilities functions - Quick Read

## Enums

Java class named Enums within the com.tep.utilities package. This class contains three enumerations (enum): BrowserType, Manipulation_Mode, and Comparison_Type. The BrowserType enum lists supported web browsers (Chrome, Firefox, Safari, Edge, and a default option). The Manipulation_Mode enum specifies modes for data manipulation (Set, Update, Delete), and the Comparison_Type enum includes various comparison operations for data validation. The class also has a private constructor to prevent its instantiation, ensuring that it is used only to access the enums it defines.

## ExcelReader

Java class named ExcelReader in the com.tep.utilities package, designed to read data from Excel files. It uses the Fillo library to query Excel data with SQL-like syntax and the Apache POI library to interact with Excel sheets. The class maintains a nested LinkedHashMap to store the retrieved data in a structured format. The getPageObjects method reads page objects from an Excel file, organizing them into this map, while the getSheetNames method retrieves the names of all sheets in the Excel file. The class handles file connections and exceptions, ensuring the connection is closed after use.

## Constants

This class serves as a utility to store static constants and methods for a framework. It includes paths to various directories such as the project base, main, test, and target directories, as well as paths to property files like web.properties, tep.properties, and api.properties. The class also contains a static block that loads properties from the tep.properties file and initializes related paths.The class is designed to centralize configuration and common utility functions for ease of maintenance and reuse across the framework.

## ExceptionUtils

The ExceptionUtils class in the com.tep.utilities package provides static utility methods for error handling in Java. It includes functions to log errors and throw exceptions, specifically IllegalArgumentException and RuntimeException, when certain conditions are met, such as an object being null. The class is designed to be non-instantiable with a private constructor and uses the SLF4J logging facade for logging errors. Methods are overloaded to offer flexibility in specifying custom error messages and using different loggers.

## JsonManipulator

The JsonManipulator class in the com.tep.utilities package is designed to handle JSON data manipulation using the Jackson library for parsing and updating JSON structures, and JsonPath for querying. It provides methods to read, write, update, and delete JSON elements, as well as to handle exceptions and log errors using SLF4J. The class encapsulates a JsonNode object representing the JSON data and offers a string constructor to initialize the JSON structure from a given JSON string.

## JsonUtils

The JsonUtils class in the com.tep.utilities package provides a static method for converting Java objects to JSON string representations using the Jackson library. The class has a private constructor to prevent instantiation, making it a utility class. It handles null objects by returning the string "null" and logs errors during the conversion process, returning a default error string if an exception occurs.

## MapUtils

The MapUtils class in the com.tep.utilities package provides static methods for working with maps, including converting maps to string representations, transforming Cucumber DataTables into maps, and updating maps based on different modes (set, update, delete). The class is designed to be non-instantiable with a private constructor and uses SLF4J for logging. It also integrates with the ExceptionUtils class for error handling.

## MethodUtils

The MethodUtils class in the com.tep.utilities package provides a static method for executing a given method with a retry mechanism that includes an exponential backoff strategy. It is designed to be non-instantiable with a private constructor and uses SLF4J for logging. The executeWithRetry method attempts to execute a supplied method multiple times (up to a specified maximum) and waits for an increasing duration between retries, with a cap on the maximum wait time. If all retries fail, it throws a RuntimeException.

## PlaceHolderReplacer

The PlaceHolderReplacer class in the com.tep.utilities package is a utility class for replacing placeholders in a string with corresponding values from a provided map. Placeholders are expected to be in the format ${placeholder}. The class allows for case-insensitive matching of placeholders by converting keys to lowercase and supports the option to replace unknown placeholders with a default value or leave them unchanged.

## PropUtils

The PropUtils class in the com.tep.utilities package is a utility class for handling operations on properties files. It provides methods to read, write, and retrieve key-value pairs from a properties file. The class is initialized with the path to a properties file, and it loads the properties into a Properties object. It also allows for converting the properties to a Map for easier access and manipulation, and includes functionality to update the properties file with new or changed key-value pairs.

## StringToType

The StringToType class in the com.tep.utilities package provides static methods to convert string representations of values into their respective data types based on a suffix that indicates the type (e.g., ":int" for integers, ":bool" for booleans). It uses an ObjectMapper for JSON parsing when the suffix indicates a JSON object. The class cannot be instantiated due to its private constructor and throws a RuntimeException if parsing fails or the specified data type conversion is not possible.

## StringUtils

The StringUtils class in the com.tep.utilities package offers static methods for string operations, such as obfuscating a string by replacing its characters with asterisks and indenting each line of a given text. The class is designed to be non-instantiable with a private constructor. It provides methods for custom indentation using a specified character and amount, as well as a default indentation using two spaces.

## YamlReader

The YamlReader class in the com.tep.utilities package is a utility class for handling YAML files. It provides methods to read and merge YAML data from files or directories, and to convert YAML data to JSON format. The class uses a YAMLMapper to read YAML and an ObjectMapper with enabled indentation to serialize objects to JSON. It handles file reading and conversion operations, returning the results as maps or JSON strings, and includes error handling for null inputs and I/O exceptions.



