# Jenkins Setup Guide for REST Assured API Automation

This guide will help you set up Jenkins to run your REST Assured API automation tests.

## Prerequisites

- Jenkins installed and running (version 2.x or higher)
- Access to Jenkins dashboard
- Git plugin installed in Jenkins
- Maven Integration plugin installed in Jenkins
- Allure plugin installed in Jenkins (optional, for test reports)

## Step 1: Install Required Jenkins Plugins

Navigate to **Manage Jenkins → Manage Plugins → Available** and install:

1. **Git Plugin** - For GitHub integration
2. **Maven Integration Plugin** - To run Maven builds
3. **Pipeline Plugin** - For Jenkinsfile support
4. **Allure Plugin** - For beautiful test reports
5. **HTML Publisher Plugin** - For publishing TestNG reports
6. **Email Extension Plugin** - For email notifications (optional)

## Step 2: Configure Global Tools

### Configure Maven
1. Go to **Manage Jenkins → Global Tool Configuration**
2. Scroll to **Maven** section
3. Click **Add Maven**
4. Name: `Maven-3.9`
5. Check "Install automatically"
6. Choose version: 3.9.x or higher
7. Click **Save**

### Configure JDK
1. In the same **Global Tool Configuration** page
2. Scroll to **JDK** section
3. Click **Add JDK**
4. Name: `JDK-11`
5. Check "Install automatically"
6. Choose Java 11 from Oracle or OpenJDK
7. Click **Save**

## Step 3: Create Jenkins Job (Freestyle Project)

### Option A: Freestyle Project (Simple Setup)

1. Click **New Item** from Jenkins dashboard
2. Enter job name: `API-Automation-Tests`
3. Select **Freestyle project**
4. Click **OK**

#### Source Code Management
1. Select **Git**
2. Repository URL: `https://github.com/ashwinirajm/restAssured-api-automation.git`
3. Credentials: Add your GitHub credentials if repo is private
4. Branch: `*/main`

#### Build Triggers (Optional)
- **Poll SCM**: `H/15 * * * *` (checks for changes every 15 minutes)
- **GitHub hook trigger**: Enable for automatic builds on push

#### Build Environment
- Check **Delete workspace before build starts** (optional, for clean builds)

#### Build Steps
1. Click **Add build step → Invoke top-level Maven targets**
2. Maven Version: `Maven-3.9`
3. Goals: `clean test -DsuiteXmlFile=src/test/resources/booking-auth-api-suite.xml`

#### Post-build Actions
1. **Publish TestNG Results**
   - TestNG XML report pattern: `**/testng-results.xml`

2. **Publish HTML reports** (requires HTML Publisher plugin)
   - HTML directory to archive: `target/surefire-reports`
   - Index page: `index.html`
   - Report title: `TestNG Report`

3. **Allure Report** (requires Allure plugin)
   - Path: `target/allure-results`

#### Save and Build
- Click **Save**
- Click **Build Now** to test

---

## Step 4: Create Pipeline Job (Recommended)

### Using Jenkinsfile

1. Click **New Item**
2. Enter name: `API-Automation-Pipeline`
3. Select **Pipeline**
4. Click **OK**

#### Pipeline Configuration
1. **Definition**: Pipeline script from SCM
2. **SCM**: Git
3. **Repository URL**: `https://github.com/ashwinirajm/restAssured-api-automation.git`
4. **Branch**: `*/main`
5. **Script Path**: `Jenkinsfile`

#### Build with Parameters
The Jenkinsfile includes a parameter to select which test suite to run:
- booking-auth-api-suite.xml
- booking-auth-schema-suite.xml
- booking-schema-suite.xml
- posts-api-suite.xml
- reqres-api-suite.xml

#### Save and Build
- Click **Save**
- Click **Build with Parameters**
- Select desired test suite
- Click **Build**

---

## Step 5: View Test Reports

After build completes:

1. **Console Output**: View real-time logs
2. **TestNG Results**: Click on "TestNG Results" link
3. **Allure Report**: Click on "Allure Report" link for detailed visual reports
4. **HTML Report**: Click on "TestNG Report" in the left sidebar

---

## Step 6: Configure Notifications (Optional)

### Email Notifications
1. Go to **Manage Jenkins → Configure System**
2. Scroll to **E-mail Notification**
3. Configure SMTP server settings
4. In your job configuration, add **Post-build Actions → E-mail Notification**
5. Enter recipient email addresses

### Slack Notifications (Optional)
1. Install **Slack Notification Plugin**
2. Configure Slack workspace integration
3. Add Slack notifications in post-build actions

---

## Parameterized Build Setup

To run different test suites dynamically:

1. In job configuration, check **This project is parameterized**
2. Add **Choice Parameter**:
   - Name: `TEST_SUITE`
   - Choices:
     ```
     booking-auth-api-suite.xml
     booking-auth-schema-suite.xml
     booking-schema-suite.xml
     posts-api-suite.xml
     reqres-api-suite.xml
     ```
3. Update Maven goals: `clean test -DsuiteXmlFile=src/test/resources/${TEST_SUITE}`

---

## Scheduled Builds

To run tests on a schedule:

1. In job configuration, under **Build Triggers**
2. Check **Build periodically**
3. Schedule examples:
   - `H 2 * * *` - Daily at 2 AM
   - `H */4 * * *` - Every 4 hours
   - `H 9 * * 1-5` - Weekdays at 9 AM

---

## Troubleshooting

### Build Fails with "command not found"
- Ensure Maven and JDK are properly configured in Global Tool Configuration
- Check tool names match exactly in Jenkinsfile

### Tests Fail
- Check Console Output for detailed error messages
- Verify API endpoints are accessible from Jenkins server
- Check config.properties for correct URLs

### Allure Report Not Generating
- Install Allure Commandline in Global Tool Configuration
- Ensure `allure-results` directory exists after test execution

### Permission Denied
- Ensure Jenkins has proper file system permissions
- Check workspace directory permissions

---

## Best Practices

1. **Use Pipeline as Code**: Store Jenkinsfile in repository
2. **Parameterize**: Make test suite selection dynamic
3. **Archive Artifacts**: Save test reports for historical reference
4. **Notifications**: Set up email/Slack for test failures
5. **Scheduled Runs**: Run smoke tests nightly, full suite weekly
6. **Clean Workspace**: Delete workspace before builds for consistency
7. **Parallel Execution**: Split test suites for faster execution

---

## Next Steps

1. Push the Jenkinsfile to your GitHub repository
2. Create a Jenkins pipeline job pointing to your repo
3. Run a test build
4. Configure notifications
5. Set up scheduled builds

For questions or issues, check Jenkins logs and build console output.