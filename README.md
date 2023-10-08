# Concierge App

The Concierge App is a Quarkus-based application primarily designed for managing access logs and security-related data. Its core functionality revolves around processing events from specific Kafka topics, storing relevant information in the database, and managing unique identifiers for different entities like persons and access logs.

## Key Features

- **Event Processing**: The application listens to specific Kafka topics to capture and process relevant events.
- **Data Management**: It stores processed information in a structured manner within the database, ensuring data integrity and consistency.
- **Unique Identifier Generation**: For every new entity, be it a person or an access log, the application automatically generates a unique identifier ensuring smooth data retrieval.

## API Endpoints

The Concierge App provides a set of RESTful API endpoints for interaction:

- **`POST /access`**: 
  - **Description**: Receives an access log (entry event) and processes it.
  - **Payload**: 
    ```json
    {
        "personId": 12345,
        "destination": "5"
    }
    ```
  - **Response**: 204 No Content (on successful processing)

- **`POST /exit`**:
  - **Description**: Receives an exit log and processes it.
  - **Payload**: 
    ```json
    {
        "personId": 12345,
        "destination": "OUT",
        "preferredRoute": "elevator"
    }
    ```
  - **Response**: 204 No Content (on successful processing)

## Event Processing

In the Concierge App, event processing is a crucial feature, especially when dealing with real-time data streams. Here's a breakdown of the Kafka topics the app interacts with:

#### Input Topics:
- **`lobby`**: This topic captures entry-related events. The `AccessLogService` processes these events and then sends the processed data to the `entrance` topic.
- **`exit`**: This topic captures exit-related events. The `ExitLogService` processes these events and then sends the processed data to the `external` topic.

#### Output Topics:
- **`entrance`**: Once the entry events from the `lobby` topic are processed, the data is sent here.
- **`external`**: Once the exit events from the `exit` topic are processed, the data is sent here.

## Monitoring and Metrics 📊

You can access the captured metrics in real-time by navigating to the endpoint `/q/metrics`.

### Key Metrics:

#### 1. **AccessLogResource Metrics**:

- **`processLobbyPostTime`**:
  - **Description**: Measures the time taken to process a lobby POST call in the `AccessLogResource` class.
  - **Metrics**:
    - **count**: Number of times the method has been invoked.
    - **sum**: Total time taken for all invocations.
    - **max**: Maximum time taken for a single invocation of the method.

#### 2. **AccessLogService Metrics**:

- **`processLobbyEventTime`**:
  - **Description**: Measures the time taken to process a lobby event in the `AccessLogService` class.
  - **Metrics**:
    - **count**: Number of times the method has been invoked.
    - **sum**: Total time taken for all invocations.
    - **max**: Maximum time taken for a single invocation of the method.

#### 3. **ExitLogResource Metrics**:

- **`processExitPostTime`**:
  - **Description**: Measures the time taken to process an exit POST call in the `ExitLogResource` class.
  - **Metrics**:
    - **count**: Number of times the method has been invoked.
    - **sum**: Total time taken for all invocations.
    - **max**: Maximum time taken for a single invocation of the method.

#### 4. **ExitLogService Metrics**:

- **`processExitEventTime`**:
  - **Description**: Measures the time taken to process an exit event in the `ExitLogService` class.
  - **Metrics**:
    - **count**: Number of times the method has been invoked.
    - **sum**: Total time taken for all invocations.
    - **max**: Maximum time taken for a single invocation of the method.

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

This project uses Quarkus, the Supersonic Subatomic Java Framework.
If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .
