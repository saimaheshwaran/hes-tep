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
		</ul>
		</ol>
	</li>
	<li><a href = "#usage">Core Project</a>
		<ol>
		<li><a href = "#git">How to build Core Artifact</a></li>
		</ol>	
	</li>
</ul>

	


## Introduction

Test Elevate is an essential unit testing framework for Java, streamlining the development process with simple annotations, assertions, and test runners. It facilitates test-driven development, enabling consistent and efficient testing practices that integrate seamlessly with build tools and CI/CD pipelines. Adopting a JUnit-based automation framework enhances software quality by enabling thorough regression testing and rapid identification of bugs.
Core project contains all the POM dependencies which can be leveraged in utilities and Database projects.

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
Windows- 
Download IntelliJ Community Version
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

    
### Core

In the Test-Elevate framework, "Core" project is a shared library or common module, Core project is a centralized codebase that houses reusable components and dependencies. 

It serves as a foundational layer that can be integrated into multiple other projects to ensure consistency, reduce redundancy, and streamline development processes


### How to build Core Artifact

Steps to create core jar in Nexus Repo:-

Please follow the below steps incase you need to update any core framework components.

1. Pull the latest code from your source repository.
     http://git.hestest.com:7990/projects/DEVOPS/repos/test-elevate-core/browse
2. In the project directory, run the Maven command to update your project dependencies:
   Run the command: mvn clean install
3. To build the project, navigate to Build >> Build project in Intellij 
4. After making the necessary updates to the core framework, update the version number in POM
5. Checkin the latest code in the git repo mentioned in step 1
6. Navigate to Jenkins and open the core framework pipeline:
	https://jenkins-devops.hestest.com/job/QA/job/test-elevate/job/Test-Run-Core/
7. Click on Build now to run the pipeline
8. Once completed, you can check the latest core jar in the snapshots folder in Nexus repo
	http://co1-rep01sha01.test.healthe:8080/nexus/#browse/browse
9. Core artifact is being used in utilities and database.	

	 
      


