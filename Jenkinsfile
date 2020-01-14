pipeline {
    environment {
     def registryCredential = 'dockerhubcred'
   def mysonarqubeserver = 'Sonarqube'
    def dockinst = ''
    def dockerid=''
    
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
    stage('Test') {
             steps {
                 script{
                        try{
                             echo '******** TESTING CODE **********'
                             sh 'mvn test'
                             echo '******** TESTING CODE WAS SUCCESSFUL **********'
                         }catch (e) {
                             echo '******** TESTING CODE WAS NOT SUCCESSFUL DUE TO ERROR ********'+e
                         }
                 }
             }
    }

    stage('JaCoCo') {
                      steps {
                          script{
                               try{
                                    echo '******** JACOCO **********'
                                    jacoco()
                                    echo '******** JACOCO STAGE WAS SUCCESSFUL **********'
                               }catch (e) {
                                    echo '******** JACOCO STAGE WAS NOT SUCCESSFUL DUE TO ERROR ********'+e
                               }
                          }
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
  
stage('docker stop container') {
       steps{
           
           //sh 'set dockerimage=$(docker ps -f name=emp-rest -q)'
           //sh 'echo $(docker ps -f name=emp-rest -q) 
               script{
                   try{
                        
                       //def dockimage = sh(script:'$(docker ps -f name=emp-rest -q)', returnStdout:true).trim()
                       sh 'docker rm $(docker ps -f name=emp-rest -q) -f'
                      // sh 'docker container prune | echo "Y"'
                       
                      // print 'Authis ' + dockimage
                       //sh 'docker stop $(docker ps -f name=emp-rest -q)'
                    //   if('sh $(docker ps -f name=emp-rest -q)' != null){
                    //       echo 'I am Not empty '+NULL
                    //      }else{
                    //       echo 'I am empty '+NULL
                    //      } 
                                // GIT_COMMIT_EMAIL = sh (
                                //     script: '$(docker ps -f name=emp-rest -q)',
                                //     returnStdout: true
                                // ).trim()
                                //  echo "Git committer email: ${GIT_COMMIT_EMAIL}"
                    
                    
                    //NULL = sh 'docker stop $(docker ps -f name=emp-rest -q)'
                    //echo 'hiiiiiiiiiii ' +NULL
                    //sh 'docker rm -f $(docker ps -f name=emp-rest -q)'
                   }catch(err){
                      echo 'I am in exception'+ err
                   }
               } 
            }
    } 
    
stage('run image') {
          steps{
            script {
              docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
                 docker.image('registry.hub.docker.com/authis/employee-react-restapp').run('--name emp-rest --publish 5009:5002')
              }  
          }
       }
    }
  
    
 }
}
