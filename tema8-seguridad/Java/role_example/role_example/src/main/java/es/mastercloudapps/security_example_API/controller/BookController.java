package es.mastercloudapps.security_example_API.controller;

import es.mastercloudapps.security_example_API.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong lastId = new AtomicLong();

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public Collection<Book> getItems() {
        return books.values();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('book:write')")
    public ResponseEntity<Book> getItem(@PathVariable long id) {
        Book book = books.get(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('book:write')")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createItem(@RequestBody Book book) {
        long id = lastId.incrementAndGet();
        book.setId(id);
        books.put(id, book);
        return book;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('book:write')")
    public ResponseEntity<Book> updateItem(@PathVariable long id, @RequestBody Book newBook) {

        Book oldBook = books.get(id);

        if (oldBook != null) {
            newBook.setId(id);
            books.put(id, newBook);

            return new ResponseEntity<>(newBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('book:write')")
    public ResponseEntity<Book> deleteItem(@PathVariable long id) {
        Book book = books.remove(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
