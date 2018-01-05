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
        success {
            script {
                if (env.GIT_BRANCH == 'master') {
                    echo "Sending email due to SUCCESS on master"
                    emailext(subject: "[${env.JOB_NAME}] Build ${BUILD_ID} SUCCESS",
                            recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],
                            body: "Build is OK. Check out the details here: ${env.BUILD_URL}")
                } else {
                    echo "No Notifications for failures on branch: ${env.GIT_BRANCH}"
                }
            }
        }
        failure {
            script {
                if (env.GIT_BRANCH == 'master') {
                    echo "Sending email due to failure on master"
                    emailext(subject: "[${env.JOB_NAME}] Build ${BUILD_ID} Failed",
                            recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],
                            body: "Something is wrong with the build. Check out the details here: ${env.BUILD_URL}")
                } else {
                    echo "No Notifications for failures on branch: ${env.GIT_BRANCH}"
                }
            }
        }
        changed {
            script {
                if (env.GIT_BRANCH == 'master' && currentBuild.currentResult == 'SUCCESS') {
                    echo "Build status on branch ${env.GIT_BRANCH} has changed to: [${currentBuild.result}]"
                    emailext(subject: "[${env.JOB_NAME}] Build ${BUILD_ID} status changed",
                            body: "Build is back to normal: ${env.BUILD_URL}",
                            recipientProviders: [[$class: 'DevelopersRecipientProvider']])
                }
            }
        }
    }
}