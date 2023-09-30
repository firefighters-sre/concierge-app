package com.redhat.quarkus.repository;

import com.redhat.quarkus.model.AccessLog;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccessLogRepository implements PanacheMongoRepository<AccessLog> {
}