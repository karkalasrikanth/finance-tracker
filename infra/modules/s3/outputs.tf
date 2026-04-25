output "cloudfront_url" {
  value = aws_cloudfront_distribution.ui.domain_name
}

output "s3_bucket_name" {
  value = aws_s3_bucket.finance_ui.bucket
}

output "cloudfront_distribution_id" {
  value = aws_cloudfront_distribution.ui.id
}