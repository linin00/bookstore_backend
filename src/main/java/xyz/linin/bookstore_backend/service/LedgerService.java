package xyz.linin.bookstore_backend.service;

import xyz.linin.bookstore_backend.entity.Ledger;

import java.util.List;

public interface LedgerService {
    List<Ledger> getAll();
    Ledger getById(Integer LedgerId);
}
