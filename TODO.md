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
- [X] **Automatic AccessLog ID Generation**: For every new access log entry, generate a unique identifier automatically.
- [X] **API Documentation**: Document all exposed APIs and endpoints for better clarity.
- [X] **Health Endpoint Integration**: Integrated `smallrye-health` to provide health check endpoints for application monitoring.
### 1.0.3
- [X] **Implement Basic SLOs, SLAs, and Alerting**
  - [X] **Availability SLO**: Ensure 99.9% uptime over a 10-minute window and create Prometheus rules for alerting.
  - [X] **Latency SLO**: Ensure API response times are under 200ms and event processing times are within 500ms.
  - [X] **Error Rate SLO**: Ensure less than 0.1% of all API requests result in errors.
  - [X] **Availability SLA**: Implement a service credit system for downtime that falls below the agreed availability of 99.8% in a 10-minute window.
  - [X] **Latency SLA**: Implement a service credit for average response time exceeding 200ms for over an hour.
### 1.0.4
- [X] **SLO/SLA prevention Automation**: Implement automation routines to monitor and alert on SLO/SLA disruption. 
  - [X] **Automated Scaling**:
    - [X] **Horizontal Pod Autoscaling (HPA)**: Dynamically scale the number of running pods based on observed CPU utilization or other select metrics.
  - [X] **Self-Healing Systems**:
    - [X] **Liveness and Readiness Probes**: Implement probes to check the health of the application and restart pods that are not responsive.
    - [X] **PodDisruptionBudget (PDB)**: Ensure high availability during voluntary disruptions by defining the minimum available replicas.
### 1.0.5
- [X] **Define Estimated Evacuation Time Formula**: Develop a formula to predict the estimated evacuation time based on the current occupancy of the building and the processing capacity of the `concierge_app`.
- [ ] **Implement SLOs, SLAs, and Alerts related to the fire incident**:
  - [X] **Evacuation Time SLO**: Monitor evacuation time ensuring it's below 1 minute during fire incidents.
  - [X] **Evacuation Time SLA**: Trigger review if average evacuation time exceeds 5 minutes.
  evacuation protocol.

### 1.0.6
- [ ] **SLO/SLA fire incident prevention Automation**:
  - [ ] Some task related to these SLOs: Escalate the application to to reduce the evacuation time
- [ ] **Custom Metrics Scaling**: Implement scaling based on custom application-specific metrics.
- [ ] **KEDA**:

### Backlog
- [ ] **Automated Evacuation Drills**: Schedule and automate periodic fire drills to ensure the readiness of all individuals and validate the effectiveness of evacuation protocols.
- [ ] **Exit Monitoring Automation**: Implement sensors and cameras to monitor exit routes and ensure they remain clear. The system should automatically raise alerts if any blockages or obstructions are detected.
- [ ] **Capacity Forecasting**: Based on the number of individuals in the building, predict potential choke points during evacuation and adjust exit strategies accordingly.
- [ ] **Capacity Planning & Resource Provisioning Automation**: Predict system resource constraints based on metrics (disk usage, memory consumption,CPU utilization) and automate provisioning processes accordingly. 
- [ ] **Exit Accessibility SLO**: Ensure that at least 90% of exits are accessible and clear during a fire incident.
- [ ] **Exit Accessibility SLA**: Regular checks on exit routes to ensure they are clear. If more than 10% of exits are found blocked during a check,  a review of the premises' maintenance protocol will be conducted. 
- [ ] **Data Availability SLO**: Ensure 99.99% uptime for the data retrieval system, especially during emergencies, to provide real-time data on individual whereabouts.
- [ ] **Data Availability SLA**: Implement a backup and recovery mechanism to ensure that data is always available, especially during emergencies. Any downtime beyond 0.01% will require a review of data systems.
- [ ] **Error Budget Automation**: Implement automation routines to monitor and alert on Error Budget consumption. This includes:
  - [ ] Automated reporting on Error Budget consumption.
  - [ ] Proactive alerts when approaching the Error Budget limit.
  - [ ] Triggering automated remediation actions if certain thresholds are crossed (e.g., rolling back a deployment).
  - [ ] Integration with CI/CD pipelines to halt new releases if the Error Budget is exceeded.
- [ ] **Throughput SLO**: Ensure the system handles at least 1000 API requests and processes at least 500 events per second.
- [ ] **Data Accuracy SLO**: Ensure less than 0.01% discrepancy between input events and processed events' data.  
- [ ] **Support Response Time SLA**: Implement a service credit system for failure to meet response times for various priority issues.
- [ ] **Data Recovery SLA**: Implement a service credit for recovery times exceeding 4 hours.
- [ ] **Maintenance Notification SLA**: Implement a service credit for failure to notify about maintenance 72 hours in advance.
- [ ] **Service Restoration SLA**: Implement a service credit for service restoration times exceeding 2 hours..
- [ ] **Stairs**: Add stairs as preferredRoute.
- [ ] **Monitoring**: Set up monitoring tools to keep track of the app's performance and health.
   - [ ] **Current Evacuation Status**: A gauge that can be set to 1 during an active evacuation and 0 otherwise. This helps in easily determining if an evacuation is in progress.
  - [ ] **Evacuation Completion Time**: A timer to measure the total time taken from the start of the evacuation until the building is confirmed empty.
- [ ] **Logging**: Set up monitoring tools to keep track of the app's performance and health.
  - [ ] **Evacuation Start**: A log entry indicating the time and reason (in this case, a fire drill) when the evacuation started.
  - [ ] **Evacuation End**: A log entry indicating the time when the evacuation was completed and the building was empty.
  - [ ] **Any Anomalies**: Log entries capturing any anomalies or issues during the evacuation, such as access system malfunctions, entry/exit bottlenecks, etc.
- [ ] **Centralized Logging**: Integrate with a centralized logging system for better traceability.
- [ ] **Store Access Log**: Save the access log entries into the database under the `AccessLog` table.
- [ ] **Retrieve Access Logs**: Implement functionality to fetch access logs from the `AccessLog` table.
- [ ] **Process Kafka `registration` Topic Events**: Capture and process events from the Kafka topic named `registration`.
- [ ] **Store Person Information**: Save the person's data into the database under the `Person` table.
- [ ] **Automatic Person ID Generation**: For every new person's data, generate a unique identifier automatically.
- [ ] **Retrieve Person Information**: Implement functionality to fetch details about a person from the `Person` table.
- [ ] **Integration with Building and Mobility Apps**: Ensure seamless data flow between the Concierge App and the other microservices.
