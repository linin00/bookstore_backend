package xyz.linin.bookstore_backend.service;

import xyz.linin.bookstore_backend.entity.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getCart();

    void addItem(CartItem cartItem);

    void editItem(Integer itemId, CartItem cartItem);

    void delItem(Integer itemId);

    void createOrder(List<Integer> itemIds);
}
