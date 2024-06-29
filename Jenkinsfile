pipeline {
    agent any
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
