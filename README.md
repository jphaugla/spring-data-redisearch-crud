# spring-data-redisearch-crud
Provides a quick-start example of using Redisearch with springBoot and the crud repository. 
This is based on work in this repository
https://github.com/rnbWarden/jredisearch-spring-boot-starter

## Overview
In this tutorial, a java spring boot application is run through a jar file to support typical API calls to a REDISearch data layer.  A redisearch docker configuration is included.

## Requirements
* Docker installed on your local system, see [Docker Installation Instructions](https://docs.docker.com/engine/installation/).

* When using Docker for Mac or Docker for Windows, the default resources allocated to the linux VM running docker are 2GB RAM and 2 CPU's. Make sure to adjust these resources to meet the resource requirements for the containers you will be running. More information can be found here on adjusting the resources allocated to docker.

[Docker for mac](https://docs.docker.com/docker-for-mac/#advanced)

[Docker for windows](https://docs.docker.com/docker-for-windows/#advanced)

* Must use Java 11 to work with com/rnbwarden/redisearch


## Links that help!

[spring data for redis github](https://github.com/spring-projects/spring-data-examples/tree/master/redis/repositories)
[spring data for redis sample code](https://www.oodlestechnologies.com/blogs/Using-Redis-with-CrudRepository-in-Spring-Boot/)
[macos java, use the home brew sections](https://stackoverflow.com/questions/26252591/mac-os-x-and-multiple-java-versions)
## Getting Started
1. Prepare Docker environment-see the Prerequisites section above...
2. Pull this github into a directory
```bash
git clone https://github.com/jphaugla/spring-data-redisearch-crud.git
```
3. Refer to the notes for redis Docker images used but don't get too bogged down as docker compose handles everything except for a few admin steps on tomcat.
 * [https://hub.docker.com/r/bitnami/redis/](https://hub.docker.com/r/bitnami/redis/)  
4. Open terminal and change to the github home where you will see the docker-compose.yml file, then: 
```bash
docker-compose up -d
```


## The spring java code

The java code demonstrates common API actions with the data persisted in REDIS.  The java spring Boot framework mminimizes the amount of code to build and maintain this solution.  Maven is used to build the java code and the code is deployed to the tomcat server.

## To execute the code
(Alternatively, this can be run through intelli4j)
1. Complile the code
```bash
mvn package
```
2.  run the jar file.   
```bash
java -jar target/redis-0.0.1-SNAPSHOT.jar
```
3.  Test the application from a separate terminal window
  * save some "in-code" values
```bash
curl http://localhost:8080/save_product
```
  * verify using redis-cli product is in redis
```bash
redis-cli
ft.search product "@brand:{ NIKE }"
```
  * retrieve by article name
```bash
curl 'http://localhost:8080/get_Article/?article=FALCON01'
```
  * delete the second value
```bash
curl -X DELETE http://localhost:8080/delete?id=id123
```
  * add a product using json input
```bash
./scripts/postProduct.sh
```
  * other scripts are available in the scripts directory and have documentation
