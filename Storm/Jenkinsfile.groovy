node {
    stage("Update Jenkins"){
        properties([parameters([string(defaultValue: '107.21.175.231', description: 'Please provide IP', name: 'ENVIR', trim: true)])])
        echo "echo Parameter added"
    }
    stage("install git"){
        sh "ssh ec2-user@${ENVIR} sudo yum install git python-pip -y"
    }
    stage("Pull Repo"){
        sh "ssh ec2-user@${ENVIR} git clone https://github.com/apenjiyev/stormpath-flask-sample.git 2> /dev/null"
    }
    stage("Install requirements"){
        sh "echo hello"
    }
    stage("pip install"){
        sh "ssh ec2-user@${ENVIR} sudo pip install -r /home/ec2-user/stormpath-flask-sample/requirements.txt"
    }
    stage("Run App"){
       sh "ssh ec2-user@${ENVIR} python /home/ec2-user/stormpath-flask-sample/bootstrap.py"
    }
}