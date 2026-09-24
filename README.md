# EnduroSat Test Automation Framework

Test automation framework for UI and API testing, built with Java, Selenium, Cucumber, TestNG, REST Assured, PicoContainer, Log4j2 and Allure.

## Prerequisites

* Java 17+
* Maven
* Git
* Google Chrome or another Chromium-based browser

Verify Java and Maven from the command line:

```bash
java -version
mvn -version
```

## Running Tests from the Command Line

Open **Command Prompt / Terminal**, navigate to the project root directory, and run the desired Maven command.

### Run UI tests

```bash
mvn clean test -Dcucumber.filter.tags="@ui"
```

Runs all Cucumber scenarios tagged with `@ui`.

### Run API tests

```bash
mvn clean test -Dcucumber.filter.tags="@api"
```

Runs all Cucumber scenarios tagged with `@api`.

### Run all tests

```bash
mvn clean test
```

Runs all available Cucumber scenarios.

### Run tests by multiple tags

For example, to run API smoke tests:

```bash
mvn clean test -Dcucumber.filter.tags="@api and @smoke"
```

## Configuration

Test configuration is stored in:

```text
src/test/resources/config/config.properties
```

It contains values such as:

* UI and API base URLs
* browser
* headless mode
* timeout
* test credentials

`ConfigManager` provides centralized access to these values, keeping configuration separate from the test implementation.

The current implementation uses a single configuration file. To change the configuration, update `config.properties` before running the tests.

## Reports

Allure test results are generated in:

```text
target/allure-results
```

To open the report from the command line:

```bash
allure serve target/allure-results
```

A static report can also be generated with:

```bash
allure generate target/allure-results -o target/allure-report
```

## Framework Structure

```text
src
├── main/java/framework
│   ├── api          # API clients and API-specific operations
│   ├── config       # Configuration management
│   ├── core         # Shared framework components
│   ├── models       # Request/response models
│   ├── pages        # UI Page Objects
│   └── utils        # Reusable utilities
│
└── test
    ├── java
    │   ├── context        # Scenario-scoped test data
    │   ├── hooks          # Test lifecycle
    │   ├── runners        # Cucumber/TestNG runner
    │   └── stepdefinitions
    │       ├── api
    │       └── ui
    │
    └── resources
        ├── config         # Configuration
        ├── features       # Cucumber scenarios
        └── log4j2.xml     # Logging configuration
```

### Key Design Decisions

**Page Object Model**
UI interactions and locators are separated from Cucumber step definitions, making the tests easier to maintain and read.

**Reusable API Client**
`ApiClient` provides common HTTP operations, while classes such as `BookingApi` contain API-specific functionality. This keeps REST Assured details out of the step definitions and promotes reuse.

**Scenario-scoped TestContext**
PicoContainer is used to share data such as booking IDs, responses, and authentication tokens between steps within the same scenario without relying on static or global state.

**Externalized Configuration**
URLs, credentials, browser settings, and timeouts are stored separately from the test code, making the framework easier to configure and maintain.
