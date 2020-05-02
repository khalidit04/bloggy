# bloggy
test project to understand the spring boot and elastic search + docker

dowload docker image of elastic search
    docker pull docker.elastic.co/elasticsearch/elasticsearch:7.6.0


run docker image with following command
    sudo docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.6.0

