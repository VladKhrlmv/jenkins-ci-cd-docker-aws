#!/bin/bash

set -a
source ../.env
set +a

# Docker variables
IMAGE="$DOCKERHUB_USERNAME/$DOCKERHUB_IMAGE_NAME"
TAG="${1:-latest}"
APP_VERSION="${2:-build}"

# Database variables
DB_CONTAINER_NAME="postgres_db"
DB_PORT="${DB_PORT:-5432}"
DB_USERNAME="${DB_USERNAME}"
DB_PASSWORD="${DB_PASSWORD}"

# Java variables
JAVA_CONTAINER_NAME="java_app"
DEFAULT_JAVA_SERVER_PORT=8080

if [ "$APP_VERSION" = "test" ]; then
  DB_NAME="${DB_NAME_TEST}"
else
  DB_NAME="${DB_NAME_BUILD}"
fi
echo "DB_NAME: $DB_NAME"

NETWORK_NAME="app_network"

if [ -z "$(docker network ls --filter name=^${NETWORK_NAME}$ --format="{{ .Name }}")" ]; then
  echo "Creating Docker network: $NETWORK_NAME"
  docker network create $NETWORK_NAME
else
  echo "Docker network $NETWORK_NAME already exists."
fi

if [ -z "$(docker ps -q -f name=${DB_CONTAINER_NAME})" ]; then
  echo "Starting PostgreSQL container..."
  docker run -d \
    --name $DB_CONTAINER_NAME \
    --network $NETWORK_NAME \
    -e POSTGRES_USER=$DB_USERNAME \
    -e POSTGRES_PASSWORD=$DB_PASSWORD \
    -e POSTGRES_DB=$DB_NAME \
    -p $DB_PORT:$DB_PORT \
    postgres:13
else
  echo "PostgreSQL container is already running."
fi

echo "Waiting for PostgreSQL to be ready..."
until docker exec $DB_CONTAINER_NAME pg_isready -U $DB_USERNAME; do
    echo "PostgreSQL is not ready yet. Retrying in 2 seconds..."
    sleep 2
done
echo "PostgreSQL is ready."

if [ -z "$(docker ps -q -f name=${JAVA_CONTAINER_NAME})" ]; then
  echo "Starting Java application container..."
  docker run -d \
    --name $JAVA_CONTAINER_NAME \
    --network $NETWORK_NAME \
    -p $DEFAULT_JAVA_SERVER_PORT:$DEFAULT_JAVA_SERVER_PORT \
    -e SERVER_PORT=$DEFAULT_JAVA_SERVER_PORT \
    -e DB_HOST=$DB_CONTAINER_NAME \
    -e DB_PORT=$DB_PORT \
    -e DB_NAME=$DB_NAME \
    -e SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME \
    -e SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD \
    -e MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE=$MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE \
    $IMAGE:$TAG
else
  echo "Java application container is already running."
fi

echo "Deployment completed successfully!"
