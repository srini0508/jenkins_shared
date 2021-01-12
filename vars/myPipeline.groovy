def call(Map pipelineParams){
pipeline {
   agent any

   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven "Maven"
   }

   stages {
       stage('checkout'){
           steps{
            // Get some code from a GitHub repository
            gitCheckout(branch:"master",url:'')
	   }
       }
      stage('Compile') {
         steps {
            // bat for windows and sh for linux
            //bat "mvn clean compile"
            //sh "mvn clean compile"
            echo "Compile Stage"
         }
            post{
                success {
                    echo "compile successful"
                }
            }
      }
      stage ('install'){
          steps{
	        echo "${pipelineParams.buildNumber}"
                //bat "mvn -Dmaven.main.skip=true integration-test"
                //bat "mvn install -DbuildNumber=${pipelineParams.buildNumber}"   
                //sh "mvn -Dmaven.main.skip=true integration-test"
                //sh "mvn install -DbuildNumber=${pipelineParams.buildNumber}" 
          }
          post {
              success{
                    echo "install and test successful"
              }

          }
      }
      stage ('deploy'){
          steps{
	        echo "deploy stage"
               //bat "mvn -s settings.xml -Dmaven.test.skip=true deploy"
               //bat "jfrog rt u target/jfrog-app-SNAPSHOT-*.*.*.*.jar libs-snapshot-local/com/jfrog/app/jfrog-app/"
               //sh "mvn -s settings.xml -Dmaven.test.skip=true deploy"
               //sh "jfrog rt u target/jfrog-app-SNAPSHOT-*.*.*.*.jar libs-snapshot-local/com/jfrog/app/jfrog-app/"
          }
          post{
              success{
                    mail bcc: '', body: '''hey,
                    Deploy Successful to the jfrog artifactory''', cc: '',
                    from: '', replyTo: '', subject: 'Deploy Successful',
                    to: 'XYZ@gmail.com'
          }
          }
      }
   }
}
}
