pipeline {
    agent any
    tools {
        maven 'Maven 3.8.1' // Use the name you configured in Jenkins
    }
    stages {
        stage('clone') {
            steps {
                sh "rm -rf *"
                sh "git clone https://github.com/Aloui-Soumaya/Spring-Boot-Email-Verification.git"
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }
}
