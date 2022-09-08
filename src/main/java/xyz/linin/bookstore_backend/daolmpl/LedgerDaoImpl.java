package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.LedgerDao;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.Ledger;
import xyz.linin.bookstore_backend.repository.LedgerRepository;

import java.util.List;

@Repository
public class LedgerDaoImpl implements LedgerDao {
    @Autowired
    private LedgerRepository ledgerRepository;
    @Override
    public List<Ledger> findAll() {
        return ledgerRepository.findAll();
    }

    @Override
    public Ledger findById(Integer ledgerId) {
        return ledgerRepository.findById(ledgerId).get();
    }

    @Override
    public boolean existsByBook(Book book) {
        return ledgerRepository.existsByBook(book);
    }

    @Override
    public Ledger findByBook(Book book) {
        return ledgerRepository.findByBook(book);
    }

    @Override
    public Ledger save(Ledger ledger) {
        return ledgerRepository.save(ledger);
    }
}
