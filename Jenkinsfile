pipeline {
    agent any

    tools {
        maven 'Maven 4.0.0'   // Make sure this matches the Maven version configured in Jenkins
        jdk 'Java 17'         // Adjust this to your installed JDK version in Jenkins
    }

    environment {
        HEADLESS = 'true'     // You can toggle this in your code
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

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
    }
}
