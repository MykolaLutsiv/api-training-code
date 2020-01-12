node {
     stage("checkout repo") {
        git branch: 'master',
        credentialsId: '9c967b5f-aa54-40b7-8072-a42e99e4ba73',
        url: 'https://github.com/MykolaLutsiv/api-training-code.git'
    }

    stage("build") {
        sh "./gradlew clean api-test:assemble"
    }

    stage("run api tests") {
            sh "./gradlew api-test:test"
        }



}