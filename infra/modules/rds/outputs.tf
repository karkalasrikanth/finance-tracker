output "db_endpoint" {
  description = "Full database endpoint (host:port)"
  value       = aws_db_instance.replenishment_db.endpoint
}