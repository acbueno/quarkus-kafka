package br.com.acbueno.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import br.com.acbueno.model.Movie;
import io.smallrye.reactive.messaging.kafka.Record;

@ApplicationScoped
public class MovieProducer {

  @Inject
  @Channel("movies-out")
  Emitter<Record<Integer, String>> emitter;

  public void send(Movie movie) {
    Record<Integer, String> of = Record.of(movie.getYear(), movie.getTitle());
    try {
      emitter.send(Record.of(movie.getYear(), movie.getTitle()));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

}
