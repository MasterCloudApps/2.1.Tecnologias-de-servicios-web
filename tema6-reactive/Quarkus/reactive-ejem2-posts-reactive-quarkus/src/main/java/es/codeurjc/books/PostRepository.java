package es.codeurjc.books;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {

    public Uni<List<Post>> findByUsername(String username) {
        return list("username", username);
    }
}
