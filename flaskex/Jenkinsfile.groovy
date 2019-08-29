node{
    stage("Update Jenkins"){
          properties([parameters([string(defaultValue: '54.194.190.109', description: 'Please provide IP of the environment you want to deploy', name: 'ENVIR', trim: true)])])
          sh "echo Parameter added"
    }
    stage("Install git"){
        sh "ssh centos@${ENVIR} sudo yum install git epel-release python python-pip -y"
        // sh "ssh centos@${ENVIR} sudo pip install --upgrade pip"
    }
    stage("Remove Repo"){
        sh "ssh centos@${ENVIR} sudo rm -rf /home/centos/flask-examples"
    }
    stage("Pull Repo"){
        sh "ssh centos@${ENVIR} git clone https://github.com/anfederico/Flaskex.git"

    }
    stage("Install Requirements"){
        //sh "virtualenv /tmp/venv"
        //sh ". /tmp/venv/bin/activate"
        sh "echo Hello"
    }
    stage("Pip Install"){
        sh "ssh centos@${ENVIR} sudo pip install -r /home/centos/flaskex/requirements.txt"
    }
    stage("Run App"){
        sh "ssh qcentos@${ENVIR} sudo python /home/centos/flaskex/app.py"
    }
}

