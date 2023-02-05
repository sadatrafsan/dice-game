# Docker Demo Project
docker -v docker images

## Create Image
docker build -t demo-1 .

## Run image in a container
docker run -p 8080:8080 image_id

## Delete image
docker image rm -f image_id

## Check containers
docker ps -a (details) docker ps -aq (container id)

## Start/Stop containers
docker stop container_id docker start container_id

## Delete a container
docker rm -f container_id