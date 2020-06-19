pipeline {
  agent none
  stages {
	  
	stage('Initialize'){
	  def dockerHome = tool 'Docker'
	  env.PATH = "${dockerHome}/bin:${env.PATH}"
	}
	  
    stage('Maven Install') {
      agent {
        docker {
          image 'maven:3.5.0'
        }
      }
      steps {
        sh 'mvn clean install'
      }
    }
  }    
}