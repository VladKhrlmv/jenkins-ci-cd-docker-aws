resource "aws_db_instance" "default" {
  identifier           = "cinema-db"
  allocated_storage    = 20
  storage_type         = "gp2"
  engine               = "postgres"
  engine_version       = "13.17"
  instance_class       = "db.t3.micro"
  db_name              = "cinema_db"
  username             = "postgres"
  password             = "password"
  publicly_accessible  = true
  skip_final_snapshot  = true
}

output "db_url" {
  value = "jdbc:postgresql://${aws_db_instance.default.address}:5432/cinema_db"
}
