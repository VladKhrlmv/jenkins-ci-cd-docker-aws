resource "aws_instance" "build_machine" {
  ami                         = data.aws_ami.ubuntu.id
  instance_type               = var.instance_type
  vpc_security_group_ids      = [aws_security_group.my_security_group.id]
  key_name                    = aws_key_pair.ec2_key.key_name

  tags = {
    Name = var.build_instance_name
  }
}

resource "terraform_data" "ssh_connection_for_build_machine" {
  provisioner "remote-exec" {
    inline = [
      "echo \"$HOSTNAME connected...\""
    ]
  }

  connection {
    type        = "ssh"
    user        = "ubuntu"
    private_key = tls_private_key.ec2_key.private_key_pem
    host        = aws_instance.build_machine.public_ip
  }
}

output "build_machine_ip_address" {
  value = aws_instance.build_machine.public_ip
}

output "build_machine_dns_address" {
  value = aws_instance.build_machine.public_dns
}