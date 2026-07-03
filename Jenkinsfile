pipeline {

    agent any

    parameters {
        string(
            name: 'NEXUS_USERNAME',
            defaultValue: 'admin',
            description: 'Nexus Username'
        )

        password(
            name: 'NEXUS_PASSWORD',
            defaultValue: '',
            description: 'Nexus Password'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -Drevision=1.0.0-%BUILD_NUMBER%'
            }
        }

        stage('Deploy to Nexus') {
            steps {

                writeFile file: 'settings.xml', text: """
<settings>
    <servers>
        <server>
            <id>nexus</id>
            <username>${params.NEXUS_USERNAME}</username>
            <password>${params.NEXUS_PASSWORD}</password>
        </server>
    </servers>
</settings>
"""

                bat 'mvn deploy -s settings.xml -Drevision=1.0.0-%BUILD_NUMBER%'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
    }

    post {

        success {
            echo 'Application successfully deployed to Nexus.'
        }

        failure {
            echo 'Build Failed.'
        }
    }
}
