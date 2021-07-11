package xyz.linin.bookstore_backend.dao;

import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.CartItem;

import java.util.List;

public interface CartItemDao {

    boolean existsByCartAndBook(Cart cart, Book book);

    CartItem findByCartAndBook(Cart cart, Book book);

    CartItem save(CartItem cartItem);

    CartItem findById(Integer id);

    void deleteById(Integer itemId);

    List<CartItem> findAllByIdIn(List<Integer> itemIds);

    void deleteAllByIdIn(List<Integer> itemIds);
}
