node{
    stage("Update Jenkins"){
          properties([parameters([string(defaultValue: '54.194.190.109', description: 'Please provide IP of the environment you want to deploy', name: 'Enviroment', trim: true)])])
          sh "echo Parameter added"
    }
    stage("Install git"){
        sh "ssh ec2_user@${Enviroment} sudo yum install git -y"
    }
    stage("Pull Repo"){
        sh "ssh ec2_user@${Enviroment} git clone https://github.com/miguelgrinberg/flask-examples.git"

    }
    stage("Install Requirements"){
        //sh "virtualenv /tmp/venv"
        //sh ". /tmp/venv/bin/activate"
        sh "echo Hello"
    }
    stage("Pip Install"){
        sh "ssh ec2_user@${Enviroment} pip install -r ~/flask-examples/requirements.txt"
    }
    stage("Run App"){
        sh "ssh ec2_user@${Enviroment} python ~/flask-examples/01-hello-world/hello.py"
    }
}
