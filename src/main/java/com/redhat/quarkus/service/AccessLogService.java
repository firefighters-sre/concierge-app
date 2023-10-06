package com.redhat.quarkus.service;

import com.redhat.quarkus.model.AccessLog;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class AccessLogService {


    @Incoming("lobby")
    @Outgoing("entrance")
    @Blocking
    public AccessLog processLobbyEvent(AccessLog accessLog) throws InterruptedException {
        return accessLog;
    }

}
