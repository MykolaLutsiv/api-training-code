node {
     stage("checkout repo") {
        git branch: 'master',
        credentialsId: '53ea92ba-20a7-4a04-8ab0-2acb8e0b0991',
        url: 'https://github.com/MykolaLutsiv/api-training-code.git'
    }

    stage("build") {
        sh "bash ./gradlew clean api-test:assemble"
    }

    stage("run api tests") {
        sh "bash ./gradlew api-test:test"
    }

    allure([
        includeProperties: false,
        jdk: '',
        properties: [],
        reportBuildPolicy: 'ALWAYS',
        results: [[path: 'api-test/build/allure-results']]
    ])
}

