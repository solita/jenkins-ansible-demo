# Demo: Jenkins Configuration Management with Ansible

This Ansible project contains server and pipeline configuration for [a hello
world application](https://github.com/noidi/hello-java). It demonstrates how
you can develop and test Jenkins jobs and pipelines against a Jenkins
installation in a Vagrant machine and only apply the finished configuration to
a production Jenkins installation.

The same project is used both for server configuration (`site.yml`) and
deployment (`deploy.yml`). The Jenkins pipeline clones/rsyncs this project and
uses it to do the deployments.

## Initial setup

1. Install [Vagrant](https://www.vagrantup.com/)

2. Clone this repository somewhere, e.g. `~/projects/jenkins-ansible-demo`.

3. Clone [noidi/hello-java](https://github.com/noidi/hello-java) (a simple
   application that the example pipeline builds) into the same parent
   directory, e.g. `~/projects/hello-java`.

4. Create and provision a Vagrant machine:

        cd ~/projects/jenkins-ansible-demo
        vagrant up


5. Open http://localhost:8080 in a web browser. You should see the Jenkins
   installation running in the Vagrant machine.

## Pipeline development workflow

1. Edit the `Vagrantfile` and add the following line after the
   `ansible.playbook` and `ansible.groups` settings:

        ansible.tags = 'solita_jenkins_jobs'

   This makes provisioning faster by limiting it to just Jenkins jobs.

2. Edit the job definition in `jobs/Hello.groovy` or the pipeline definition in
   `jobs/pipeline/Hello.groovy.j2`.

3. Run `vagrant provision` to update Jenkins' configuration.

4. GOTO 2 until you're happy with your changes.

## Moving the configuration to real servers

1. Edit the inventories `environments/<ci|qa|prod>/inventory` and replace
   `jenkins-ansible-demo1`, `jenkins-ansible-demo2`, `jenkins-ansible-demo3`
   with servers to which you have SSH and sudo access.

2. Provision the servers in each environment:

        ansible-playbook -i environments/ci/inventory site.yml
        ansible-playbook -i environments/qa/inventory site.yml
        ansible-playbook -i environments/prod/inventory site.yml

## Known issues

- The SSH host keys of the hosts in the `jenkins_sudo_access` group are not
  added to the `jenkins` user's `.ssh/known_hosts` automatically. The
  pipeline's deployment stages won't complete successfully until you've done
  this manually (or configured Ansible to disable host key checking). This is
  not a fundamental problem but simply an omission (PRs accepted!).

- **(WON'T FIX)** `deploy.yml` doesn't do a proper deployment. It simply copies
  the JAR over to the server. Service setup and monitoring is out of scope for
  this example.

## License

Copyright &copy; 2017 [Solita](https://www.solita.fi/)

Licensed under the [MIT license](LICENSE.txt)
