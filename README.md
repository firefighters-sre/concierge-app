# Concierge App

The Concierge App is a Quarkus-based application primarily designed for managing access logs and security-related data. Its core functionality revolves around processing events from specific Kafka topics, storing relevant information in the database, and managing unique identifiers for different entities like persons and access logs.

## Key Features

- **Event Processing**: The application listens to specific Kafka topics to capture and process relevant events.
- **Data Management**: It stores processed information in a structured manner within the database, ensuring data integrity and consistency.
- **Unique Identifier Generation**: For every new entity, be it a person or an access log, the application automatically generates a unique identifier ensuring smooth data retrieval.
## TODO List
- [X] **Process Kafka `AccesLog` in `lobby` Topic Events**: Capture and process events from the Kafka topic named `lobby`.
- [X] **Enhance `MoveLog` with `preferredRoute` Attribute**: Add a preferredRoute attribute to the MoveLog class.
- [X] **Deliver `MoveLog` to Kafka `entrance` Topic**: Send processed messages to the Kafka topic named `entrance`.
### 1.1
- [ ] **Monitoring & Alerting**: Set up monitoring tools to keep track of the app's performance and health.
- [ ] **Centralized Logging**: Integrate with a centralized logging system for better traceability.
- [ ] **API Documentation**: Document all exposed APIs and endpoints for better clarity.
- [ ] **Helm Chart Creation**: Design and implement a Helm chart for streamlined deployments of the `concierge-app` on Kubernetes clusters.
- [ ] **Store Access Log**: Save the access log entries into the database under the `AccessLog` table.
- [ ] **Retrieve Access Logs**: Implement functionality to fetch access logs from the `AccessLog` table.
- [ ] **Automatic AccessLog ID Generation**: For every new access log entry, generate a unique identifier automatically.
- [ ] **Process Kafka `registration` Topic Events**: Capture and process events from the Kafka topic named `registration`.
- [ ] **Store Person Information**: Save the person's data into the database under the `Person` table.
- [ ] **Automatic Person ID Generation**: For every new person's data, generate a unique identifier automatically.
- [ ] **Retrieve Person Information**: Implement functionality to fetch details about a person from the `Person` table.
- [ ] **Integration with Building and Mobility Apps**: Ensure seamless data flow between the Concierge App and the other microservices.

## Payload Example

Here's an example of a typical payload that the Concierge App expects:

```json
{
    "recordId": 123456,
    "personId": 12345,
    "entryTime": "2023-10-08T09:00:00Z",
    "exitTime": "2023-10-08T17:00:00Z",
    "destination": "5"
}
```
Here's an example of a typical payload that the Concierge App produces:

```json
{
    "personId": 12345,
    "destination": "5",
    "preferredRoute": "stairs"
}
```

This project uses Quarkus, the Supersonic Subatomic Java Framework.
If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/qrk-fire-archetype-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.
