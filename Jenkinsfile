pipeline {
    agent any

    stages {
        stage('Commit') {
            steps{
                echo "Starting build ${BUILD_ID} for ${JOB_NAME}"
                sh "./gradlew build"
            }
        }
    }
    post {
        always {
            echo "Build result: [${currentBuild.result}]"
            echo "Git branch: [${env.GIT_BRANCH}]"
        }
        failure {
            mail to: 'mark-donahue@idexx.com',
            subject: "[${env.JOB_NAME}] Build ${BUILD_ID} Failed",
            body: "Something is wrong with the build. Check out the details here: ${env.BUILD_URL}"
        }
        changed {
            echo 'Build result changed'
            script {
                if (currentBuild.result == 'SUCCESS') {
                    echo "Build status has changed to: [${currentBuild.result}]"
                }
            }
            mail to: 'mark-donahue@idexx.com',
                subject: "[${env.JOB_NAME}] Build ${BUILD_ID} status changed",
                body: "Build is back to normal: ${env.BUILD_URL}"
        }
    }
}