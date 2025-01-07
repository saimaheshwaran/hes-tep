[![Tests](https://github.com/ey-advisory-technology-testing/ngtp-trunk/actions/workflows/tests.yml/badge.svg)](https://github.com/ey-advisory-technology-testing/ngtp-trunk/actions/workflows/tests.yml)

# Test Elevate Platform

Test Elevate Platform is a Junit based test automation framework to test Web.
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
			<li><a href = "#git">Viewing WEB Test result</a></li>
			</li>
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

# Configure the JDK in IntelliJ IDEA- 

1. Open IntelliJ IDEA and either create a new project or open an existing one.
2. Go to `File` > `Project Structure` or press `Ctrl` + `Alt` + `Shift` + `S` on your keyboard to open the Project Structure dialog.
3. In the Project Structure dialog, select the `SDKs` under the Platform Settings section.
4. Click the `+` button to add a new JDK, and a file chooser will appear.
5. Navigate to the JDK installation path you located earlier, select the JDK's root directory, and click `OK`.
6. IntelliJ IDEA will load the selected JDK and display its version in the list of SDKs.

![image](https://github.com/user-attachments/assets/50081139-7f10-40ca-b598-692d252bb5b2)

# Configure Maven in IntelliJ IDEA- 

1. Open IntelliJ IDEA and either create a new project with Maven or open an existing Maven project.
2. Go to `File` > `Settings` (on Windows/Linux) or `IntelliJ IDEA` > `Preferences` (on macOS).
3. In the Settings/Preferences dialog, navigate to `Build, Execution, Deployment` > `Build Tools` > `Maven`.
4. In the Maven settings, you'll see the 'Maven home directory' field. Click the browse button (the folder icon) next to it.
5. Navigate to the Maven installation path you located earlier and select the Maven directory. Click `OK`.
6. Optionally, you can configure other Maven settings such as user settings file, local repository, and importing options according to your project needs.
7. Click `Apply` and then `OK` to save the changes.

![image](https://github.com/user-attachments/assets/d3e21040-0536-485a-b729-1bda762c2514)
   
# Configure Utilities Dependency

1. Create a JAR file for the utility project.
2. Place the JAR file in the following directory: .m2/repository/com/tep/utilities/1.0.0.
3. Ensure the JAR file is named "utilities-1.0.0.jar". You can either rename it manually or set this name while creating the JAR file under "build artifact".
4. Ensure the POM file in that directory is named "utilities-1.0.0.pom". This needs to be done manually.
5. Build the project in intellij IDE.

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
        driver.seleniumWaits.sleep(2);
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
        driver.seleniumWaits.sleep(2);
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
        driver.seleniumWaits.sleep(2);
        driver.closeBrowser();

    }


### Input Data

# In YAML,

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

# In xlsx,

In Test-Elevate framework, page. You can store all your Xpath, Id, Name, link, Partial link in Excel format. 
you can explore the input data saved in the xlsx file at "src/test/resources/testProject/input/web/PageObjects.xlsx"

![image](https://github.com/user-attachments/assets/1c3b70e6-55d5-41fc-965b-01c8e3972789)

# In Page object class,

In Test-Elevate framework, page. You can store all your Xpath, Id, Name, link, Partial link in java class.
you can explore the input data saved in the xlsx file at "src/test/java/pages/eymerchandise"

![image](https://github.com/user-attachments/assets/0a3d78d1-6e75-435b-9da7-f7b733cd1c26)
![image](https://github.com/user-attachments/assets/65c28a1a-9520-4f7e-8deb-4932e9503b89)
![image](https://github.com/user-attachments/assets/ffd90610-c886-474c-9ec2-8217cbc1b47a)



### Running WEB Test
"web.properties file", is used in our frameworks to set up and manage environment-specific properties.

 Location of the file- "api/src/main/resources/api.properties"
![image](https://github.com/user-attachments/assets/b261bcb3-cd06-42a6-83f2-84661b302514)


 "tep.properties file" is used to manage and configure various aspects of the test environment.
   web=true - This line indicates that web testing is enabled. When the testing framework reads this property, it will 
   include web tests in the test execution.
   
   File Location- "api/src/main/resources/tep.properties"
   ![image](https://github.com/user-attachments/assets/c0b4d7c4-f229-44e9-9d87-5f54bcf4d480)


### Viewing WEB Test Result
Allure Reporting Storage refers to the system or location where Allure test reports are saved and managed.It typically involves storing generated report files in a way that allows for easy access, sharing, and historical analysis.

![image](https://github.com/user-attachments/assets/243a5bce-6085-4d76-a4ab-1d3f1f796abb)
![image](https://github.com/user-attachments/assets/489304df-d831-4eb0-bc56-708f54a2ce1a)


