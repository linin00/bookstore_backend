package xyz.linin.bookstore_backend.service;

import xyz.linin.bookstore_backend.dto.BookDto;
import xyz.linin.bookstore_backend.entity.Book;

import java.util.List;

public interface BookService {
    Book find(Integer id);
    boolean delete(Integer id);
    boolean edit(Integer id, BookDto bookDto);
    List<Book> all();
    boolean add(BookDto bookDto);
}
