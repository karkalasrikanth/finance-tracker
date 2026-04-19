module "vpc" {
  source = "./modules/vpc"
}

module "rds" {
  source      = "./modules/rds"
  db_password = var.db_password
  db_username = var.db_username
}

module "ecr" {
  source = "./modules/ecr"
}
module "ecs" {
  source            = "./modules/ecs"
  subnet_id         = module.vpc.private_subnet_id
  security_group_id = module.vpc.ecs_sg_id
  container_image   = ""
  db_username       = var.db_username
  db_password       = var.db_password
  db_host_name      = module.rds.db_endpoint
}