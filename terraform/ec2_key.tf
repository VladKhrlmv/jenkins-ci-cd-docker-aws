resource "tls_private_key" "ec2_key" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "aws_key_pair" "ec2_key" {
  key_name   = "ec2-key"
  public_key = tls_private_key.ec2_key.public_key_openssh
}

resource "local_file" "private_key" {
  content  = tls_private_key.ec2_key.private_key_pem
  filename = "${path.module}/tmp/ec2-key.pem"
}

resource "null_resource" "set_private_key_permissions" {
  provisioner "local-exec" {
    command = "chmod 600 ${path.module}/tmp/ec2-key.pem"
  }

  depends_on = [local_file.private_key]
}