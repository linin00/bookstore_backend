package xyz.linin.bookstore_backend.dao;

import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.Ledger;

import java.util.List;

public interface LedgerDao {

    List<Ledger> findAll();

    Ledger findById(Integer ledgerId);

    boolean existsByBook(Book book);

    Ledger findByBook(Book book);

    Ledger save(Ledger ledger);
}
