node {
     stage("checkout repo") {
        git branch: 'master',
        credentialsId: 'aca9cb84-5aa3-4f71-b936-dc6b0b3fe20d',
        url: 'https://github.com/MykolaLutsiv/api-training-code.git'
    }

 //   stage("build"){
 //       sh "./gradlew clean api-test:assemble"
 //   }

    stage("run api tests"){
        sh "./gradlew api-test:test"
    }
}