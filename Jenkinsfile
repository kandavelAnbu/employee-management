properties([
    parameters([
        string(
            name: 'NEXUS_USERNAME',
            defaultValue: 'admin',
            description: 'Nexus Username'
        ),
        password(
            name: 'NEXUS_PASSWORD',
            description: 'Enter Nexus Password'
        )
    ])
])

node {

    def mvnHome = tool 'mavenConfigure'

    stage('Checkout') {

        echo 'Checking out source code...'

        checkout scm
    }

    stage('Build') {

        echo 'Building Spring Boot Application...'

        bat "\"${mvnHome}\\bin\\mvn.cmd\" clean package -Drevision=1.0.0-%BUILD_NUMBER%"
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

        bat "\"${mvnHome}\\bin\\mvn.cmd\" deploy -s settings.xml -Drevision=1.0.0-%BUILD_NUMBER%"
    }

    stage('Archive') {

        archiveArtifacts artifacts: 'target/*.jar'
    }

    echo 'Pipeline Completed Successfully'
}
