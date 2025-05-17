UI Testing Framework
This project is a UI testing framework built with Selenium WebDriver, JUnit 5, and Google Guice for dependency injection. It includes reusable components and pre-configured modules for efficient and scalable test automation.

Project Structure
extensions/: Contains test extensions, such as UIExtension, for handling setup and teardown logic.
factory/: Includes WebDriverFactory, responsible for managing WebDriver instances.
modules/: Contains Guice modules (GuicePagesModule and GuiceComponentsModule) to inject dependencies like page objects and components.
pages/: Contains page classes such as MainPage, CatalogPage.
components/: Includes reusable UI components like EducationComponent and HeaderEducationComponent.
Prerequisites
Java: JDK 21 or higher is required.
Maven: Ensure Maven is installed and configured.
Browser Drivers: Corresponding WebDriver executables (e.g., ChromeDriver) must be accessible in your system's PATH.
Dependencies: Managed via Maven (defined in pom.xml).
Getting Started
Clone the Repository
git clone https://github.com/gbkocharyan/otus.git
cd otus
Install Dependencies
Run the following Maven command to download and install dependencies:

mvn clean install
Configuration
Browser Configuration
The browser type is determined by the browser system property.
To set the browser (e.g., Chrome), pass the argument when running tests:
-Dbrowser=chrome
Alternatively, you can place the executable in the project directory and provide the path programmatically.

How to Run Tests
Using Maven
To execute all tests, run:

mvn clean test
To run tests with a specific browser:

mvn -Dbrowser=chrome clean test
Running a Specific Test
To run a particular test class:

mvn -Dtest=<TestClassName> test
Example
mvn -Dtest=SampleTest -Dbrowser=chrome test
Reporting
By default, Maven generates test reports in the following location:
target/surefire-reports/
Key Classes
1. UIExtension
   This class sets up the WebDriver and injects the required page objects and components before each test using Guice dependency injection. It also handles cleanup after tests.

2. WebDriverFactory
   Manages WebDriver instance creation based on the specified browser type. Currently, it supports:

Chrome
Throws BrowserNotSupportedException for unsupported browsers.
3. Guice Modules
   GuicePagesModule: Configures page objects for dependency injection.
   GuiceComponentsModule: Configures reusable UI components for dependency injection.
4. Page Classes and Components
   Page Classes: Represent specific pages of the application (MainPage, CoursesPage, etc.).
   UI Components: Reusable building blocks, such as headers or navigation fields (HeaderComponent, TrainingComponent, etc.).