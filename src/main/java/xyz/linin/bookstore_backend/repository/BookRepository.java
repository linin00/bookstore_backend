package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    @Override
    List<Book> findAll();
}
