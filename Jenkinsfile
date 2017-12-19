pipeline {
    agent any

    stages {
        stage('Commit') {
            echo "Starting build ${BUILD_ID} for ${JOB_NAME}"
            sh "./gradlew build"
        }
    }
    post {
        failure {
            mail to: 'mark-donahue@idexx.com',
            subject: "[${env.JOB_NAME}] Build ${BUILD_ID} Failed",
            body: "Something is wrong with the build. Check out the details here: ${env.BUILD_URL}"
        }
        changed {
            mail to: 'mark-donahue@idexx.com',
                subject: "[${env.JOB_NAME}] Build ${BUILD_ID} status changed",
                body: "Build is back to normal: ${env.BUILD_URL}"
        }
    }
}