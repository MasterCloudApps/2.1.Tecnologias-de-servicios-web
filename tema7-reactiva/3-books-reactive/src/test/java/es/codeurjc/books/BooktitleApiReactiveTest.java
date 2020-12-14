package es.codeurjc.books;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

public class BooktitleApiReactiveTest {

    @Test
    void test() {
        WebTestClient client = WebTestClient.bindToController(new BooksController()).build();

        client.get().uri("/booktitles?title=Java")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody().jsonPath("$.length()").isEqualTo(10);

    }
}
