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
		<li><a href = "#intellij">Configure Utilities Dependency</a></li>	
        <li><a href = "#intellij">How to build Api Artifact</a></li>
        <li><a href = "#intellij">How to use Api Artifact in other project</a></li>
		</ul>
		</ol>
	</li>
	<li><a href = "#usage">API</a>
		<ol>
			<li><a href = "#git">Creating a API Test</a></li>
			<li><a href = "#git">Input Data</a></li>
			<li><a href = "#git">Running API Test</a></li>
			<li><a href = "#git">Viewing API Test result</a></li>
		</ol>	
	</li>
	<li><a href = "#git">API Project - Quick Read</a>
        <ol>
        	<li><a href = "#Config">Config</a>
            <ol>
			<li><a href = "#git">ApiConfig</a></li>
            <li><a href = "#git">ApiConstants</a></li>
            <li><a href = "#git">ApiEnums</a></li>
            <li><a href = "#git">ApiKeys</a></li>
            </ol>
            </li>
	   		<li><a href = "#request">request</a>
             <ol>
			<li><a href = "#git">ApiRequest</a></li>
            <li><a href = "#git">ApiRequestFactory</a></li>
            <li><a href = "#git">ApiRequestJsonImpl</a></li>
            <li><a href = "#git">ApiRequestXmlImpl</a></li>
            </ol>
            </li>
			<li><a href = "#response">response</a>
            <ol>
			<li><a href = "#git">ApiResponse</a></li>
            <li><a href = "#git">ApiSchema</a></li>
            </ol>
            </li>
			<li><a href = "#Support">Support</a>
             <ol>
			<li><a href = "#git">ApiDriver</a></li>
            </ol>
            </li>
			<li><a href = "#ApiRequest">resources</a>
            <ol>
			<li><a href = "#git">api.properties</a></li>
            <li><a href = "#git">logback.xml</a></li>
            <li><a href = "#git">tep.properties</a></li>
            </ol>
            </li>
        </ol>  
	</li>	
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
 
MAVEN_HOME: <Path to your Maven folder>
  > e.g. C:\maven\apache-maven-3.6.3

## IntelliJ
Windows- Download IntelliJ Community Version
Click next through all the setup instructions. Click finish at the end. Required version: 2019.2.2 or later

## Configure dependencies and addons Libraries

IntelliJ IDEA requires a Java Development Kit (JDK) to develop Java applications.

# Configure JDK in Intellij IDEA- 

1. Open IntelliJ IDEA and either create a new project or open an existing one.
2. Go to `File` > `Project Structure` or press `Ctrl` + `Alt` + `Shift` + `S` on your keyboard to open the Project Structure dialog.
3. In the Project Structure dialog, select the `SDKs` under the Platform Settings section.
4. Click the `+` button to add a new JDK, and a file chooser will appear.
5. Navigate to the JDK installation path you located earlier, select the JDK's root directory, and click `OK`.
6. IntelliJ IDEA will load the selected JDK and display its version in the list of SDKs.



# Configure Maven in Intellij IDE- 

1. Open IntelliJ IDEA and either create a new project with Maven or open an existing Maven project.
2. Go to `File` > `Settings` (on Windows/Linux) or `IntelliJ IDEA` > `Preferences` (on macOS).
3. In the Settings/Preferences dialog, navigate to `Build, Execution, Deployment` > `Build Tools` > `Maven`.
4. In the Maven settings, you'll see the 'Maven home directory' field. Click the browse button (the folder icon) next to it.
5. Navigate to the Maven installation path you located earlier and select the Maven directory. Click `OK`.
6. Optionally, you can configure other Maven settings such as user settings file, local repository, and importing options according to your project needs.
7. Click `Apply` and then `OK` to save the changes.



### How to build Api Artifact

Steps to create api jar in Nexus Repo:-

1. Pull the latest api code from your source repository.
      "http://git.hestest.com:7990/projects/DEVOPS/repos/test-elevate-api/browse"
2. In the project directory, run the Maven command to update your project dependencies:
   Run the command: mvn clean install
3. To build the project, navigate to Build >> Build project in Intellij
4. Navigate to Jenkins and open the api framework pipeline:
   https://jenkins-devops.hestest.com/job/QA/job/test-elevate/job/Test-Run-Api/
5. Click on Build now to run the pipeline
6. Once completed, you can check the latest api jar in the snapshots folder in Nexus repo
   http://co1-rep01sha01.test.healthe:8080/nexus/#browse/browse


### How to use Api Artifact in other project

Api utility is used by others Api projects. Please follow these steps to incorporate them to others project.

1. Create a New Maven project >> Enter a name for your project in the "Project name" field.
2. Choose the location where your project will be stored by clicking on the "..." button and selecting a directory. >> Finish
3. Open your project >> open the "pom.xml" file located in the "Project" view on the left-hand side.
4. In your Web project's pom.xml, add the api project as dependency:

		<repositories>
        	<repository>
				<id>internal-nexus</id>
				<url>http://co1-rep01sha01.test.healthe:8080/nexus/content/groups/snapshots/</url>
			</repository>
       </repositories>  

		<dependencies><!-- api project dependency -->
			<dependency>
			   <groupId>com.tep</groupId>
			   <artifactId>api</artifactId>
			   <version>1.0.0</version>
			   </dependency>
		</dependencies>
         
5. In the project directory, run the Maven command to update your project dependencies:
   Run the command: mvn clean install
6. To build the project, navigate to Build >> Build project in Intellij

 
### API

In the Test-Elevate framework, test cases are written in Java and are located in the `test` directory of your project. Each test case should be annotated with `@Test`.

To get started with writing tests using the JUnit framework, you can explore the test cases at api/src/test . This test case file contains test methods that demonstrate how to verify API request.

### Creating an API test

We can create tests in java class under api/src/test/java/ location and every test should have @Test annotation
Following are the different scenarios where you can update the data for the respective tests

In an api.properties file, ENVIRONMENT = dev - Based on the environment values it will take the input from Yaml file.
for e.g:-ENVIRONMENT = dev in api.properties file so the same environment name is mentioned in YAML file.


Scenario1: Test for GET request without query parameters 

In YAML,

````
dev:
    catfact_fact:
        baseUri: https://catfact.ninja
        endpoint: fact
````

    @Test
    public void api_basic_request_from_yaml() {
        apiDriver.setConfigFromYaml("catfact_fact");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        int code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
    }

Scenario2: Test for GET request with query parameters 

In YAML,
````
dev:
    catfact_facts:
        baseUri: https://catfact.ninja
        endpoint: facts
        queryParams:
        limit: 2
````

    @Test
    public void api_basic_request_from_yaml_with_query_params() {
        apiDriver.setConfigFromYaml("catfact_facts");
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        int code = apiDriver.Response().get().statusCode();
        Assert.assertEquals(200, code);
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
    }

Scenario3: Test for POST request by adding Cookies, Headers and Query parameters 

In YAML,
````
dev:
    httpbin_post:
        baseUri: https://httpbin.org
        endpoint: post
        headers:
        Content-Type: application/json
        User-Agent: "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
        retryOnError:
        enabled: true
````
    @Test
    public void test_set_map_parameters() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.setQueryParams(new HashMap<>() {{
            put("param1", "paramValue1");
        }});
        apiDriver.setCookies(new HashMap<>() {{
            put("cookie1", "cookieValue1");
        }});
        apiDriver.setHeaders(new HashMap<>() {{
            put("header1", "headerValue1");
        }});
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().body("args.param1", equalTo("paramValue1"));
        apiDriver.getResponse().then().body("headers.Cookie", equalTo("cookie1=cookieValue1"));
        apiDriver.getResponse().then().body("headers.Header1", equalTo("headerValue1"));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
    }

Scenario4: Test for POST request by updating Cookies, Headers parameters and validating the response

In YAML,
````
dev:
    httpbin_post:
        cookies:
        MyCookie: MyCookieValue
        headers:
        Authorization: Bearer ${DEV_BEARER_TOKEN}
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

    @Test
    public void test_set_map_parameters_with_mode() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.setQueryParams(new HashMap<>() {{
            put("param1", "paramValue1");
        }}, Enums.Manipulation_Mode.SET);
        apiDriver.setCookies(new HashMap<>() {{
            put("cookie1", "cookieValue1");
        }}, Enums.Manipulation_Mode.UPDATE);
        apiDriver.setCookies(new HashMap<>() {{
            put("MyCookie", "MyCookieValue1");
        }}, Enums.Manipulation_Mode.UPDATE);
        apiDriver.setHeaders(new HashMap<>() {{
            put("header1", "headerValue1");
        }}, Enums.Manipulation_Mode.SET);

        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().body("args.param1", equalTo("paramValue1"));
        apiDriver.getResponse().then().body("headers.Cookie", equalTo("cookie1=cookieValue1; MyCookie=MyCookieValue1"));
        apiDriver.getResponse().then().body("headers.Header1", equalTo("headerValue1"));
        apiDriver.getResponse().then().body("headers.Cookie", containsString("cookie1=cookieValue1"));
    }

Scenario5: Test for POST request by deleting the Cookies parameters and validating the response

In YAML,
````
dev:
    httpbin_post:
        cookies:
        MyCookie: MyCookieValue
        headers:
        Authorization: Bearer ${DEV_BEARER_TOKEN}
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

    @Test
    public void test_set_map_parameters_with_mode_for_delete() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.setQueryParams(new HashMap<>() {{
            put("param1", "paramValue1");
        }}, Enums.Manipulation_Mode.SET);
        apiDriver.setCookies(new HashMap<>() {{
            put("cookie1", "cookieValue1");
        }}, Enums.Manipulation_Mode.UPDATE);
        apiDriver.setCookies(new HashMap<>() {{
            put("MyCookie", "MyCookieValue1");
        }}, Enums.Manipulation_Mode.UPDATE);
        apiDriver.setCookies(new HashMap<>() {{
            put("MyCookie", null);
        }}, Enums.Manipulation_Mode.DELETE);
        apiDriver.setHeaders(new HashMap<>() {{
            put("header1", "headerValue1");
        }}, Enums.Manipulation_Mode.SET);

        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().body("args.param1", equalTo("paramValue1"));
        apiDriver.getResponse().then().body("headers.Cookie", equalTo("cookie1=cookieValue1"));
        apiDriver.getResponse().then().body("headers.Header1", equalTo("headerValue1"));
    }

Scenario6: Test for POST request by manipulating(Set,Update and Delete) form parameters and validating the response

In YAML,
````
dev:
    form_param_post:
        baseUri: https://httpbin.org/anything
        formParams:
        key1: value1
        key2: value2
````
    @Test
    public void test_rest_api_with_form_params_using_config_and_step() {
        apiDriver.setConfigFromYaml("form_param_post");

        apiDriver.setFormParams(new HashMap<>() {{
            put("formKey1", "formValue1");
        }}, Enums.Manipulation_Mode.SET);

        apiDriver.setFormParams(new HashMap<>() {{
            put("key2", "formValue2");
        }}, Enums.Manipulation_Mode.UPDATE);
        apiDriver.setFormParams(new HashMap<>() {{
            put("key1", "value1");
        }}, Enums.Manipulation_Mode.DELETE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(200);
    }

Scenario7: Test for POST request by updating the request body and validating the response

In YAML,
````
dev:
    httpbin_post:
        cookies:
        MyCookie: MyCookieValue
        headers:
        Authorization: Bearer ${DEV_BEARER_TOKEN}
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

    @Test
    public void test_update_requestbody() {
        apiDriver.setConfigFromYaml("httpbin_post");
        apiDriver.setQueryParams(new HashMap<>() {{
            put("param1", "paramValue1");
        }});
        apiDriver.setCookies(new HashMap<>() {{
            put("cookie1", "cookieValue1");
        }});
        apiDriver.setHeaders(new HashMap<>() {{
            put("header1", "headerValue1");
        }});
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "my_integer", "333", "Integer", Enums.Manipulation_Mode.UPDATE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "nest.c[0]", "12", "Integer", Enums.Manipulation_Mode.UPDATE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "nest.c[1]", "111", "Integer", Enums.Manipulation_Mode.UPDATE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "nest.c[2]", "", "", Enums.Manipulation_Mode.DELETE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "my_unset_variable", "", "", Enums.Manipulation_Mode.DELETE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "nest.pankaj", "Tester", "String", Enums.Manipulation_Mode.UPDATE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "my_string_array[2]", "Shakeer", "String", Enums.Manipulation_Mode.UPDATE);
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "my_boolean_array[2]", "", "", Enums.Manipulation_Mode.DELETE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.Response().validateStringFieldByPath("args.param1", Enums.Comparison_Type.EQUAL, "paramValue1");
        apiDriver.Response().validateStringFieldByPath("headers.Cookie", Enums.Comparison_Type.EQUAL, "cookie1=cookieValue1");
        apiDriver.Response().validateStringFieldByPath("headers.Header1", Enums.Comparison_Type.EQUAL, "headerValue1");
    }



### Input Data

In Test-Elevate framework, page. You can store all your GetRequest, PostRequest, BaseURI, Endpoint, QueryParams, PathParams, Headers in YAMLformat. 
File location - "api/src/test/resources/testProject/input.api"

In YAML,

````
dev:
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
In an api.properties file, ENVIRONMENT = dev - typically indicates that the application is configured to run in a development environment. 

File Location- "api/src/main/resources/api.properties"



 "tep.properties file" is used to manage and configure various aspects of the test environment.
   api=true - This line indicates that API testing is enabled. When the testing framework reads this property, it will 
   include API tests in the test execution.
   
   File Location- "api/src/main/resources/tep.properties"


### Viewing API Test Result
Allure Reporting Storage refers to the system or location where Allure test reports are saved and managed.It typically involves storing generated report files in a way that allows for easy access, sharing, and historical analysis.


## API project - Quick Read

## config

### ApiConfig

The ApiConfig class in the com.tep.api.config package is a singleton configuration class responsible for loading, parsing, and managing API settings from YAML and property files. It initializes itself statically and sets up JSONPath configuration using Jackson. The class provides methods to load API configurations from a specified folder, replace placeholders with environment variables, and query configuration values based on API name and path. It also includes a utility method to unload YAML data into both YAML and JSON files in a target directory. The class uses logging for debugging and error reporting.

### ApiConstants

The ApiConstants class in the com.tep.api.config package is a utility class that provides constant values for the API framework. It defines default settings such as the number of retries for API calls and the maximum backoff time in milliseconds. The class is designed to be non-instantiable with a private constructor that throws an UnsupportedOperationException if instantiation is attempted. This ensures that the class serves only as a repository for constant values used throughout the project.

### ApiEnums

The ApiEnums class in the com.tep.api.config package defines an enumeration Http_Method with values GET and POST to represent HTTP methods. The class includes a private constructor to prevent instantiation, throwing an UnsupportedOperationException if an attempt is made to create an instance. This class is intended to be a utility class for storing API-related enumerations.

### ApiKeys

The ApiKeys class in the com.tep.api.config package is a utility class that defines constants representing various configuration keys used in the API framework. These keys include base URI, base path, endpoint, request body, query parameters, headers, cookies, path parameters, form parameters, and settings for retry and proxy configurations. The class is final and includes a private constructor to prevent instantiation, ensuring it is used solely as a container for static constants to promote consistency and maintainability across the API framework.

## request

### ApiRequest

The ApiRequest interface in the com.tep.api.request package defines operations for handling API request content, particularly for structured formats like JSON or XML. It provides methods to convert objects to string representations, read values from content at specified paths, delete values from content, cast values to specific types, and update values within the content. This interface is intended to be implemented by classes that manage the manipulation and querying of API request data.

### ApiRequestFactory

The ApiRequestFactory class in the com.tep.api.request package is a factory class for creating instances of ApiRequest based on the content type, such as JSON or XML. It provides static methods to return singleton instances of JSON and XML handlers and to determine the appropriate handler for a given content. The class is final with a private constructor to prevent instantiation and includes an internal enum ContentType to represent possible content types. It also performs error handling for unknown content types.

### ApiRequestJsonImpl

The ApiRequestJsonImpl class implements the ApiRequest interface for handling JSON content in API requests. It provides methods to convert objects to JSON strings, read values from JSON using JSONPath expressions, delete values at specified JSONPaths, read and cast values to specific types, and update values within JSON content. The class uses an ObjectMapper for JSON processing and a JsonManipulator for JSON content manipulation, with error handling and logging provided by SLF4J.

### ApiRequestXmlImpl

The ApiRequestXmlImpl class implements the ApiRequest interface for handling XML content in API requests. It provides methods to convert objects to XML strings, read values from XML using XPath expressions, delete values at specified XPaths, read and cast values to specific types, and update values within XML content. The class uses an XmlMapper for XML processing and dom4j for XML manipulation, with error handling and logging provided by SLF4J.

## response

### ApiResponse

The ApiResponse class in the com.tep.api.response package is designed to handle API responses, allowing for the validation of fields and comparison of values within the response. It uses the RestAssured library to interact with and validate the response data. The class provides methods to validate string, boolean, and numeric fields by JSON path, validate the entire response body as text, and compare the response body against expected JSON files or strings. It includes error handling and logging capabilities, and supports custom comparison types through the Enums.Comparison_Type enumeration.

### ApiSchema

The ApiSchema class in the com.tep.api.response package is responsible for validating API response bodies against a specified JSON schema. It uses RestAssured's JSON schema validation module to perform the validation. The class provides a constructor to initialize with a RestAssured Response object and a method validateJsonSchema to validate the response against a JSON schema file located in the classpath. It includes error handling for assertion failures, invalid file paths, and JSON schema validation exceptions, with logging provided by SLF4J.

### ApiDriverHelpers

The ApiDriverHelpers class in the com.tep.api.support package is a helper class that extends the ApiDriver class. It is intended to provide additional functionality to the base API driver capabilities. Currently, the class does not implement any new methods or properties, but it serves as a placeholder for future enhancements or custom methods that may be added to augment the API request and response handling provided by ApiDriver.

## support

### ApiDriver

The ApiDriver class in the com.tep.api package is designed to configure and execute API requests, including setting request parameters like headers, cookies, and query parameters. It handles proxy settings and executes requests with optional retry capabilities. The class provides methods to set API configurations from YAML files, validate JSON response schemas, and build request specifications using RestAssured. It also includes functionality to reset request data, update request parameters, and convert the instance to a string representation for debugging. The class uses an ObjectMapper for JSON processing and supports various HTTP methods. Logging is provided by SLF4J, and additional utility methods are included for parameter manipulation and validation.

## resources

### api.properties

This properties file specifies configuration settings for an API framework. It defines the output format for API responses as YAML and sets the primary and fallback environments to 'dev' and 'default', respectively. Additionally, it provides environment-specific properties such as bearer tokens, API passwords, and proxy credentials for UAT (User Acceptance Testing) and DEV (Development) environments. The file contains sensitive information like passwords and tokens, indicating it should be securely managed.

### tep.properties

This properties file contains configuration settings related to different types of testing environments and project-specific information. It specifies that API testing is enabled (api=true), while desktop, mobile, and web testing are disabled. Additionally, it defines the project's name as 'testProject' and sets a placeholder for the project's base directory (project.dir=$[project.basedir]). The file serves as a centralized location for toggling testing types and defining project-level settings.

### logback.xml

This XML file is a configuration file for Logback, a Java-based logging framework. It defines an appender named "STDOUT" that outputs log messages to the console using a specified pattern that includes the timestamp, thread name, log level, logger name, and message. The root logger is set to the "info" level, which means only messages at this level and above will be logged. There is also an empty "debug" level root logger configuration, which may be intended for future use or customization.