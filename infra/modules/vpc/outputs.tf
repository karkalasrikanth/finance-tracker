output "private_subnet_id" {
  value = aws_subnet.private_subnet.id
}

output "ecs_sg_id" {
  value = aws_security_group.ecs_sg.id
}