package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.CartItem;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
    List<CartItem> findAllByCart(Cart cart);
    boolean existsByCartAndBook(Cart cart, Book book);
    CartItem findByCartAndBook(Cart cart, Book book);
    List<CartItem> findAllByIdIn(List<Integer> itemIds);
    void deleteAllByIdIn(List<Integer> itemIds);
}
