# bloggy

use following command to start the bloggy container.
## docker run --network="host" --name bloggy-docker  -p 8081:8081  docker-bloggy


--network="host" lets the services to connect the host from inside the container.
 
 ## run elastic search with following command
 #### docker run  -p 9200:9200 -p 9300:9300 -v es-data:/usr/share/elasticsearch/data  -e "discovery.type=single-node" --name=elasticdb docker.elastic.co/elasticsearch/elasticsearch:7.6.0



### PENDING- configure the same in docker-compose.yml
