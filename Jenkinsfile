properties([
    parameters([
        string(
            name: 'NEXUS_USERNAME',
            defaultValue: 'admin',
            description: 'Nexus Username'
        ),
        password(
            name: 'NEXUS_PASSWORD',
            defaultValue: '',
            description: 'Nexus Password'
        )
    ])
])

node {

    stage('Checkout') {
        echo 'Source code checked out successfully.'
    }

    stage('Build') {
        echo 'Building Spring Boot Application...'
        bat 'mvn clean package -Drevision=1.0.0-%BUILD_NUMBER%'
    }

    stage('Deploy to Nexus') {

        echo 'Creating temporary settings.xml...'

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

    stage('Archive') {

        archiveArtifacts artifacts: 'target/*.jar'

    }

    echo 'Pipeline Completed Successfully'
}