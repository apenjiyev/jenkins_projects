node {
    stage("Update Jenkins"){
        properties([parameters([string(defaultValue: '107.21.175.231', description: 'Please provide IP', name: 'ENVIR', trim: true)])])
        echo "echo Parameter added"
    }
    stage("install git"){
        sh "ssh ec2-user@${ENVIR} sudo yum install git python-pip -y"

    }
    stage("remove repo"){
        sh "ssh ec2-user@${ENVIR} sudo rm -rf /home/ec2-user/flask-examples"
    }
    stage("Pull Repo"){
        sh "ssh ec2-user@${ENVIR} git clone https://github.com/apenjiyev/flask-examples.git"
    }
    stage("Install requirements"){
        //sh "virtualenv /tmp/venv"
        //sh ". /tmp/venv/bin/activate"
        sh "echo hello"
    }
    stage("pip install"){
        sh "ssh ec2-user@${ENVIR} sudo pip install -r /home/ec2-user/flask-examples/requirements.txt"
    }
    stage("Run App"){
       sh "ssh ec2-user@${ENVIR} python /home/ec2-user/flask-examples/01-hello-world/hello.py"
    }
}