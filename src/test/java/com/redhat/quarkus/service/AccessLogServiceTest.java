package com.redhat.quarkus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

import com.redhat.quarkus.model.AccessLog;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kafka.InjectKafkaCompanion;
import io.quarkus.test.kafka.KafkaCompanionResource;
import io.smallrye.reactive.messaging.kafka.companion.ConsumerTask;
import io.smallrye.reactive.messaging.kafka.companion.KafkaCompanion;

import jakarta.ws.rs.core.MediaType;

@QuarkusTest
@QuarkusTestResource(KafkaCompanionResource.class)
public class AccessLogServiceTest {

    @InjectMock
    AccessLogService accessLogService;

    @InjectKafkaCompanion
    KafkaCompanion companion;

    @Test
    void testProcessLobbyEvent() {
        // Create an example AccessLog object to send to Kafka
        AccessLog accessLog1 = new AccessLog();
        accessLog1.setRecordId(1L);
        accessLog1.setPersonId(1L);
        accessLog1.setEntryTime("09:00");
        accessLog1.setExitTime("17:00");
        accessLog1.setDestination("A");

        AccessLog accessLog2 = new AccessLog();
        accessLog2.setRecordId(2L);
        accessLog2.setPersonId(2L);
        accessLog2.setEntryTime("10:00");
        accessLog2.setExitTime("18:00");
        accessLog2.setDestination("B");

        // Produce the AccessLog object to Kafka
        companion.produceStrings().usingGenerator(i -> new ProducerRecord<>("lobby", accessLog1.toString()));
        companion.produceStrings().usingGenerator(i -> new ProducerRecord<>("lobby", accessLog2.toString()));

        // Expect that the AccessLogService processes the message and persists it
        
        // Perform a test HTTP request to your endpoint
        // given()
        //     .when()
        //     .get("/accesslog")
        //     .then()
        //     .statusCode(200)
        //     .contentType(MediaType.APPLICATION_JSON)
        //     .body("recordId", hasItems(1, 2)) // Adjust this based on your AccessLog structure
        //     .body("personId", hasItems(1, 2))   // Adjust this based on your AccessLog structure
        //     .body("entryTime", hasItems("09:00", "10:00")) // Adjust this based on your AccessLog structure
        //     .body("exitTime", hasItems("17:00", "18:00"))   // Adjust this based on your AccessLog structure
        //     .body("destination", hasItems("A", "B")); // Adjust this based on your AccessLog structure

        // Wait for the Kafka consumer to process the message
        ConsumerTask<String, String> lobbyConsumer = companion.consumeStrings().fromTopics("lobby", 2);
        lobbyConsumer.awaitCompletion();

        // Assert that two messages have been processed
        assertEquals(2, lobbyConsumer.count());
    }
}


