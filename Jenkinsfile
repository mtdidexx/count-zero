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
            script {
                if (env.GIT_BRANCH == 'master') {
                    echo 'Sending email due to failure on master'
                    mail to: 'mark-donahue@idexx.com',
                        subject: "[${env.JOB_NAME}] Build ${BUILD_ID} Failed",
                        body: "Something is wrong with the build. Check out the details here: ${env.BUILD_URL}"
                } else {
                    echo "No Notifications for failures on branch: ${env.GIT_BRANCH}"
                }
            }
        }
        changed {
            script {
                if (env.GIT_BRANCH == 'master' && (currentBuild.result == 'SUCCESS' || currentBuild.result == null)) {
                    echo "Build status on branch ${env.GIT_BRANCH} has changed to: [${currentBuild.result}]"
                    mail to: 'mark-donahue@idexx.com',
                            subject: "[${env.JOB_NAME}] Build ${BUILD_ID} status changed",
                            body: "Build is back to normal: ${env.BUILD_URL}"
                } else {
                    echo 'Not sending notifications from changed block'
                }
            }
        }
    }
}