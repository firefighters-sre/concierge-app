package com.redhat.quarkus.service;

import com.redhat.quarkus.model.AccessLog;
import com.redhat.quarkus.repository.AccessLogRepository;
import io.quarkus.panache.common.Sort;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class AccessLogService {

    @Inject
    AccessLogRepository accessLogRepository;

    @Incoming("lobby")
    @Outgoing("entrance")
    @Blocking
    public AccessLog processLobbyEvent(AccessLog accessLog) throws InterruptedException {
        // accessLogRepository.persist(accessLog);
        return accessLog;
    }

    public List<AccessLog> listAll() {
        return AccessLog.listAll(Sort.by("entryTime").ascending());
    }

    public AccessLog createAccessLog(AccessLog accessLog) {
        accessLogRepository.persist(accessLog);
        return accessLog;
    }
}
