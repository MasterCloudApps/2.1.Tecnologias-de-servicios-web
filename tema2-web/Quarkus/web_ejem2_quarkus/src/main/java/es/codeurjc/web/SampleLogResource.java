package es.codeurjc.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.jboss.logging.Logger;

@Path("page_log")
public class SampleLogResource {

  private static final Logger LOG = Logger.getLogger(SampleLogResource.class);

  @Inject
  Template greetingTemplate;

  @GET
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance page() {

    LOG.trace("A TRACE Message");
    LOG.debug("A DEBUG Message");
    LOG.info("An INFO Message");
    LOG.warn("A WARN Message");
    LOG.error("An ERROR Message");

    return greetingTemplate.data("name", "Mundo");
  }	
}