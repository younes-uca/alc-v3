pipeline {
    agent { label 'dev' }

 stages {
        stage('Build') {
            steps {
                sh 'pwd'
		        sh './mvnw install -Dmaven.test.skip=true '
                sh './mvnw package -Dmaven.test.skip=true'
            }
        }
        stage('Deploy') {
            steps {
                 script {
                        sh 'docker build -t back-project .'

                    try {
			            sh 'docker rm back-project -f'
                        sh 'docker run --name back-project --net back-project --restart always -d -p 8036:8036 back-project'
                    }
                    catch (Exception e) {
                        sh 'docker run --name back-project --restart always --net back-project -d -p 8036:8036 back-project'
                    }
                }
            }
        }
    }
}
