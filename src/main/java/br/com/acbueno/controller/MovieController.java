package br.com.acbueno.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import br.com.acbueno.consumer.MovieConsumer;
import br.com.acbueno.model.Movie;
import br.com.acbueno.producer.MovieProducer;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieController {

  @Inject
  MovieProducer producer;

  private final Logger logger = Logger.getLogger(MovieController.class);

  @POST
  public Response send(Movie movie) {
    try {
      producer.send(movie);
    } catch (Exception e) {
      logger.infof("Exception %s", e.getMessage());
    }
    return Response.accepted().build();
  }

}
