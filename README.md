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
  * retrieve by first and lastname	
```bash
./scripts/getByname.sh
```
  * retrieve one user by user id
```bash
./scripts/getByID.sh
```
  * delete the second value
```bash
curl http://localhost:8080/delete?id=2
```
## Redis CRUD indexing strategy
Very exciting that using the CRUD repository, a field in the java class with the Indexed annotation is treated as an index.
### User class
```bash
@RedisHash("user")
public class User {
	private @Id String id;
	private @Indexed String firstName;
	private String middleName;
	private @Indexed String lastName;
	private String roleName;
}
```
for a user with an id=1, This is stored in a Hash with a key of user:1
(this is stored in a hash and not in a json format but displaying in json)
```json
{"_class":"com.jphaugla.domain.User","id":"1","firstName":"Jason","middleName":"Paul","lastName":"Haugland","roleName":"CEO"}
```
Since firstName and lastName are indexed, they are added to a set key value for each index:
```bash
user:1:idx
	user:firstName:Jason
	user:lastName:Haugland
```
Then user:firstName:Jason is a set holding the user idx of each user with a first name of jason.  User 2 is Jason Smith so user 2 is in this set.
```bash
user:firstName:Jason
	1
	2
```
user:lastName:Haugland is a set hodle user idx of each user with a last name of Haugland.   User 5 is Caterhine Haugland so user 5 is in this set.
```bash
user:lastName:Haugland
	1
	5
```
Finally, user is a set of all the IDs
```bash
user
	1
	2
	5
```

