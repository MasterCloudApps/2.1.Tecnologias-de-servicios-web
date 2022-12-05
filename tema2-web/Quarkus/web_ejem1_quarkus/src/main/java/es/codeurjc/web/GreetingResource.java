package es.codeurjc.web;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("greeting")
public class GreetingResource {

    @Inject
    Template greetingTemplate;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance greeting() {
        return greetingTemplate.data("name", "Mundo");
    }
}
