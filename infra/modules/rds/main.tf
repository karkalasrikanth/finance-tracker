resource "aws_db_instance" "replenishment_db" {
  identifier        = "replenishment-db"
  engine            = "postgres"
  engine_version    = "15"
  instance_class    = "db.t3.micro"
  allocated_storage = 20
  db_name           = "replenishment"
  username          = var.db_username
  password          = var.db_password

  publicly_accessible = false
  skip_final_snapshot = true
}