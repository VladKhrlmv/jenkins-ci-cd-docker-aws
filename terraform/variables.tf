variable "aws_region" {
  description = "The AWS region to deploy resources"
  default     = "us-east-1"
}

variable "instance_type" {
  description = "EC2 instance type"
  default     = "t2.micro"
}

variable "build_instance_name" {
  description = "Name tag for the BUILD instance"
  default     = "build-machine"
}

variable "jenkins_instance_name" {
  description = "Name tag for the jenkins EC2 instance"
  type        = string
  default     = "jenkins-machine"
}
