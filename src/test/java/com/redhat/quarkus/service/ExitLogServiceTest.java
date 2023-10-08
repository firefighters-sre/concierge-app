package com.redhat.quarkus.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import jakarta.inject.Inject;

import com.redhat.quarkus.model.ExitLog;
import com.redhat.quarkus.model.MoveLog;

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
public class ExitLogServiceTest {

    @Inject
    @Identifier("default-kafka-broker")
    Map<String, Object> kafkaConfig;

    KafkaProducer<String, MoveLog> logProducer;
    KafkaConsumer<String, ExitLog> logConsumer;

    @BeforeEach
    void setUp() {
        logConsumer = new KafkaConsumer<>(consumerConfig(), new StringDeserializer(), new ObjectMapperDeserializer<>(ExitLog.class));
        logProducer = new KafkaProducer<>(kafkaConfig, new StringSerializer(), new ObjectMapperSerializer<>());
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
    void testProcessExitEvent() {
        logConsumer.subscribe(Collections.singleton("external"));

        // Create an example MoveLog object to send to Kafka
        MoveLog logToSend = new MoveLog();
        logToSend.setDestination("OUT");
        logToSend.setPersonId(1L);
        logToSend.setPreferredRoute("elevator");

        logProducer.send(new ProducerRecord<>("exit", logToSend));

        ConsumerRecords<String, ExitLog> records = logConsumer.poll(Duration.ofMillis(10000));
        ExitLog receivedLog = records.records("external").iterator().next().value();
 
        assertEquals(logToSend.getPersonId(), receivedLog.getPersonId());
        assertEquals(logToSend.getDestination(), receivedLog.getDestination());
        assertNotNull(receivedLog.getRecordId());
        assertNotNull(receivedLog.getExitTime());
    }
}
