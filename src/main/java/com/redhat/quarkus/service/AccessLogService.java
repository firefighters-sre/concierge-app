package com.redhat.quarkus.service;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;

import com.redhat.quarkus.model.AccessLog;
import com.redhat.quarkus.model.MoveLog;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccessLogService {
    
    private static final Logger LOG = Logger.getLogger(AccessLogService.class);

    @Incoming("lobby")
    @Outgoing("entrance")
    @Blocking
    @Timed(name = "processLobbyEventTime", description = "Time taken to process a lobby event.", unit = MetricUnits.MILLISECONDS)
    public MoveLog processLobbyEvent(AccessLog accessLog) throws InterruptedException {
        MoveLog moveLog = new MoveLog();
        moveLog.setDestination(accessLog.getDestination());
        moveLog.setPersonId(accessLog.getPersonId());
        moveLog.setPreferredRoute("elevator");
        LOG.infof(
            "Processing lobby event: Person ID: %s, Entry Time: %s, Destination: %s",
            accessLog.getPersonId(),
            accessLog.getEntryTime(),
            accessLog.getDestination()
        );
        return moveLog;
    }

}
