node {
     stage("checkout repo") {
        git branch: 'master',
        credentialsId: '7714ecee-22f4-429e-ba81-a0ade8b1888b',
        url: 'https://github.com/MykolaLutsiv/api-training-code.git'
    }

    stage("build") {
        sh "bash ./gradlew clean api-test:assemble"
    }

    stage("run api tests") {
        sh "bash ./gradlew api-test:test -Dlogging=${LOGGING}"
    }
}