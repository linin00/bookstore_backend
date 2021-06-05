package xyz.linin.bookstore_backend.dao;

import xyz.linin.bookstore_backend.dto.BookDto;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.ListsOfCart;

import java.util.List;

public interface CartDao {
    boolean addToCart (Integer cartId, Integer bookId);
    boolean rmFromCart (Integer cartId, Integer bookId);
    boolean createCart (Integer userId);
    boolean deleteCart (Integer cartId);
    List<ListsOfCart> getCart (Integer cartId);
}
