resource "null_resource" "ansible_provisioner" {
  depends_on = [
    terraform_data.ssh_connection_for_build_machine,
    terraform_data.ssh_connection_for_jenkins_machine,
    local_file.ansible_inventory
  ]

  provisioner "local-exec" {
    working_dir = "ansible/"
    command     = "ansible-playbook build_machine.yml"
  }

  provisioner "local-exec" {
    working_dir = "ansible/"
    command     = "ansible-playbook jenkins_machine.yml"
  }
}
