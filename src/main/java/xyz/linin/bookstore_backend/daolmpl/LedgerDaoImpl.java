package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.LedgerDao;
import xyz.linin.bookstore_backend.entity.Ledger;
import xyz.linin.bookstore_backend.repository.LedgerRepository;

import java.util.List;

@Repository
public class LedgerDaoImpl implements LedgerDao {
    @Autowired
    private LedgerRepository ledgerRepository;
    @Override
    public List<Ledger> getAll() {
        return ledgerRepository.findAll();
    }

    @Override
    public Ledger getById(Integer ledgerId) {
        return ledgerRepository.findById(ledgerId).get();
    }
}
