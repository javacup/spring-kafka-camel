# Apache Camel Kafka Example

This application is a Spring boot example for connecting Apache Camel to Apache
Kafka for Producing and Consuming messages.

## Running

To get the application going:
```
$ mvn clean install # Build the project
$ run App.java as a java application and spring boot will take it up from there
$ 
```

## Messaging with Kafka

Run the API and hit /message with a GET

Run the API and hit /message with a POST body of JSON:
```
{
  "title": "Simple Notification",
  "body": "The simple notification body!"
}
```
