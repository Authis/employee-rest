pipeline {
    environment {
   def registry = "authis/employeereg"
    def registryCredential = 'dockerhubcred'
   def mysonarqubeserver = 'Sonarqube'
    def dockinst = ''
}

  agent any

  tools {
      nodejs "Node v10"
      maven 'maven'

  }

  stages {
    stage('Example') {
      steps {
        sh 'npm config ls'
      }
    }
   stage('Cloning Git') {
      steps {
        git 'https://github.com/Authis/employee-rest'
      }
    }
    stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
     stage("SonarQube analysis") {

            steps {
              withSonarQubeEnv(mysonarqubeserver) {

               sh 'mvn clean package sonar:sonar'
              }
            }
        }

   stage('Building image') {
    steps{
      script {
        dockinst =  docker.build "authis/employee-react-restapp:latest"
       }
       sh 'echo "Build docker image Successfully >>>>>>>>>>>>>>>>>>>>>"'
    }

     }

      stage('Push image') {
       steps{
      script {
        docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
            dockinst.push()

        }
          sh 'echo "Trying to push Docker Build to Docker Hub"'
      }
       }
    }




 }
}