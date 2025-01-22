resource "aws_s3_bucket" "frontend_bucket" {
  bucket        = "${var.app_name}-frontend"
  force_destroy = true
}

resource "aws_s3_bucket_website_configuration" "frontend_website" {
  bucket = aws_s3_bucket.frontend_bucket.id

  index_document {
    suffix = "index.html"
  }

  error_document {
    key = "index.html"
  }
}

resource "aws_s3_bucket_public_access_block" "frontend_public_access" {
  bucket = aws_s3_bucket.frontend_bucket.id

  block_public_acls       = false
  block_public_policy     = false
  ignore_public_acls      = false
  restrict_public_buckets = false
}

# resource "null_resource" "build_and_sync" {
#   provisioner "local-exec" {
#     command = <<EOT
#       aws s3 sync ../../frontend s3://${aws_s3_bucket.frontend_bucket.id} --delete
#     EOT
#   }
# }

output "bucket_url" {
  value = aws_s3_bucket.frontend_bucket.website_endpoint
}
