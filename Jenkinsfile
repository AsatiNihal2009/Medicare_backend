pipeline {
    agent any
    tools { 
        maven 'maven' 
        jdk 'java_home'
    }
    stages{
        stage('code checkout') {
            steps {
                git branch: 'main', credentialsId: '60cf59e8-768c-4e6c-88c7-d5411a5422ba', url: 'https://github.com/AsatiNihal2009/Medicare_backend.git'
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv(installationName: 'sonaqube',credentialsId: 'ci') {
                    sh 'mvn clean verify sonar:sonar'
                    sleep 60;
                }
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
            
        }
        stage("build") {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('publish over ssh') { 
            steps([$class: 'BapSshPromotionPublisherPlugin']) {               
                sshPublisher(
                    continueOnError: false, failOnError: true,
                    publishers: [
                        sshPublisherDesc(
                            configName:"dev-1",
                            verbose: true,
                            transfers:[
                                sshTransfer(
                                    sourceFiles:"**/*.jar",
                                    removePrefix:"target/",
                                    remoteDirectory:"",
                                    execCommand:"bash exec.sh"
                                )
                            ]
                        )
                    ]
                )
            }
        }
    }
}