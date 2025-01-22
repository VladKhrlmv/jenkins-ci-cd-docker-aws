output "frontend_bucket_url" {
  value = module.frontend.bucket_url
}

output "backend_endpoint" {
  value = module.backend.beanstalk_endpoint
}