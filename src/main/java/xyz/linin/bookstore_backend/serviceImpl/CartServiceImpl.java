package xyz.linin.bookstore_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.dao.CartDao;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.CartItem;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.service.AuthService;
import xyz.linin.bookstore_backend.service.CartService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private AuthService authService;

    @Override
    @Transactional
    public List<CartItem> getCart() {
        User user = authService.getCurrentUser();
        return cartDao.getByUser(user);
    }

    @Override
    @Transactional
    public void addItem(Book book) {
        User user = authService.getCurrentUser();
        cartDao.add(user, book);
    }

    @Override
    @Transactional
    public void editItem(CartItem cartItem) {
        User user = authService.getCurrentUser();
        cartItem.setCart(user.getCart());
        cartDao.edit(user, cartItem);
    }

    @Override
    @Transactional
    public void delItem(Integer itemId) {
        User user = authService.getCurrentUser();
        cartDao.del(user, itemId);
    }

    @Override
    @Transactional
    public void createOrder(List<Integer> itemIds) {
        User user = authService.getCurrentUser();
        cartDao.createOrder(user, itemIds);
    }

}
