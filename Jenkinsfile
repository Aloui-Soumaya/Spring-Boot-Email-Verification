pipeline {
    agent any
    stages {
        stage('clone') {
            steps {
                sh "git clone https://github.com/Aloui-Soumaya/Spring-Boot-Email-Verification.git"
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
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
