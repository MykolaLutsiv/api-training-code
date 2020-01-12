node {
     stage("checkout repo") {
        git branch: 'master',
        credentialsId: '17b8c4b2-16e0-4037-a15d-a5af8ee0fa7c',
        url: 'https://github.com/MykolaLutsiv/api-training-code.git'
    }

    stage("build") {
        sh "./gradlew clean api-test:assemble"
    }

    stage("run api tests") {
            sh "./gradlew api-test:test"
        }



}