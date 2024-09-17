pipeline {
    agent any

    stages {
        stage('Trigger DevJobImulation_G10') {
            steps {
                script {
                    def devJob = build(job: 'DevJobImulation_G10', propagate: false)
                    if (devJob.result == 'SUCCESS') {
                        stage('Trigger Deploing_ENV') {
                            def envJob = build(job: 'Deploing_ENV', propagate: false)
                            if (envJob.result == 'SUCCESS') {
                                stage('Trigger G10_run_test') {
                                    build(job: 'G10_run_test', parameters: [
                                        string(name: 'Module', value: 'overAll'),
                                        string(name: 'testName', value: 'LoginTestWithPageObject#TR001_validLogin'),
                                        string(name: 'defaultLogin', value: '-DdefaultLogin=qaauto'),
                                        string(name: 'additionalParameters', value: '-Denv=aqa'),
                                        string(name: 'devBuildNumber', value: String.valueOf(devJob.number))
                                    ], quietPeriod: 0, wait: true)
                                }
                            } else {
                                error("Deploing_ENV job failed")
                            }
                        }
                    } else {
                        error("DevJobImulation_G10 job failed")
                    }
                }
            }
        }
    }
}