package xyz.linin.bookstore_backend.dao;

import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.User;


public interface CartDao {

    Cart findByUser(User user);

    void deleteById(Integer cartId);

    Cart save(Cart cart);
}
