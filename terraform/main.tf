terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
    }
  }
}

provider "aws" {
  region = var.aws_region
}

module "frontend" {
  source = "./frontend"
}

module "backend" {
  source = "./backend"
}
