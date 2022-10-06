# Spring Kafka POC

A proof of Concept project to produce and consume events using Kafka.

## Docker

The project uses docker to containerize Kafka and Zookeeper and easily make them available out of
the box in your machine. To initiate run the command below:

``` shell 
docker compose -f docker-compose.yml up -d 
```

## Spring Kafka Producer

This module is in charge of exposing an endpoint to receive post request for events. Once the
request is received, the event is immediately published in the designated kafka topic. To start this
spring boot project, run this command for root of the project:

```shell
mvn clean install
cd spring-kafka-producer
mvn spring-boot:run 
```

## Spring Kafka Consumer

This module is in charge of listening events published in the project's topic. To start this
spring boot project, run this command for root of the project:

```shell
cd spring-kafka-consumer
mvn spring-boot:run 
```

## Posting events

Now that both projects are running, execute the command below:

```shell
curl  --request POST 'http://localhost:8080/events' \
      --header 'Content-Type: application/json' \
      --data-raw '{
        "name": "Castro",
        "created": "2022-10-04T11:48:00"
      }'
```