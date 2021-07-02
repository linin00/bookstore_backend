package xyz.linin.bookstore_backend.dao;

import xyz.linin.bookstore_backend.entity.CartItem;
import xyz.linin.bookstore_backend.entity.User;

import java.util.List;

public interface CartDao {

    List<CartItem> getByUser(User user);

    void edit(User user, Integer itemId, CartItem cartItem);

    void del(User user, Integer itemId);

    void add(User user, CartItem cartItem);

    void createOrder(User user, List<Integer> itemIds);
}
