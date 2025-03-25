#!/bin/bash

docker start neo4j ||
docker run -d --name neo4j \
  --publish=7474:7474 \
  --publish=7687:7687 \
  --volume=$HOME/neo4j/data:/data \
  --env NEO4J_AUTH=neo4j/secret \
  --env NEO4J_PLUGINS='["apoc"]' \
  neo4j:2025.02.0

