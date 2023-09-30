package com.redhat.quarkus.service;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.MediaType;

import com.redhat.quarkus.model.AccessLog;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

@QuarkusTest
public class AccessLogServiceTest {

    @InjectMock
    AccessLogService accessLogService;

    @Test
    public void testListAllAccessLogs() {
        // Prepare a list of AccessLog objects for mocking
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

        // Perform a test HTTP request to your endpoint
        given()
            .when()
            .get("/accesslog")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body("recordId", hasItems(1, 2)) // Adjust this based on your AccessLog structure
            .body("personId", hasItems(1, 2))   // Adjust this based on your AccessLog structure
            .body("entryTime", hasItems("09:00", "10:00")) // Adjust this based on your AccessLog structure
            .body("exitTime", hasItems("17:00", "18:00"))   // Adjust this based on your AccessLog structure
            .body("destination", hasItems("A", "B")); // Adjust this based on your AccessLog structure
    }
}


