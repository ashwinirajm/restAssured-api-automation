# REST Assured API Automation Framework

A scalable and production-style API automation framework built using REST Assured, TestNG, and Maven, with CI/CD support via Jenkins and reporting using Allure.

## 📐 Framework Design

The framework is structured in **phases**, each targeting a different public API and a specific automation concept.

### 🔹 Phase 1 – ReqRes > User management
   - User CRUD operations
   - Authentication and authorization

### 🔹 Phase 2 – JSONPlaceholder > Sample REST API
   - Posts API tests
   - Users API tests
   - Relationships and nested data tests

### 🔹 Phase 3 – Restful Booker > Booking management system
   - Authentication handling (token + basic auth)
   - Booking CRUD operations
   - Protected endpoint testing (PUT / PATCH)
   - JSON schema validation 

## 🏗️ Project Structure

```
restassured-api-automation/
├── src/
│   ├── main/java/
│   │   ├── base/                    # Base test classes
│   │   ├── config/                  # Configuration management
│   │   ├── constants/               # API endpoints
│   │   ├── pojo/                    # Request/Response POJOs
│   │   │   ├── reqres/
│   │   │   ├── posts/
│   │   │   └── booking/          
│   │   └── utils/                   # Utility classes
│   └── test/java/
│       └── tests/                   # Test classes
│           ├── booking/             # Restful Booker tests
│           ├── jsonPlaceholder/     # JSONPlaceholder tests
│           └── reqres/              # ReqRes tests
├── src/test/resources/
│   ├── config.properties            # Test configuration
│   ├── schemas/                     # JSON schemas for validation
│   └── *.xml                        # TestNG suite files
├── Jenkinsfile                      # Jenkins pipeline configuration
└── pom.xml                          # Maven configuration
```

## 🧰 Tech Stack

- Java 11 or higher
- RestAssured
- TestNG
- Maven 3.9+
- Allure Reporting
- Jenkins CI

### Run Specific Test Suite

```bash
# Authentication Tests
mvn test -DsuiteXmlFile=src/test/resources/booking-auth-api-suite.xml && mvn allure:serve

# Schema Validation Tests
mvn test -DsuiteXmlFile=src/test/resources/booking-auth-schema-suite.xml && mvn allure:serve

# Posts API Tests
mvn test -DsuiteXmlFile=src/test/resources/posts-api-suite.xml && mvn allure:serve

# ReqRes API Tests
mvn test -DsuiteXmlFile=src/test/resources/reqres-api-suite.xml && mvn allure:serve
```

## 🔄 Jenkins CI/CD Integration

### Features

✅ **Automated Pipeline** - Runs tests on every build
✅ **Parameterized Builds** - Select which test suite to run
✅ **Allure Reports** - Automatically published after each build
✅ **TestNG Reports** - HTML reports available in Jenkins
✅ **Build History** - Track test results over time

### Jenkins Pipeline Stages

1. **Checkout** - Pulls latest code from GitHub
2. **Clean** - Cleans previous build artifacts
3. **Compile** - Compiles the project
4. **Run Tests** - Executes selected test suite
5. **Publish Reports** - Publishes Allure and TestNG reports

### Sample Allure Report Features

- 📊 **Overview Dashboard** - Summary of test results
- 🔍 **Test Cases** - Detailed test execution information
- 📝 **Categories** - Test failures grouped by type
- 📈 **Graphs** - Visual representation of test trends
- 🕐 **Timeline** - Test execution timeline
- 📂 **Behaviors** - Tests organized by features and stories


