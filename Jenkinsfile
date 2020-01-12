node {
     stage("checkout repo") {
        git branch: 'master',
        credentialsId: '816f93d1-73c0-4824-8e8d-06f02a139d94',
        url: 'https://github.com/MykolaLutsiv/api-training-code.git'
    }

    stage("build") {
        sh "./gradlew clean api-test:assemble"
    }

    stage("run api tests") {
            sh "./gradlew api-test:test"
        }



}