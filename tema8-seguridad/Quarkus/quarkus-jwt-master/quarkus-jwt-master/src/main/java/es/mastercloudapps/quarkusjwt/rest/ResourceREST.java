package es.mastercloudapps.quarkusjwt.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.mastercloudapps.quarkusjwt.model.Message;

@Path("/resource")
public class ResourceREST {

	@RolesAllowed("USER")
	@GET @Path("/user") @Produces(MediaType.APPLICATION_JSON)
	public Response user() {
		return Response.ok(new Message("Content for user")).build();
	}
	
	@RolesAllowed("ADMIN")
	@GET @Path("/admin") @Produces(MediaType.APPLICATION_JSON)
	public Response admin() {
		return Response.ok(new Message("Content for admin")).build();
	}
	
	@RolesAllowed({"USER", "ADMIN"})
	@GET @Path("/user-or-admin") @Produces(MediaType.APPLICATION_JSON)
	public Response userOrAdmin() {
		return Response.ok(new Message("Content for user or admin")).build();
	}
}
