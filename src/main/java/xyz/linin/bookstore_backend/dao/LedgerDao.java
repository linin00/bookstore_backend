package xyz.linin.bookstore_backend.dao;

import xyz.linin.bookstore_backend.entity.Ledger;

import java.util.List;

public interface LedgerDao {
    List<Ledger> getAll();
    Ledger getById(Integer ledgerId);
}
