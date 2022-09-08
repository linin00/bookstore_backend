package xyz.linin.bookstore_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xyz.linin.bookstore_backend.dto.BookDto;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public DataResponse<List<Book>> getAll() {
        return new DataResponse<>(bookService.all());
    }

    @GetMapping("/{bookId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public DataResponse<Book> getById(@PathVariable Integer bookId) {
        return new DataResponse<>(bookService.find(bookId));
    }

    @PutMapping("/{bookId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> editBook(@PathVariable Integer bookId, @Valid @RequestBody BookDto bookDto) {
        bookService.edit(bookId, bookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{bookId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> delBook(@PathVariable Integer bookId) {
        bookService.delete(bookId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDto bookDto) {
        bookService.add(bookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
