# REST Assured API Automation Framework

![Java](https://img.shields.io/badge/Java-11%2B-blue)
![REST Assured](https://img.shields.io/badge/REST--Assured-API%20Testing-green)
![TestNG](https://img.shields.io/badge/TestNG-Testing-orange)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![Allure](https://img.shields.io/badge/Allure-Reports-yellow)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-blueviolet)


A scalable, production-style API automation framework built using **REST Assured, TestNG, and Maven**, covering multiple public APIs and demonstrating real-world automation practices such as authentication handling, schema validation, and CI/CD execution using Jenkins with Allure reporting.

## 📐 Framework Design

The framework is structured in **phases**, each targeting a different public API and a specific automation concept.

### 🔹 Phase 1 – ReqRes (User management)
   - User CRUD operations
   - Authentication and authorization

### 🔹 Phase 2 – JSONPlaceholder (Sample REST API)
   - Posts API tests
   - Users API tests
   - Relationships and nested data tests

### 🔹 Phase 3 – Restful Booker (Booking management system)
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

## 🚀 Run Specific Test Suite

```bash
# Authentication Tests
mvn test -DsuiteXmlFile=src/test/resources/booking-auth-api-suite.xml
mvn allure:serve

# Schema Validation Tests
mvn test -DsuiteXmlFile=src/test/resources/booking-auth-schema-suite.xml
mvn allure:serve

# Posts API Tests
mvn test -DsuiteXmlFile=src/test/resources/posts-api-suite.xml
mvn allure:serve

# ReqRes API Tests
mvn test -DsuiteXmlFile=src/test/resources/reqres-api-suite.xml
mvn allure:serve
```

## 🔄 CI Support (Jenkins)

The framework is integrated with Jenkins to support continuous integration and automated execution of API tests.
- Jenkins pipeline is defined using a Jenkinsfile
- Builds execute API test suites using Maven + TestNG
- Supports running specific TestNG suites via build parameters
- Allure reports are generated and published after test execution
- Enables tracking of test results across builds for quick feedback

## 📊 Allure Report 

(Sample execution report)

<img width="1400" height="600" alt="Screenshot 2026-02-04 at 1 09 53 AM" src="https://github.com/user-attachments/assets/e9c7a500-004b-42ad-ae35-35e8b88429b8" />




