package com.redhat.quarkus.resources;

import java.util.concurrent.CompletionStage;

import com.redhat.quarkus.model.AccessLog;
import com.redhat.quarkus.model.MoveLog;

import org.jboss.logging.Logger;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/access")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccessLogResource {

    @Inject
    @Channel("entrance-rest")
    Emitter<MoveLog> accessEmitter;

    @Inject
    Logger logger;

    @POST
    public void processLobbyEvent(AccessLog accessLog) {
        MoveLog moveLog = new MoveLog();
        moveLog.setDestination(accessLog.getDestination());
        moveLog.setPersonId(accessLog.getPersonId());
        moveLog.setPreferredRoute("elevator");
        logger.infof("Sending %s to Elevator", moveLog.toString());
        CompletionStage<Void> ack = accessEmitter.send(moveLog);
    }

}
