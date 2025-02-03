# Test Elevate Platform

Test Elevate Platform is a test automation framework to test Web.
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
		<li><a href = "#intellij">How to build Web Artifact</a></li>	
    <li><a href = "#intellij">How to use Web Artifact in other project</a></li>	
			</li>
		</ul>
		</ol>
	</li>
	<li>
		<a href = "#usage">WEB</a>
		<ol>
			<li><a href = "#git">Creating a WEB Test</a></li>
			<li><a href = "#git">Input Data</a></li>
			<li><a href = "#git">Running WEB Test</a></li>
			</li>
		</ol>	
    <li><a href = "#git">Web Project - Quick Read</a>
        <ol>
            <li><a href = "#utilities">utilities</a>
            <ol>
            <li><a href = "#git">strings</a></li>
            </ol>
            </li>
             <li><a href = "#utilities">base</a>
            <ol>
            <li><a href = "#git">SeleniumDriver</a></li>
            <li><a href = "#git">SeleniumWaits</a></li>
            </ol>
            </li>
             <li><a href = "#utilities">browser</a>
            <ol>
            <li><a href = "#git">BrowserEvent</a></li>
            <li><a href = "#git">TabEvent</a></li>
            <li><a href = "#git">WindowEvent</a></li>
            <li><a href = "#git">WindowManipulation</a></li>
            <li><a href = "#git">WindowScrolling</a></li>
            </ol>
            </li>
             <li><a href = "#utilities">config</a>
            <ol>
            <li><a href = "#git">PageObjects</a></li>
            <li><a href = "#git">WebConstants</a></li>
            <li><a href = "#git">WebEnums</a></li>
            </ol>
            </li>
             <li><a href = "#utilities">element</a>
            <ol>
            <li><a href = "#git">checkbox</a></li>
            <li><a href = "#git">click</a></li>
            <li><a href = "#git">dropdown</a></li>
            <li><a href = "#git">getter</a></li>
            <li><a href = "#git">mousehover</a></li>
            <li><a href = "#git">radiobutton</a></li>
            <li><a href = "#git">sendkey</a></li>
            </ol>
            </li>
             <li><a href = "#utilities">validation</a>
            <ol>
            <li><a href = "#git">Assertion</a></li>
            <li><a href = "#git">AttributeValidation</a></li>
            <li><a href = "#git">CheckboxValidation</a></li>
            <li><a href = "#git">DropdownValidation</a></li>
            <li><a href = "#git">EnablementValidation</a></li>
            <li><a href = "#git">PageValidation</a></li>
            <li><a href = "#git">PresenceValidation</a></li>
            <li><a href = "#git">RadioButtonValidation</a></li>
            <li><a href = "#git">TextValidation</a></li>
            <li><a href = "#git">TypeValidation</a></li>
            </ol>
            </li>
            <li><a href = "#ApiConstants">Web</a>
            <ol>
            <li><a href = "#git">WebAppDriver</a></li>
            </ol>
            </li>
             <li><a href = "#ApiConstants">resources</a>
            <ol>
            <li><a href = "#git">logback.xml</a></li>
            <li><a href = "#git">tep.properties</a></li>
            <li><a href = "#git">Web.propertiesr</a></li>
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

# Configure the JDK in IntelliJ IDEA- 

1. Open IntelliJ IDEA and either create a new project or open an existing one.
2. Go to `File` > `Project Structure` or press `Ctrl` + `Alt` + `Shift` + `S` on your keyboard to open the Project Structure dialog.
3. In the Project Structure dialog, select the `SDKs` under the Platform Settings section.
4. Click the `+` button to add a new JDK, and a file chooser will appear.
5. Navigate to the JDK installation path you located earlier, select the JDK's root directory, and click `OK`.
6. IntelliJ IDEA will load the selected JDK and display its version in the list of SDKs.


# Configure Maven in IntelliJ IDEA- 

1. Open IntelliJ IDEA and either create a new project with Maven or open an existing Maven project.
2. Go to `File` > `Settings` (on Windows/Linux) or `IntelliJ IDEA` > `Preferences` (on macOS).
3. In the Settings/Preferences dialog, navigate to `Build, Execution, Deployment` > `Build Tools` > `Maven`.
4. In the Maven settings, you'll see the 'Maven home directory' field. Click the browse button (the folder icon) next to it.
5. Navigate to the Maven installation path you located earlier and select the Maven directory. Click `OK`.
6. Optionally, you can configure other Maven settings such as user settings file, local repository, and importing options according to your project needs.
7. Click `Apply` and then `OK` to save the changes.


### How to build Web Artifact

Steps to create Web jar in Nexus Repo:-

1. Pull the latest web code from your source repository.
      http://git.hestest.com:7990/projects/DEVOPS/repos/test-elevate-web/browse
2. In the project directory, run the Maven command to update your project dependencies:
   Run the command: mvn clean install
3. To build the project, navigate to Build >> Build project in Intellij
4. Navigate to Jenkins and open the Web framework pipeline:
   https://jenkins-devops.hestest.com/job/QA/job/test-elevate/
5. Click on Build now to run the pipeline
6. Once completed, you can check the latest web jar in the snapshots folder in Nexus repo
   http://co1-rep01sha01.test.healthe:8080/nexus/#browse/browse


### How to use Web Artifact in other project

Web utility is used by other Web projects. Please follow these steps to incorporate web dependency in others project.

1. Create a New Maven project >> Enter a name for your project in the "Project name" field.
2. Choose the location where your project will be stored by clicking on the "..." button and selecting a directory. >> Finish
3. Open your project >> open the "pom.xml" file located in the "Project" view on the left-hand side.
4. In your Web project's pom.xml, add the utilities project as dependency:

		<repositories>
        	<repository>
				<id>internal-nexus</id>
				<url>http://co1-rep01sha01.test.healthe:8080/nexus/content/groups/snapshots/</url>
			</repository>
       </repositories>  

		<dependencies><!-- utilities project dependency -->
			<dependency>
			   <groupId>com.tep</groupId>
			   <artifactId>utilities</artifactId>
			   <version>1.0.0</version>
			   </dependency>
		</dependencies>

5. In the project directory, run the Maven command to update your project dependencies:
   Run the command: mvn clean install
6. To build the project, navigate to Build >> Build project in Intellij
   
### Web

In the Test-Elevate framework, test cases are written in Java and are located in the `test` directory of your project. Each test case should be annotated with `@Test`.

To get started with writing tests using the JUnit framework, you can explore the test cases at /src/test/java/tests/eymerchandise . This test case file contains test methods that demonstrate how to verify UI components.

### Creating a Web test

We can create tests in java class under "src/test/java/tests/eymerchandise" location and every test should have "@Test" annotation.
Following are the different scenarios where you can validate the data for the respective tests.

Scenario1: To verify given webpage and validate title through page object class.

    @Test
    public void testverifypage() {

        WebAppDriver driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        driver.pageValidation.checkPageTitle("Search", true);
        driver.closeBrowser();
    }


Scenario2: Search for a Specific Product and add to cart through YAML and page object class.

    @Test
    public void testProduct() {

        WebAppDriver driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        HomePage homePage = new HomePage(driver.getBrowser());
        SearchPage searchPage = new SearchPage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());
        driver.seleniumSendKeys.sendKeys(homePage.getSearchBox(), "Bag");
        driver.actionSendKeys.enterKeys(homePage.getSearchBox(), "ENTER");
        driver.pageValidation.checkPartialPageTitle("Search", true);
        driver.seleniumClick.click(searchPage.getProductBag());
        driver.pageValidation.checkPartialPageTitle("Bag", true);
        driver.seleniumSendKeys.clearInputs(productPage.getQuantity());
        driver.seleniumSendKeys.sendKeys(productPage.getQuantity(), "2");
        driver.seleniumClick.click(productPage.getAddToCart());
        driver.textValidation.isPartiallyMatching(productPage.getAlert(), "Added to cart", true);
        driver.closeBrowser();
    }

Scenario3: To click on Submenus and validate partial title through YAML and page object class.

    @Test
    public void testsubmenu() {

        WebAppDriver driver = new WebAppDriver();
        driver.openBrowser(WebEnums.BrowserType.CHROME);
        driver.browserEvent.goToUrlByPOValue("EY.page");
        HomePage homePage = new HomePage(driver.getBrowser());
        SearchPage searchPage = new SearchPage(driver.getBrowser());
        ProductPage productPage = new ProductPage(driver.getBrowser());
        driver.seleniumClick.click(productPage.getApparel());
        driver.seleniumClick.click(productPage.getHeadwear());
        driver.pageValidation.checkPartialPageTitle("Headwear", true);
        driver.closeBrowser();

    }


## Input Data

### In YAML,

In Test-Elevate framework, page. You can store all your Xpath, Id, Name, link, Partial link in YAMLformat. 
you can explore the input data saved in the yaml file at "src/test/resources/testProject/input/web/PageObjects.yaml"

````
EY:
  page: {value: 'https://ey.corpmerchandise.com'}
  searchBox: {id: 'searchTextBox'}
  pen: {xpath: '//ul//a[@id="linkProductName"]//h4[contains(text(),"Pen")]'}
  bag: {xpath: '//ul//a[@id="linkProductName"]//h4[contains(text(),"Bag")]'}
  quantity:
    id: 'Quantity'
  addToCart:
    xpath: '//button[contains(@id, "button-addtocart")]'
  alert:
    xpath: '//div[@id="messageBoxContainerId"]'
````

### In xlsx,

In Test-Elevate framework, page. You can store all your Xpath, Id, Name, link, Partial link in Excel format. 
you can explore the input data saved in the xlsx file at "src/test/resources/testProject/input/web/PageObjects.xlsx"


### In Page object class,

In Test-Elevate framework, page. You can store all your Xpath, Id, Name, link, Partial link in java class.
you can explore the input data saved in the xlsx file at "src/test/java/pages/eymerchandise"


### In Json,

In Test-Elevate framework, page. You can store all your Xpath, Id, Name, link, Partial link in Json format.
you can explore the input data saved in the json file at "src/test/resources/testProject/input/web/PageObjects.json"


## Running WEB Test
"web.properties file", is used in our frameworks to set up and manage environment-specific properties.

The configuration entry page.object.type = yaml in the properties file of our framework determines the type of file from which page object information is sourced. Should the page.object.type be configured as xls, the system will extract page object identifiers from an Excel spreadsheet. If it is configured as json, the system will instead retrieve the identifiers from a JSON structure. These identifiers include various selectors like ID, XPath, name, or link text, which are essential for web element identification and manipulation within the framework.
 
you can explore the .properties file at this location  "src/main/resources/web.properties"


"tep.properties file" is used to manage and configure various aspects of the test environment.
web=true - This line indicates that web testing is enabled. When the testing framework reads this property, it will 
include web tests in the test execution.

you can explore the .properties file at this location "src/main/resources/tep.properties"
   
## Web project - Quick Read

## utilities

### Strings
The Strings class provides utility methods for string manipulation and interaction with web elements in Selenium. Methods include parseLocatorfromWebElementString to extract locators from web element strings, identifyKey to map string representations to Selenium Keys enums, removeUTFCharacters to strip UTF characters from strings, stringFetch to retrieve substrings based on regex patterns, subStringSearchInList to find substrings within a list, encode and decode for Base64 encoding and decoding, and compareStrings to assert the equality of two strings.

## base

### SeleniumDriver
The SeleniumDriver class in the com.tep.web.base package is a utility class for managing WebDriver instances across multiple browsers such as Chrome, Firefox, Edge, and Safari. It provides methods for initializing browsers with specific options, opening and closing browsers, retrieving WebElements using various locator strategies, and extracting locator strings from WebElements. The class also handles browser configuration based on predefined constants and manages implicit wait times for element searches.

### SeleniumWaits
The SeleniumWaits class provides custom waiting mechanisms for WebDriver elements, utilizing WebDriverWait and FluentWait to implement various wait conditions. It includes methods to wait for JavaScript and jQuery to load, for page titles to contain specific text, and for WebElements to be displayed, clickable, or not displayed/clickable. Additionally, it offers a sleep function for execution pauses and a private method to scroll to and highlight WebElements on the page.

## browser

### BrowserEvent
The BrowserEvent class provides methods for browser navigation actions, including going to a specified URL, navigating back and forward in the browser history, and refreshing the current page. It utilizes a SeleniumDriver instance to interact with the browser and perform these navigation actions. Methods are provided to navigate directly to a URL or to a URL specified by a page object value.

### TabEvent
The TabEvent class in the com.tep.web.browser package provides functionality to manage browser tabs using a SeleniumDriver instance. It includes methods to create a new tab and navigate to a specified URL, switch to a tab based on its title, and switch to the first tab in the browser. The class utilizes JavaScript execution and Selenium WebDriver's window handle management to perform these actions.

### WindowEvent
The WindowEvent class handles browser window and frame interactions, such as switching between windows or tabs, handling browser alerts, and working with iframes. It provides methods to switch to new or previously opened windows or tabs, close windows, handle alert boxes, switch to frames identified by WebElements or page object names, switch back to the main content from frames, and refresh the current page. The class uses a SeleniumDriver instance for browser interactions and a SeleniumWaits instance to ensure elements are visible before interaction.

### WindowManipulation
The WindowManipulation class provides methods to manipulate the browser window, including zooming in and out, resizing, and maximizing the window. It also contains methods to interact with specific elements to adjust the zoom level until they become visible. The class uses a SeleniumDriver instance for browser interactions and a SeleniumWaits instance to ensure elements are visible before interaction. It utilizes key combinations and JavaScript execution for zooming and resizing actions.

### WindowScrolling
The WindowScrolling class provides methods to control the scrolling behavior on a web page. It allows for scrolling to the top or bottom of the page, to specific elements, or by a specified number of pixels both vertically and horizontally. The class uses a SeleniumDriver instance for browser interactions, a SeleniumWaits instance to wait for elements, and a JavascriptExecutor to perform the actual scrolling actions through JavaScript commands.

## config

### PageObjects
The PageObjects class is responsible for managing Page Object data for web automation, supporting YAML, JSON, and Excel file formats. It provides functionality to load, retrieve, modify, and save locators and their values associated with web elements defined in the Page Object model. The class uses a nested LinkedHashMap structure to store the page object data and includes methods to get and set locator values, as well as to persist changes back to the file system.

### WebConstants
The WebConstants class in the com.tep.web.config package defines and loads a set of static constants used throughout the web automation framework. It reads configurations from a web.properties file, including settings for the page object model type, browser preferences, reporting options, and wait times for Selenium actions. The class also provides a Properties object that holds all the loaded properties and includes utility methods for date and time formatting.

### WebEnums
The WebEnums class in the com.tep.web.config package defines enumerations for various constants used in the web automation framework. The primary enumeration included is BrowserType, which lists supported browsers such as Chrome, Chrome Canary, Firefox, Safari, Edge, and a default option for unspecified browser types. This enumeration is used to specify and manage browser preferences within the framework.

## elements

### ActionCheckBox
The ActionCheckBox class provides methods to interact with checkbox elements on a webpage using Selenium WebDriver. It includes functionality to check, uncheck, and toggle checkboxes, ensuring that the desired state is achieved regardless of the initial state of the checkbox. The class uses instances of SeleniumWaits for explicit waits, SeleniumDriver for browser interactions, and ActionClick to simulate click actions. It also handles potential StaleElementReferenceException by retrying the action if necessary

### JavaScriptCheckBox
The JavaScriptCheckBox class provides methods to interact with checkbox elements on a webpage using JavaScript execution. It includes methods to check, uncheck, and toggle checkboxes directly through JavaScript commands, bypassing the standard user interface interactions. The class uses an instance of SeleniumWaits for explicit waits and SeleniumDriver for browser interactions. It also handles StaleElementReferenceException by retrying the action if the element reference becomes stale.

### SeleniumCheckBox
The SeleniumCheckBox class provides methods for interacting with checkbox elements on a webpage using Selenium WebDriver. It includes functionality to check, uncheck, and toggle checkboxes by performing click actions on them. The class utilizes SeleniumWaits to wait for elements to be displayed, SeleniumClick to perform the click actions, and SeleniumDriver to interact with the browser and locate elements. It also handles StaleElementReferenceException to ensure reliable element interaction.

### ActionClick
The ActionClick class provides methods to perform click and double-click actions on web elements using the Actions class in Selenium WebDriver. It includes functionality to wait for elements to be displayed before clicking and handles StaleElementReferenceException by retrying the action if necessary. The class uses instances of SeleniumWaits for explicit waits and SeleniumDriver for browser interactions.

### JavaScriptClick
The JavaScriptClick class provides methods to perform click and double-click actions on web elements using JavaScript execution within Selenium WebDriver. This approach is particularly useful when elements are not interactable using standard Selenium methods. The class ensures elements are displayed and enabled before executing JavaScript click actions and handles StaleElementReferenceException by retrying if necessary. It uses SeleniumWaits for explicit waits and SeleniumDriver for browser interactions

### SeleniumClick
The SeleniumClick class provides methods for performing click and double-click actions on web elements using standard Selenium WebDriver interactions. It includes functionality to wait for elements to be displayed before clicking and handles StaleElementReferenceException by retrying the action if the element reference becomes stale during interaction. The class uses SeleniumWaits for explicit waits and SeleniumDriver for browser interactions.

### ActionDropdown
The ActionDropdown class provides methods for interacting with dropdown elements on a webpage using Selenium WebDriver. It includes functionality to select and deselect options based on index, value, or visible text, and can handle multi-select dropdowns. The class uses SeleniumWaits to wait for elements to be displayed before interaction and SeleniumDriver to locate elements. It also handles StaleElementReferenceException to ensure reliable interaction with dropdown elements.

### JavaScriptDropdown
The JavaScriptDropdown class provides methods for interacting with dropdown elements on a webpage using JavaScript execution within Selenium WebDriver. It includes functionality to select and deselect options based on index, value, or visible text, and can handle multi-select dropdowns by selecting or deselecting all options. The class uses SeleniumWaits to wait for elements to be displayed before interaction and SeleniumDriver to locate elements. It also handles StaleElementReferenceException to ensure reliable interaction with dropdown elements.

### SeleniumDropdown
The SeleniumDropdown class provides methods for interacting with standard HTML select dropdowns using Selenium WebDriver. It includes methods to select and deselect options by index, value, or visible text, and it supports multi-select dropdowns. The class uses SeleniumWaits to wait for elements to be displayed before interaction, SeleniumClick to perform click actions, and SeleniumDriver to locate elements. It also handles StaleElementReferenceException to ensure reliable interaction with dropdown elements.

### GetAttribute
The GetAttribute class provides methods to retrieve the text content or a specific attribute value of a given WebElement using Selenium WebDriver. It facilitates interactions with web elements to extract information such as visible text or other HTML attributes. The class uses an instance of SeleniumDriver to locate elements on the web page. Methods are provided to get text or attribute values either by passing the object name of the element or directly using the WebElement.

### GetElement
The GetElement class provides utility methods to check the state of a WebElement, such as whether it is selected, enabled, or displayed. It is designed to handle scenarios involving checkboxes, radio buttons, and other interactable elements. The class includes error handling for StaleElementReferenceException by retrying the check, and for NoSuchElementException by returning false, indicating the element is not present on the page.

### ActionMouseHover
The ActionMouseHover class provides a method to simulate a mouse hover action over a specified element on a webpage using Selenium WebDriver. It utilizes the Actions class to move the mouse pointer to the target WebElement after ensuring the element is visible using SeleniumWaits. The class also handles StaleElementReferenceException by retrying the hover action if necessary. It uses an instance of SeleniumDriver for browser interactions.

### JavaScriptMouseHover
The JavaScriptMouseHover class provides a method to simulate a mouse hover over a web element using JavaScript execution within Selenium WebDriver. It triggers the 'mouseover' event on the specified WebElement by executing a custom JavaScript snippet. The class uses SeleniumWaits to ensure the element is displayed before performing the hover action and includes error handling for StaleElementReferenceException by retrying the action if necessary. An instance of SeleniumDriver is used for browser interactions.

### ActionRadioButton
The ActionRadioButton class provides a method to select a radio button element on a webpage using Selenium WebDriver. It uses the Actions class to move the mouse to the target radio button and performs a click action on it with the help of the ActionClick class if the radio button is not already selected. The class uses SeleniumWaits to ensure the element is visible before interaction and handles StaleElementReferenceException by retrying the action if necessary. An instance of SeleniumDriver is used for browser interactions.

### JavaScriptRadioButton
The JavaScriptRadioButton class provides a method to select a radio button element on a webpage using JavaScript execution within Selenium WebDriver. It executes a JavaScript script to check the radio button if it is not already selected. The class uses SeleniumWaits to wait for the element to be displayed before executing the script and handles StaleElementReferenceException by retrying the action if the element reference becomes stale. An instance of SeleniumDriver is used for browser interactions.

### SeleniumRadioButton
The SeleniumRadioButton class provides a method to select a radio button element on a webpage using Selenium WebDriver. It uses the SeleniumClick class to perform a click action on the radio button if it is not already selected. The class employs SeleniumWaits to ensure the element is displayed before attempting to interact with it and handles StaleElementReferenceException by retrying the selection if the element reference becomes stale. An instance of SeleniumDriver is used for browser interactions.

### ActionSendKeys
The ActionSendKeys class provides methods for sending keyboard inputs to web elements using Selenium WebDriver. It allows for sending plain text, pressing specific keys like Enter or Backspace, and handling key combinations such as Shift+Enter. The class uses SeleniumWaits to ensure elements are displayed before sending keys and handles StaleElementReferenceException by retrying the action if necessary. An instance of SeleniumDriver is used for browser interactions, and the Actions class is utilized for more complex key sequences.

### JavaScriptSendKeys
The JavaScriptSendKeys class allows for sending text to a web element using JavaScript execution within Selenium WebDriver. It sets the "value" attribute of an input field to simulate typing text. The class uses SeleniumWaits to ensure the element is displayed and enabled before executing the script and handles StaleElementReferenceException by retrying the action if the element reference becomes stale. An instance of SeleniumDriver is used for browser interactions.

### SeleniumSendKeys
The SeleniumSendKeys class provides methods to send text to web elements and clear input fields using Selenium WebDriver. It interacts with web elements to simulate typing and clearing of inputs. The class uses SeleniumWaits to wait for elements to be displayed before sending text or clearing the fields and handles StaleElementReferenceException by retrying the action if the element reference becomes stale. An instance of SeleniumDriver is used for browser interactions.

## validation

### Assertion
The Assertion class provides methods for performing both hard and soft assertions within a testing framework. Hard assertions stop the test execution immediately upon failure, while soft assertions allow the test to continue and report all failures at the end. The class uses SeleniumDriver for browser interactions and SoftAssert for soft assertions. It also allows switching between assertion types by setting a system property, and includes methods for asserting conditions, equality, and inequality of values or objects.

### AttributeValidation
The AttributeValidation class provides methods to verify the value of a web element's attribute against an expected value using Selenium WebDriver. It allows for assertions on whether the actual attribute value should match or not match the expected value. The class uses SeleniumWaits to wait for elements to be displayed before performing the verification and SeleniumDriver to interact with the browser. It also uses the Assertion class to assert the outcome of the validation.

### CheckBoxValidation
The CheckBoxValidation class provides methods to verify the state of a checkbox element on a webpage using Selenium WebDriver. It checks whether a checkbox is selected (checked) or not (unchecked) and asserts if the actual state matches the expected state. The class uses SeleniumWaits to wait for the checkbox to be displayed before performing the verification and SeleniumDriver to interact with the browser. It uses the Assertion class to assert the outcome of the validation.

### DropdownValidation
The DropdownValidation class provides methods to verify the selected option in a dropdown element using Selenium WebDriver. It checks whether a specific option is selected based on the option's text or value and asserts if the actual selection matches the expected selection. The class uses SeleniumWaits to wait for the dropdown to be displayed before performing the verification and SeleniumDriver to interact with the browser. It uses the Assertion class to assert the outcome of the validation.

### EnablementValidation
The EnablementValidation class provides methods to verify whether a web element is enabled or disabled using Selenium WebDriver. It asserts that the element's enabled state matches the expected state, either enabled (true) or disabled (false). The class uses SeleniumWaits to wait for the element to be displayed before performing the verification and SeleniumDriver to interact with the browser. It uses the Assertion class to assert the outcome of the validation.

### PageValidation
The PageValidation class provides methods for validating page titles and ensuring a page is fully loaded using Selenium WebDriver. It includes methods to retrieve the current page title after verifying that JavaScript and jQuery have finished loading, check if the page title matches or contains a specified title, and wait for the page to reach a 'complete' ready state. The class uses SeleniumWaits to wait for JavaScript and jQuery to load and SeleniumDriver to interact with the browser. It also uses the Assertion class to assert the outcomes of the validations.

### PresenceValidation
The PresenceValidation class provides methods for validating the presence of web elements on a page using Selenium WebDriver. It verifies if an element is present or absent based on a specified condition and can perform this check within a given wait time. The class uses SeleniumWaits to apply the wait conditions and SeleniumDriver to interact with the browser. It employs the Assertion class to assert whether the element's presence matches the expected condition.

### RadioButtonValidation
The RadioButtonValidation class provides methods to validate the selection state of radio buttons on a web page. It checks whether a radio button is selected or not and asserts whether this state matches the expected condition (selected or not selected). The class uses SeleniumWaits to wait for the radio button to be displayed before performing the check and SeleniumDriver to interact with the browser. It employs the Assertion class to assert the outcome of the validation.

### TextValidation
The TextValidation class provides methods to validate the text content of web elements. It checks whether the text in an element exactly matches or contains the expected text, and asserts based on the specified condition (match or not match). The class uses SeleniumWaits to wait for the element to be displayed before performing the text validation and SeleniumDriver to interact with the browser. It employs the Assertion class to assert the outcome of the text comparison.

### TypeValidation
The TypeValidation class is a singleton that provides methods to validate the types of locators and options used in web automation. It ensures that the specified locator types (such as "id", "class", "xpath") and option types (such as "text", "value", "index") are valid before any actions are performed using them. The class includes functionality to check the validity of these types and throws exceptions if an invalid type is provided. The singleton pattern ensures that only one instance of the class is used throughout the application.

## web

### WebAppDriver
The WebAppDriver class encapsulates the initialization and management of the web browser and various web automation actions for a web application. It provides a comprehensive set of methods to interact with web elements, perform browser operations, and validate the state of the page. The class initializes instances of action classes for clicks, send keys, mouse hovers, and checkbox and dropdown interactions, both with standard Selenium methods and JavaScript execution. It also includes validation classes for checking page titles, element presence, text, attributes, and more. The class uses SeleniumDriver for browser interactions and SeleniumWaits for managing wait conditions.

## resourses

### Logback.xml
This XML configuration snippet is for Logback, a Java logging framework. It defines a ConsoleAppender named "STDOUT" that outputs log messages to the console. The log message format includes the timestamp, thread name, log level, logger name, and the actual message, with specific parts highlighted in colors like bold cyan for the thread and bold green for the logger. Two root loggers are configured with different levels: "info" and "debug", but only the "info" level logger is associated with the "STDOUT" appender. The "debug" level logger is defined but not associated with any appender.

### Web.properties

This properties file specifies configuration settings for an web framework.The configuration entry page.object.type = yaml in the properties file of our framework determines the type of file from which page object information is sourced. Should the page.object.type be configured as xls, the system will extract page object identifiers from an Excel spreadsheet. If it is configured as json, the system will instead retrieve the identifiers from a JSON structure. These identifiers include various selectors like ID, XPath, name, or link text, which are essential for web element identification and manipulation within the framework.

### tep.properties

This properties file contains configuration settings related to different types of testing environments and project-specific information. It specifies that API testing is enabled (api=true), while desktop, mobile, and web testing are disabled. Additionally, it defines the project's name as 'testProject' and sets a placeholder for the project's base directory (project.dir=$[project.basedir]). The file serves as a centralized location for toggling testing types and defining project-level settings.



