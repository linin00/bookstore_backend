package xyz.linin.bookstore_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.dao.LedgerDao;
import xyz.linin.bookstore_backend.entity.Ledger;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.service.AuthService;
import xyz.linin.bookstore_backend.service.LedgerService;

import java.util.List;

@Service
public class LedgerServiceImpl implements LedgerService {
    @Autowired
    private AuthService authService;
    @Autowired
    private LedgerDao ledgerDao;
    @Override
    public List<Ledger> getAll() {
        return ledgerDao.getAll();
    }

    @Override
    public Ledger getById(Integer ledgerId) {
        User user = authService.getCurrentUser();
        if (user.getRole() != Role.admin) throw new BusinessLogicException("无权限");
        return ledgerDao.getById(ledgerId);
    }
}
