package xyz.linin.bookstore_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.dao.CartDao;
import xyz.linin.bookstore_backend.entity.CartItem;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.service.AuthService;
import xyz.linin.bookstore_backend.service.CartService;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private AuthService authService;

    @Override
    public List<CartItem> getCart() {
        User user = authService.getCurrentUser();
        return cartDao.getByUser(user);
    }

    @Override
    public void addItem(CartItem cartItem) {
        User user = authService.getCurrentUser();
        cartDao.add(user, cartItem);
    }

    @Override
    public void editItem(Integer itemId, CartItem cartItem) {
        User user = authService.getCurrentUser();
        cartDao.edit(user, itemId, cartItem);
    }

    @Override
    public void delItem(Integer itemId) {
        User user = authService.getCurrentUser();
        cartDao.del(user, itemId);
    }

    @Override
    public void createOrder(List<Integer> itemIds) {
        User user = authService.getCurrentUser();
        cartDao.createOrder(user, itemIds);
    }

}
