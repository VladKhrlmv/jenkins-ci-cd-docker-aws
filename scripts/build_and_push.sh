#!/bin/bash

IMAGE="$DOCKERHUB_USERNAME/cinema_test"

DATE_TAG=$(date +%Y-%m-%d-%H-%M)
LATEST_TAG="latest"

echo "$DOCKER_PAT_TOKEN" | docker login --username "$DOCKERHUB_USERNAME" --password-stdin

if [ $? -ne 0 ]; then
  echo "Docker login failed."
  exit 1
else
  echo "Docker login succeeded."
fi

if [ ! -d "../$GITHUB_REPO" ]; then
  echo "Cloning repository: $GITHUB_OWNER/$GITHUB_REPO"
  git clone https://${GITHUB_TOKEN}@github.com/${GITHUB_OWNER}/${GITHUB_REPO}.git ../$GITHUB_REPO
else
  echo "Repository already cloned."
fi

cd ../$GITHUB_REPO/app || { echo "Failed to navigate to $GITHUB_REPO/app"; exit 1; }

echo "Building Docker image: $IMAGE:$DATE_TAG"
docker build -t $IMAGE:$DATE_TAG .

echo "Tagging image as latest: $IMAGE:$LATEST_TAG"
docker tag $IMAGE:$DATE_TAG $IMAGE:$LATEST_TAG

echo "Pushing image: $IMAGE:$DATE_TAG"
docker push $IMAGE:$DATE_TAG

echo "Pushing image: $IMAGE:$LATEST_TAG"
docker push $IMAGE:$LATEST_TAG

cd ../..
rm -rf $GITHUB_REPO

echo "Build and push completed successfully!"
