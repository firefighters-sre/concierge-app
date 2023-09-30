package com.redhat.quarkus.service;

import com.redhat.quarkus.model.AccessLog;
import com.redhat.quarkus.repository.AccessLogRepository;
import io.quarkus.panache.common.Sort;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class AccessLogService {

    @Inject
    AccessLogRepository accessLogRepository;

    @Incoming("lobby")
    @Blocking
    public void processLobbyEvent(AccessLog accessLog) {
        accessLogRepository.persist(accessLog);
    }

    public List<AccessLog> listAll() {
        return AccessLog.listAll(Sort.by("entryTime").ascending());
    }
}
