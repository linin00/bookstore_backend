package xyz.linin.bookstore_backend.dao;

import xyz.linin.bookstore_backend.dto.BookDto;
import xyz.linin.bookstore_backend.entity.Book;

import java.util.List;

public interface BookDao {

    Book save(Book book);

    Book findById(Integer id);

    void deleteById(Integer id);

    List<Book> findAll();

    boolean existsById(Integer id);
}
