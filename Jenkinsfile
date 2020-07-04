node {
     stage("checkout repo") {
        git branch: 'master',
        credentialsId: '5606dfbc-ff70-4237-bdbb-57327199f53a',
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