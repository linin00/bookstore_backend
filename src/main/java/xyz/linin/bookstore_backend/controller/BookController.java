package xyz.linin.bookstore_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:8000", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {
    private final BookService bookService;
    @GetMapping("/all")
    public DataResponse<List<Book>> getAll() {
        return new DataResponse<>(bookService.all());
    }
}
