node{
    stage("Update Jenkins"){
          properties([parameters([string(defaultValue: '54.194.190.109', description: 'Please provide IP of the environment you want to deploy', name: 'ENVIR', trim: true)])])
          sh "echo Parameter added"
    }
    stage("Install git"){
        sh "ssh centos@${ENVIR} sudo yum install git epel-release python python-pip -y"
        // sh "ssh centos@${ENVIR} sudo pip install --upgrade pip"
    }
    stage("Pull Repo"){
        sh "ssh centos@${ENVIR} git clone https://github.com/NadiraSaip/stormpath-flask-sample.git 2> /dev/null"

    }
    stage("Install Requirements"){
        sh "echo Hello"
    }
    stage("Pip Install"){
        // sh "ssh centos@${ENVIR} sudo pip install -r /home/centos/stormpath-flask-sample/requirements.txt"
    }
    stage("Run App"){
        sh "ssh centos@${ENVIR} sudo python /home/centos/storm/app.py"
    }
}

