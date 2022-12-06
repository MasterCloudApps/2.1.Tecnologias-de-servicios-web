package es.codeurjc.books;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class SampleDataLoader {

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        Post.persist(new Post("Pepe", "Vendo moto", "Barata, barata"));
        Post.persist(new Post("Juan", "Compro coche", "Pago bien"));
    }
}
