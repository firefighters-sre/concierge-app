package com.redhat.quarkus.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import jakarta.inject.Inject;

import com.redhat.quarkus.model.AccessLog;
import com.redhat.quarkus.model.MoveLog;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import io.restassured.http.ContentType;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.common.annotation.Identifier;

@QuarkusTest
public class AccessLogResourceTest {

  @Inject
  @Identifier("default-kafka-broker")
  Map<String, Object> kafkaConfig;

  KafkaConsumer<String, MoveLog> logConsumer;

  @BeforeEach
  void setUp() {
    logConsumer = new KafkaConsumer<>(consumerConfig(), new StringDeserializer(),
        new ObjectMapperDeserializer<>(MoveLog.class));
  }

  @AfterEach
  void tearDown() {
    logConsumer.close();
  }

  Properties consumerConfig() {
    Properties properties = new Properties();
    properties.putAll(kafkaConfig);
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-log-group-id");
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    return properties;
  }

  @Test
  void testAccess() {
    logConsumer.subscribe(Collections.singleton("entrance"));

    // Create an example AccessLog object to send to Kafka
    AccessLog logToSend = new AccessLog();
    logToSend.setRecordId(1L);
    logToSend.setPersonId(1L);
    logToSend.setEntryTime("09:00");
    logToSend.setExitTime("17:00");
    logToSend.setDestination("A");

    // Call the endpoint under test.
    given()
      .when()
      .contentType(ContentType.JSON)
      .body(logToSend)
      .post("/access")
      .then()
      .statusCode(204);

    ConsumerRecords<String, MoveLog> records = logConsumer.poll(Duration.ofMillis(10000));
    MoveLog receivedLog = records.records("entrance").iterator().next().value();

    assertEquals(logToSend.getPersonId(), receivedLog.getPersonId());
    assertEquals(logToSend.getDestination(), receivedLog.getDestination());
    assertEquals("elevator", receivedLog.getPreferredRoute());
  }
}
