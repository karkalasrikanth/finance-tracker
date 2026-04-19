variable "aws_region" {
  default = ""
}
variable "db_username" {}
variable "db_password" {
  type      = string
  sensitive = true
}
