# REST Assured API Automation Framework

A comprehensive API automation testing framework built with REST Assured, TestNG, and Maven, featuring Jenkins CI/CD integration and Allure reporting.

## 🚀 Features

- **REST Assured** - Powerful API testing library
- **TestNG** - Test execution and management
- **Maven** - Build automation and dependency management
- **Allure Reports** - Beautiful, interactive test reports
- **Jenkins Pipeline** - Automated CI/CD integration
- **JSON Schema Validation** - Validates API response structures
- **Multi-API Testing** - Tests for multiple API endpoints (Restful Booker, JSONPlaceholder, ReqRes)

## 📊 Test Coverage

### APIs Under Test

1. **Restful Booker API** - Booking management system
   - Authentication tests (6 tests)
   - Schema validation tests (4 tests)
   - Booking CRUD operations

2. **JSONPlaceholder API** - Sample REST API
   - Posts API tests
   - Users API tests
   - Relationships and nested data tests

3. **ReqRes API** - User management
   - User CRUD operations
   - Authentication and authorization

## 🏗️ Project Structure

```
api-automation/
├── src/
│   ├── main/java/
│   │   ├── base/              # Base test classes
│   │   ├── config/            # Configuration management
│   │   ├── constants/         # API endpoints
│   │   ├── pojo/              # Request/Response POJOs
│   │   └── utils/             # Utility classes
│   └── test/java/
│       └── tests/             # Test classes
│           ├── booking/       # Restful Booker tests
│           ├── jsonPlaceholder/  # JSONPlaceholder tests
│           └── reqres/        # ReqRes tests
├── src/test/resources/
│   ├── config.properties      # Test configuration
│   ├── schemas/               # JSON schemas for validation
│   └── *.xml                  # TestNG suite files
├── Jenkinsfile                # Jenkins pipeline configuration
├── JENKINS_SETUP.md           # Detailed Jenkins setup guide
└── pom.xml                    # Maven configuration
```

## 🔧 Prerequisites

- Java 11 or higher
- Maven 3.9+
- Jenkins (optional, for CI/CD)
- Git

## 📥 Installation

### Clone the Repository

```bash
git clone https://github.com/ashwinirajm/restAssured-api-automation.git
cd restAssured-api-automation
```

### Install Dependencies

```bash
mvn clean install
```

## ▶️ Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Suite

```bash
# Authentication Tests
mvn test -DsuiteXmlFile=src/test/resources/booking-auth-api-suite.xml

# Schema Validation Tests
mvn test -DsuiteXmlFile=src/test/resources/booking-auth-schema-suite.xml

# Posts API Tests
mvn test -DsuiteXmlFile=src/test/resources/posts-api-suite.xml

# ReqRes API Tests
mvn test -DsuiteXmlFile=src/test/resources/reqres-api-suite.xml
```

## 📈 Viewing Test Reports

### Allure Reports (Local)

After running tests, generate Allure reports:

```bash
# Generate and open Allure report
mvn allure:serve
```

This will generate an interactive HTML report and automatically open it in your browser.

### TestNG Reports

TestNG HTML reports are automatically generated at:
```
target/surefire-reports/index.html
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

### Accessing Jenkins Reports

#### Option 1: Local Jenkins Instance

If you have Jenkins running locally:

1. **Access Jenkins Dashboard**: http://localhost:8080
2. **Navigate to Job**: Click on `API-Automation-Pipeline`
3. **View Latest Build**: Click on the latest build number (e.g., #4)
4. **Access Reports**:
   - **Allure Report**: Click "Allure Report" in the left sidebar
   - **TestNG Report**: Click "TestNG Report" in the left sidebar
   - **Console Output**: Click "Console Output" to see execution logs

#### Option 2: Shared Jenkins Server

If Jenkins is hosted on a server:

1. Access Jenkins at: `http://<jenkins-server-url>:8080`
2. Navigate to the `API-Automation-Pipeline` job
3. View build history and reports

### Running Tests via Jenkins

1. Go to Jenkins Dashboard
2. Click on `API-Automation-Pipeline`
3. Click **"Build with Parameters"**
4. Select desired test suite from dropdown:
   - `booking-auth-api-suite.xml`
   - `booking-auth-schema-suite.xml`
   - `booking-schema-suite.xml`
   - `posts-api-suite.xml`
   - `reqres-api-suite.xml`
5. Click **"Build"**
6. Monitor build progress in "Build History"
7. Once complete, view Allure Report for detailed test results

### Sample Allure Report Features

- 📊 **Overview Dashboard** - Summary of test results
- 🔍 **Test Cases** - Detailed test execution information
- 📝 **Categories** - Test failures grouped by type
- 📈 **Graphs** - Visual representation of test trends
- 🕐 **Timeline** - Test execution timeline
- 📂 **Behaviors** - Tests organized by features and stories

## 🛠️ Setting Up Jenkins

For detailed Jenkins setup instructions, see [JENKINS_SETUP.md](JENKINS_SETUP.md)

### Quick Start

1. **Install Jenkins**:
   ```bash
   brew install jenkins-lts
   brew services start jenkins-lts
   ```

2. **Access Jenkins**: http://localhost:8080

3. **Install Required Plugins**:
   - Git Plugin
   - Maven Integration
   - Pipeline Plugin
   - Allure Plugin
   - HTML Publisher

4. **Configure Tools**:
   - JDK 11
   - Maven 3.9+
   - Allure Commandline

5. **Create Pipeline Job**:
   - New Item → Pipeline
   - Configure SCM: GitHub repository
   - Script Path: `Jenkinsfile`

6. **Run Your First Build**!

## 📊 Test Results Summary

### Current Test Status

| Test Suite | Tests | Passed | Failed | Duration |
|------------|-------|--------|--------|----------|
| Authentication API | 6 | ✅ 6 | ❌ 0 | ~5s |
| Schema Validation | 4 | ✅ 4 | ❌ 0 | ~6s |
| Booking API | TBD | - | - | - |
| Posts API | TBD | - | - | - |
| ReqRes API | TBD | - | - | - |

*Run `mvn test` to get updated results*

## 🏆 Best Practices Implemented

- ✅ Page Object Model (POM) design pattern
- ✅ Data-driven testing with TestNG
- ✅ Centralized configuration management
- ✅ Request/Response POJOs for type safety
- ✅ JSON Schema validation
- ✅ Allure annotations for detailed reporting
- ✅ Maven for dependency management
- ✅ Jenkins pipeline as code
- ✅ Git version control

## 🔐 Configuration

Test configuration is managed in `src/test/resources/config.properties`:

```properties
# API Base URLs
booking.base.url=https://restful-booker.herokuapp.com
posts.base.url=https://jsonplaceholder.typicode.com
reqres.base.url=https://reqres.in/api

# Authentication
booking.username=admin
booking.password=password123
```

## 🐛 Troubleshooting

### Common Issues

**Tests fail with connection timeout**
- Check API endpoints are accessible
- Verify internet connection
- Check firewall settings

**Allure report not generating**
- Ensure Allure is installed: `mvn allure:serve`
- Check `target/allure-results` directory exists

**Jenkins build fails**
- Verify Maven and JDK are configured in Jenkins
- Check console output for detailed errors
- Ensure all plugins are installed

## 📚 Documentation

- [Jenkins Setup Guide](JENKINS_SETUP.md) - Complete Jenkins configuration guide
- [Jenkinsfile](Jenkinsfile) - Pipeline configuration
- [REST Assured Documentation](https://rest-assured.io/)
- [Allure Documentation](https://docs.qameta.io/allure/)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run tests to ensure they pass
5. Submit a pull request

## 📧 Contact

**Author**: Ashwini Raj M
**GitHub**: [@ashwinirajm](https://github.com/ashwinirajm)
**Email**: ashwini.ash162@gmail.com

## 📄 License

This project is available for educational and testing purposes.

---

## 🎯 Next Steps

- [ ] Add more test cases for booking API
- [ ] Implement data-driven tests with CSV/Excel
- [ ] Add database validation
- [ ] Set up scheduled Jenkins builds
- [ ] Add Slack/Email notifications
- [ ] Integrate with GitHub Actions
- [ ] Add performance testing with JMeter

---

**⭐ If you find this project helpful, please give it a star!**

---

*Last Updated: February 2026*