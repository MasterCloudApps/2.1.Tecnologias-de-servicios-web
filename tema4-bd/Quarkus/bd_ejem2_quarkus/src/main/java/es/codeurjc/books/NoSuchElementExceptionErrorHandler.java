package es.codeurjc.books;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.ws.rs.core.Response;
import java.util.NoSuchElementException;

public class NoSuchElementExceptionErrorHandler {

	@ServerExceptionMapper(NoSuchElementException.class)
	public Response handleNoTFound() {
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}

