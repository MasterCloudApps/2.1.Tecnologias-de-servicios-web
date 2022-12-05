package es.codeurjc.board;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class SampleDataLoader {

    @Inject
    PostRepository posts;

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        posts.persist(new Post("Pepe", "Vendo moto", "Barata, barata"));
        posts.persist(new Post("Juan", "Compro coche", "Pago bien"));
    }
}
