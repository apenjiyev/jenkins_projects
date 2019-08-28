node {
    stage("Update Jenkins"){
        properties([parameters([string(defaultValue: '107.21.175.231', description: 'Please provide IP', name: 'ENVIR', trim: true)])])
        echo "echo Parameter added"
    }
    stage("install git"){
        sh "ssh ec2-user@${ENVIR} sudo yum install git python-pip -y"

    }
    stage("Pull Repo"){
        sh "ssh ec2-user@${ENVIR} git clone https://github.com/miguelgrinberg/flask-examples.git"
    }
    stage("Install requirements"){
        //sh "virtualenv /tmp/venv"
        //sh ". /tmp/venv/bin/activate"
        sh "echo hello"
    }
    stage("pip install"){
        sh "ssh ec2-user@${ENVIR} pip install -r ~/flask-examples/requirements.txt"
    }
    stage("Run App"){
       sh "ssh ec2-user@${ENVIR} python ~/flask-examples/01-hello-world/hello.py"
    }
}