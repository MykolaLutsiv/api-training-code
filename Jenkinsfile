node {
     stage("checkout repo") {
        git branch: 'master',
        credentialsId: 'b456f2c0-35d9-4815-8837-e82deaaf5f15',
        url: 'https://github.com/MykolaLutsiv/api-training-code.git'
    }

    stage("build") {
        sh "./gradlew clean api-test:assemble"
    }

    stage("run api tests") {
            sh "./gradlew api-test:test"
        }



}