package com.redhat.quarkus.controller;

import com.redhat.quarkus.model.RegistroAcesso;
import com.redhat.quarkus.service.AccessService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/access")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccessResource {

    @Inject
    AccessService accessService;

    @POST
    @Path("/entry")
    public Response addEntry(RegistroAcesso registroAcesso) {
        accessService.addEntry(registroAcesso);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/exit")
    public Response addExit(RegistroAcesso registroAcesso) {
        accessService.addExit(registroAcesso);
        return Response.status(Response.Status.OK).build();
    }
}
