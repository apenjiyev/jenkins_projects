node {
    stage("Update Jenkins"){
        properties([parameters([string(defaultValue: '107.21.175.231', description: 'Please provide IP', name: 'Environment', trim: true)])])
        echo "echo Parameter added"
    }
    stage("install git"){
        sh "ssh ec2-user@${Environment} sudo yum install git -y"

    }
    stage("Pull Repo"){
        sh "ssh ec2-user@${Environment} git clone https://github.com/miguelgrinberg/flask-examples.git"
    }
    stage("Install requirements"){
        //sh "virtualenv /tmp/venv"
        //sh ". /tmp/venv/bin/activate"
        sh "echo hello"
    }
    stage("pip install"){
        sh "ssh ec2-user@${Environment} pip install -r ~/flask-examples/requirements.txt"
    }
    stage("Run App"){
       sh "ssh ec2-user@${Environment} python ~/flask-examples/01-hello-world/hello.py"
    }
}