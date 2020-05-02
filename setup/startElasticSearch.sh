#!/bin/bash

#pull docker image of elastic search by firinsg following command
# docker pull docker.elastic.co/elasticsearch/elasticsearch:7.6.0


echo "Running elastic searver.."

sudo docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.6.0
