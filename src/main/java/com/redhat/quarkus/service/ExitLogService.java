package com.redhat.quarkus.service;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;

import com.redhat.quarkus.model.ExitLog;
import com.redhat.quarkus.model.MoveLog;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExitLogService {
    
    private static final Logger LOG = Logger.getLogger(ExitLogService.class);

    @Incoming("exit")
    @Outgoing("external")
    @Blocking
    @Timed(name = "processExitEventTime", description = "Time taken to process an exit event.", unit = MetricUnits.MILLISECONDS)
    public ExitLog processExitEvent(MoveLog moveLog) throws InterruptedException {
        ExitLog exitLog = new ExitLog(moveLog.getPersonId(), moveLog.getDestination());
        LOG.infof(
            "Processing exit event [%s]: Person ID: %s, Exit Time: %s, Destination: %s",
            exitLog.getRecordId(),
            exitLog.getPersonId(),
            exitLog.getExitTime(),
            exitLog.getDestination()
        );
        return exitLog;
    }
}
