pipeline {
    agent any

    tools {
        maven 'Maven 4.0.0'   // Must match a configured tool in Jenkins
        jdk 'Java 17'         // Adjust based on your Jenkins JDK setup
    }

    environment {
        HEADLESS = 'true'     // Custom variable for your test logic
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vitex-dev1/PlanZTestScripts'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
    }
}
