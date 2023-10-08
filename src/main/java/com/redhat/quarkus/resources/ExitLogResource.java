package com.redhat.quarkus.resources;

import java.util.concurrent.CompletionStage;

import com.redhat.quarkus.model.ExitLog;
import com.redhat.quarkus.model.MoveLog;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/exit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExitLogResource {

    @Inject
    @Channel("external-rest")
    Emitter<ExitLog> exitEmitter;

    private static final Logger LOG = Logger.getLogger(ExitLogResource.class);

    @POST
    @Timed(name = "processExitPostTime", description = "Time taken to process an exit POST call.", unit = MetricUnits.MILLISECONDS)
    public void processExitEvent(MoveLog moveLog) {
      ExitLog exitLog = new ExitLog(moveLog.getPersonId(), moveLog.getDestination());
      LOG.infof(
            "Processing exit event [%s]: Person ID: %s, Exit Time: %s, Destination: %s",
            exitLog.getRecordId(),
            exitLog.getPersonId(),
            exitLog.getExitTime(),
            exitLog.getDestination()
        );
        CompletionStage<Void> ack = exitEmitter.send(exitLog);
    }
}
