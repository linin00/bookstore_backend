package xyz.linin.bookstore_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.entity.Ledger;
import xyz.linin.bookstore_backend.service.LedgerService;

import java.util.List;

@RestController
@RequestMapping("/ledger")
public class LedgerController {
    @Autowired
    private LedgerService ledgerService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public DataResponse<List<Ledger>> getAll() {
        return new DataResponse<>(ledgerService.getAll());
    }
    @GetMapping("/{ledgerId}")
    @Secured("ROLE_ADMIN")
    public DataResponse<Ledger> getById(@PathVariable Integer ledgerId) {
        return new DataResponse<>(ledgerService.getById(ledgerId));
    }
}
