node{
    stage("Update Jenkins"){
          properties([parameters([string(defaultValue: '54.194.190.109', description: 'Please provide IP of the environment you want to deploy', name: 'ENVIR', trim: true)])])
          sh "echo Parameter added"
    }
    stage("Install git"){
        sh "ssh centos@${ENVIR} sudo yum install git epel-release python-pip -y"
    }
    stage("Remove Repo"){
        sh "ssh centos@${ENVIR} sudo rm -rf /home/centos/flask-examples"
    }
    stage("Pull Repo"){
        sh "ssh centos@${ENVIR} git clone https://github.com/NadiraSaip/flask-examples.git"

    }
    stage("Install Requirements"){
        //sh "virtualenv /tmp/venv"
        //sh ". /tmp/venv/bin/activate"
        sh "echo Hello"
    }
    stage("Pip Install"){
        sh "ssh centos@${ENVIR} pip install -r /home/centos/flask-examples/requirements.txt"
    }
    stage("Run App"){
        sh "ssh qcentos@${ENVIR} python /home/centos/flask-examples/01-hello-world/hello.py"
    }
}

