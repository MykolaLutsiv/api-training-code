node {
     stage("checkout repo") {
        git branch: 'master',
        credentialsId: '4a023716-092a-4c87-a752-a449109046fa',
        url: 'https://github.com/MykolaLutsiv/api-training-code.git'
    }

    stage("build") {
        sh "bash ./gradlew clean api-test:assemble"
    }

    stage("run api tests") {
        sh "bash ./gradlew api-test:test -Dlogging=%LOGGING%"
    }

    allure([
        includeProperties: false,
        jdk: '',
        properties: [],
        reportBuildPolicy: 'ALWAYS',
        results: [[path: 'api-test/build/allure-results']]
    ])
}