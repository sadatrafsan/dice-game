# How To Run
use docker to create image and run with image id.
URL: localhost:8080

# ENDPOINT
Start Game: http://localhost:8080/api/game [POST]
Scores: http://localhost:8080/api/game/scores/{gameId} [GET]
Player List: http://localhost:8080/api/players [GET]
Create New Player: http://localhost:8080/api/players [POST]


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