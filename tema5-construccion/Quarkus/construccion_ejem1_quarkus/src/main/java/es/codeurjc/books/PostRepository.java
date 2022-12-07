package es.codeurjc.books;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {

    public Collection<Post> findByUsername(String username) {
        return list("username", username);
    }
}
