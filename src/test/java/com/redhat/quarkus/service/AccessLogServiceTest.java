package com.redhat.quarkus.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import jakarta.inject.Inject;

import com.redhat.quarkus.model.AccessLog;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import io.quarkus.kafka.client.serialization.ObjectMapperSerializer;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.common.annotation.Identifier;

@QuarkusTest
public class AccessLogServiceTest {

    @Inject
    @Identifier("default-kafka-broker")
    Map<String, Object> kafkaConfig;

    KafkaProducer<String, AccessLog> logProducer;
    KafkaConsumer<String, AccessLog> logConsumer;

    @BeforeEach
    void setUp() {
        logConsumer = new KafkaConsumer<>(consumerConfig(), new StringDeserializer(), new ObjectMapperDeserializer<>(AccessLog.class));
        logProducer = new KafkaProducer<>(kafkaConfig, new StringSerializer(), new ObjectMapperSerializer());
    }

    @AfterEach
    void tearDown() {
        logProducer.close();
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
    void testProcessLobbyEvent() {
        logConsumer.subscribe(Collections.singleton("entrance"));

        // Create an example AccessLog object to send to Kafka
        AccessLog logToSend = new AccessLog();
        logToSend.setRecordId(1L);
        logToSend.setPersonId(1L);
        logToSend.setEntryTime("09:00");
        logToSend.setExitTime("17:00");
        logToSend.setDestination("A");

        logProducer.send(new ProducerRecord<>("lobby", logToSend));

        ConsumerRecords<String, AccessLog> records = logConsumer.poll(Duration.ofMillis(10000));
        AccessLog receivedLog = records.records("entrance").iterator().next().value();

        assertEquals(logToSend.getRecordId(), receivedLog.getRecordId()); 
        assertEquals(logToSend.getPersonId(), receivedLog.getPersonId()); 
        assertEquals(logToSend.getEntryTime(), receivedLog.getEntryTime()); 
        assertEquals(logToSend.getExitTime(), receivedLog.getExitTime()); 
        assertEquals(logToSend.getDestination(), receivedLog.getDestination()); 
    }
}
