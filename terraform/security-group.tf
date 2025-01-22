resource "aws_security_group" "my_security_group" {
  name = "my_sg"

  tags = {
    Name = "my_SG"
  }
}

resource "aws_security_group_rule" "allow_icmp" {
  type              = "ingress"
  security_group_id = aws_security_group.my_security_group.id
  from_port         = -1
  to_port           = -1
  protocol          = "icmp"
  cidr_blocks       = ["0.0.0.0/0"]

  description = "Allow ICMP (Ping) inbound traffic"
}

resource "aws_security_group_rule" "allow_postgres" {
  type              = "ingress"
  security_group_id = aws_security_group.my_security_group.id
  from_port         = 5432
  to_port           = 5432
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  description       = "Allow inbound traffic for PostgreSQL"
}

resource "aws_security_group_rule" "allow_jenkins" {
  type              = "ingress"
  security_group_id = aws_security_group.my_security_group.id
  from_port         = 8080
  to_port           = 8080
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]

  description = "Allow inbound traffic on port 8081 for Nexus"
}

resource "aws_vpc_security_group_ingress_rule" "allow_http" {
  security_group_id = aws_security_group.my_security_group.id
  cidr_ipv4         = "0.0.0.0/0"
  from_port         = 80
  ip_protocol       = "tcp"
  to_port           = 80
  tags = {
    Name = "Allow HTTP in"
  }
  
}

resource "aws_vpc_security_group_ingress_rule" "allow_ssh" {
  security_group_id = aws_security_group.my_security_group.id
  cidr_ipv4         = "0.0.0.0/0"
  from_port         = 22
  ip_protocol       = "tcp"
  to_port           = 22
  tags = {
    Name = "Allow SSH in"
  }
}

resource "aws_vpc_security_group_egress_rule" "allow_all" {
  security_group_id = aws_security_group.my_security_group.id
  cidr_ipv4         = "0.0.0.0/0"
  ip_protocol       = "-1"
  tags = {
    Name = "Allow all trafic out"
  }

}

