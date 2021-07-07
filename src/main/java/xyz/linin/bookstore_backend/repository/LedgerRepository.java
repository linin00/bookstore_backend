package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.Ledger;

import java.util.List;


public interface LedgerRepository extends CrudRepository<Ledger, Integer> {
    Ledger findByBook(Book book);
    boolean existsByBook(Book book);
    List<Ledger> findAll();
}
