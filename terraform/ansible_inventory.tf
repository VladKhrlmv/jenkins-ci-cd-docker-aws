resource "local_file" "ansible_inventory" {
    depends_on = [
    terraform_data.ssh_connection_for_build_machine,
    terraform_data.ssh_connection_for_jenkins_machine
  ]

  content = <<-EOT
    [build_machines]
    ${aws_instance.build_machine.public_ip} ansible_user=ubuntu ansible_ssh_private_key_file=/workspaces/ubuntu/lab10/terraform/tmp/ec2-key.pem

    [jenkins_machines]
    ${aws_instance.jenkins_machine.public_ip} ansible_user=ubuntu ansible_ssh_private_key_file=/workspaces/ubuntu/lab10/terraform/tmp/ec2-key.pem
  EOT
  filename = "${path.module}/ansible/inventory.ini"
}
