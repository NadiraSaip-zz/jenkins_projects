node{
    stage("Update Jenkins"){
          properties([parameters([string(defaultValue: '54.194.190.109', description: 'Please provide IP of the environment you want to deploy', name: 'ENVIR', trim: true)])])
          sh "echo Parameter added"
    }
    stage("Install git"){
        sh "ssh centos@${ENVIR} sudo yum install git python-pip -y"
    }
    stage("Remove Repo"){
        sh "ssh centos@${ENVIR} sudo rm -rf /home/centos/flask-examples"
    }
    stage("Pull Repo"){
        sh "ssh centos@${ENVIR} git clone git@github.com:NadiraSaip/flask-examples.git"

    }
    stage("Install Requirements"){
        //sh "virtualenv /tmp/venv"
        //sh ". /tmp/venv/bin/activate"
        sh "echo Hello"
    }
    stage("Pip Install"){
        sh "centos@${ENVIR} sudo pip install -r /home/flask-examples/requirements.txt"
    }
    stage("Run App"){
        sh "centos@${ENVIR} python /home/flask-examples/01-hello-world/hello.py"
    }
}

