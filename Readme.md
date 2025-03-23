# MyCredo Login Automation

This project contains automated tests for the MyCredo login functionality. It verifies login behavior across different languages and validates form validation for empty username/password fields.

## Project Structure

```
credo-task/
├── src/
│   └── main/
│       └── java/
│           └── ge/
│               └── mycredo/
│                   ├── data/              # Test data and constants
│                   │   ├── Config.java    # Browser and timeout settings
│                   │   ├── Constants.java # Language-specific text resources
│                   │   ├── CredoDataProvider.java  # TestNG data providers
│                   │   └── Routes.java    # Application URLs
│                   ├── pages/             # Page Objects
│                   │   ├── BasePage.java  # Common page functionality
│                   │   ├── LoginPage.java # Login page elements and actions
│                   │   └── components/    # Reusable page components
│                   │       └── LanguageChangePopup.java
│                   ├── steps/             # Step definitions with Allure annotations
│                   │   ├── LoginPageSteps.java
│                   │   └── componentsteps/
│                   │       └── LanguageChangePopupSteps.java
│                   └── utils/
│                       └── config/        # Test configuration
│                           ├── BaseTest.java  # Common test setup
│                           ├── RetryAnalyzer.java  # Test retry mechanism
│                           └── testlisteners/
│                               └── ScreenshotListener.java  # Captures screenshots on failure
└── test/
    └── java/
        └── MyCredoTest.java       # Test class with test methods
    └── resources/
        └── allure/                            # Allure configuration
            ├── allure.properties
            ├── categories.json
            └── environment.properties
├── pom.xml                                # Maven configuration
└── testng.xml                             # TestNG suite configuration
```

## Technology Stack

- Java 21
- Selenium WebDriver 4.29.0
- TestNG 7.11.0
- Allure Reporting 2.29.1
- WebDriverManager 6.0.0
- Maven

## Prerequisites

- Java JDK 21
- Maven
- Chrome and Edge browsers installed

## Setup and Execution

1. Clone the repository:
   ```
   git clone [https://github.com/Skhila/credo-task.git]
   cd credo-task
   ```

2. Build the project:
   ```
   mvn clean install -DskipTests
   ```

3. Run tests:
   ```
   mvn clean test
   ```

4. Generate Allure report:
   ```
   mvn allure:report
   ```

5. Open Allure report:
   ```
   mvn allure:serve
   ```

## Test Configuration

- Default browser: Chrome (configurable in `Config.java` or via `testng.xml`)
- Default timeout: 10 seconds (configurable in `Config.java`)
- Tests are executed in both Chrome and Edge browsers as defined in `testng.xml`

## Test Cases

The project includes the following test cases:

1. **Login with Invalid Credentials Across Different Languages**
    - Priority: 1
    - Severity: Critical
    - Description: Verifies system behavior with invalid login credentials in different languages (Georgian, Svan, English)
    - Data provider: `negativeLoginDataProvider`

2. **Login with Empty Username**
    - Priority: 2
    - Severity: Critical
    - Description: Verifies that login button is disabled when username is empty

3. **Login with Empty Password**
    - Priority: 3
    - Severity: Critical
    - Description: Verifies that login button is disabled when password is empty

## Features

- **Page Object Model**: Structured approach to separating test logic from page interactions
- **Fluent API**: Step methods return the step class instance for method chaining
- **Screenshot Capture**: Automatic screenshot capture on test failure using TestNG listeners
- **Retry Mechanism**: Failed tests automatically retry up to 2 times (configurable in `RetryAnalyzer.java`)
- **Multi-language Support**: Tests verify behavior in Georgian, Svan, and English languages
- **SoftAsserts**: Non-blocking assertions to capture multiple failures in a single test run
- **Allure Reporting**: Detailed test reports with screenshots, steps, and test metadata

## Allure Report Categories

The project defines the following Allure report categories in `categories.json`:

- Successful Tests
- Ignored Tests
- Outdated Tests
- Product defects
- Test defects
- Unknown Tests

## Notes

- Explicit waits are used throughout the project for reliable element detection
- Random alphanumeric strings are used for testing invalid login credentials
- Tests will be retried up to 2 times upon failure to handle flaky tests
- Multi-browser testing is implemented via TestNG parameter configuration