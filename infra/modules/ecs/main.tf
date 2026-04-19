resource "aws_ecs_cluster" "app_cluster" {
  name = "replenishment-cluster"
}

resource "aws_ecs_task_definition" "service" {
  family                   = "replenishment-service"
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  cpu                      = "512"
  memory                   = "1024"
  execution_role_arn       = ""
  container_definitions = jsonencode([
    {
      name  = "backend"
      image = "your-docker-image:latest"

      portMappings = [
        {
          containerPort = var.container_port
        }
      ]
      environment = [
        {
          name      = "DB_URL"
          valueFrom = var.db_host_name
        },
        {
          name      = "DB_USERNAME"
          valueFrom = var.db_username
        },
        {
          name      = "DB_PASSWORD"
          valueFrom = var.db_password
        }
      ]
    }
  ])
}

resource "aws_ecs_service" "backend_service" {
  name            = "replenishment-service"
  cluster         = aws_ecs_cluster.app_cluster.id
  task_definition = aws_ecs_task_definition.service.arn
  launch_type     = "FARGATE"
  desired_count   = var.desired_count
  network_configuration {
    subnets          = [var.subnet_id]
    security_groups  = [var.security_group_id]
    assign_public_ip = true
  }
}