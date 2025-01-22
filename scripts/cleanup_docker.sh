#!/bin/bash

# Remove all running and stopped containers
echo "Stopping and removing all Docker containers..."
docker ps -aq | xargs -r docker stop
docker ps -aq | xargs -r docker rm

# Remove all volumes
echo "Removing all Docker volumes..."
docker volume ls -q | xargs -r docker volume rm

# Remove all images
echo "Removing all Docker images..."
docker images -aq | xargs -r docker rmi -f

# Clean up Docker cache
echo "Pruning unused Docker objects (networks, dangling images, build cache)..."
docker system prune -af --volumes

echo "Docker cleanup complete!"
