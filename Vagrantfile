# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = 'centos-7.2-x64_64-solitabase-virtualbox'
  config.vm.box_url = 'http://images.solitacloud.fi/Centos+7/centos-7.2.1511_build_2-x86_64.box'

  config.vm.network 'private_network', type: 'dhcp'
  config.vm.network 'forwarded_port', guest: 8080, host: 8080

  config.vm.synced_folder '~/solita-cd/hello-java', '/hello-java'

  config.vm.provision 'ansible' do |ansible|
    ansible.playbook = 'site.yml'
    ansible.groups = {
      'jenkins' => ['default'],
      'jenkins:vars' => {'app_path' => '/hello-java',
                         'ansible_path' => '/vagrant',
                         'qa_inventory' => 'environments/local/inventory',
                         'prod_inventory' => 'environments/local/inventory',
                         'solita_jenkins_security_realm' => 'none'},
      'app' => ['default']
    }
  end
end
