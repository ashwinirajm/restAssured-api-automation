pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-11'
    }

    parameters {
        choice(
            name: 'TEST_SUITE',
            choices: ['booking-auth-api-suite.xml', 'booking-auth-schema-suite.xml', 'booking-schema-suite.xml', 'posts-api-suite.xml', 'reqres-api-suite.xml'],
            description: 'Select test suite to execute'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from GitHub...'
                checkout scm
            }
        }

        stage('Clean') {
            steps {
                echo 'Cleaning previous build artifacts...'
                sh 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                echo 'Compiling the project...'
                sh 'mvn compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running test suite: ${params.TEST_SUITE}"
                sh "mvn test -DsuiteXmlFile=src/test/resources/${params.TEST_SUITE}"
            }
        }
    }

    post {
        always {
            echo 'Publishing test results...'

            // Publish TestNG results
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/surefire-reports',
                reportFiles: 'index.html',
                reportName: 'TestNG Report'
            ])

            // Publish Allure results
            allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results']]
            ])

            // Archive test reports
            archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
        }

        success {
            echo 'Tests executed successfully!'
        }

        failure {
            echo 'Tests failed. Please check the reports.'
        }
    }
}