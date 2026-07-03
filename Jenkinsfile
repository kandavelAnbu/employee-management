pipeline {

    agent any

    tools {
        maven 'mavenConfigure'
    }

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
                echo 'Source code checked out successfully.'
            }
        }

        stage('Build') {
            steps {
                echo 'Building Spring Boot application...'
                bat 'mvn clean package -Drevision=1.0.0-%BUILD_NUMBER%'
            }
        }

        stage('Deploy to Nexus') {
            steps {

                echo 'Creating temporary settings.xml file...'

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

                echo 'Deploying artifact to Nexus...'

                bat 'mvn deploy -s settings.xml -Drevision=1.0.0-%BUILD_NUMBER%'
            }
        }

        stage('Archive Artifact') {
            steps {
                echo 'Archiving generated JAR...'
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

        always {
            echo 'Cleaning temporary files...'
            deleteDir()
        }
    }
}
