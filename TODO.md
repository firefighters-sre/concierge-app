# Concierge App

The Concierge App is a Quarkus-based application primarily designed for managing access logs and security-related data. Its core functionality revolves around processing events from specific Kafka topics, storing relevant information in the database, and managing unique identifiers for different entities like persons and access logs.

## TODO List
### 1.0
- [X] **Process Kafka `AccesLog` in `lobby` Topic Events**: Capture and process events from the Kafka topic named `lobby`.
- [X] **Enhance `MoveLog` with `preferredRoute` Attribute**: Add a preferredRoute attribute to the MoveLog class.
- [X] **Deliver `MoveLog` to Kafka `entrance` Topic**: Send processed messages from `lobby` to the Kafka topic named `entrance`.
### 1.0.1
- [X] **Monitoring**: Set up monitoring tools to keep track of the app's performance and health.
  - [X] **Entrance Rate**: A meter to measure the rate of people entering the building.
  - [X] **Exit Rate** A meter to measure the rate of people exiting the building during the evacuation.
- [X] **Logging**: Set up monitoring tools to keep track of the app's performance and health.
  - [X] **Person Entry**: A log entry every time a person enters the building.
  - [X] **Person Exit**: A log entry every time a person exits the building during the evacuation.
- [X] **Helm Chart Creation**: Design and implement a Helm chart for streamlined deployments of the `concierge-app` on Kubernetes clusters.
### 1.0.2
- [X] **Process Kafka `MoveLog` in `exit` Topic Events**: Capture and process events from the Kafka topic named `exit`.
- [X] **Deliver `ExitLog` to Kafka `external` Topic**: Send processed messages from `exit` to the Kafka topic named `external`.
### 1.0.3
- [ ] **SLOs and SLAs**: Define and implement Service Level Objectives (SLOs) and Service Level Agreements (SLAs) for the access services.
- [ ] **Alerting**: Set up alert tools to keep track of the app's performance and health.
### 1.1
- [ ] **Stairs**: Add stairs as preferredRoute.
- [ ] **Monitoring**: Set up monitoring tools to keep track of the app's performance and health.
   - [ ] **Current Evacuation Status**A gauge that can be set to 1 during an active evacuation and 0 otherwise. This helps in easily determining if an evacuation is in progress.
  - [ ] **Evacuation Completion Time**: A timer to measure the total time taken from the start of the evacuation until the building is confirmed empty.
- [ ] **Logging**: Set up monitoring tools to keep track of the app's performance and health.
  - [ ] **Evacuation Start**: A log entry indicating the time and reason (in this case, a fire drill) when the evacuation started.
  - [ ] **Evacuation End**: A log entry indicating the time when the evacuation was completed and the building was empty.
  - [ ] **Any Anomalies**: Log entries capturing any anomalies or issues during the evacuation, such as access system malfunctions, entry/exit bottlenecks, etc.
- [ ] **Centralized Logging**: Integrate with a centralized logging system for better traceability.
- [ ] **API Documentation**: Document all exposed APIs and endpoints for better clarity.
- [ ] **Store Access Log**: Save the access log entries into the database under the `AccessLog` table.
- [ ] **Retrieve Access Logs**: Implement functionality to fetch access logs from the `AccessLog` table.
- [ ] **Automatic AccessLog ID Generation**: For every new access log entry, generate a unique identifier automatically.
- [ ] **Process Kafka `registration` Topic Events**: Capture and process events from the Kafka topic named `registration`.
- [ ] **Store Person Information**: Save the person's data into the database under the `Person` table.
- [ ] **Automatic Person ID Generation**: For every new person's data, generate a unique identifier automatically.
- [ ] **Retrieve Person Information**: Implement functionality to fetch details about a person from the `Person` table.
- [ ] **Integration with Building and Mobility Apps**: Ensure seamless data flow between the Concierge App and the other microservices.
