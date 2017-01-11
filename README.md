# Demo: Jenkins Configuration Management with Ansible

This project is an example of using Ansible for Jenkins configuration
management. It demonstrates how you can develop and test Jenkins jobs and
pipelines against a Jenkins installation in a Vagrant machine and only apply
the finished configuration to a production Jenkins installation.

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

## Known issues

- The SSH host keys of the hosts in the `jenkins_sudo_access` group are not
  added to the `jenkins` user's `.ssh/known_hosts` automatically. This is not a
  fundamental problem but simply an omission (PRs accepted!).

## License

Copyright &copy; 2017 [Solita](https://www.solita.fi/)

Licensed under the [MIT license](LICENSE.txt)
