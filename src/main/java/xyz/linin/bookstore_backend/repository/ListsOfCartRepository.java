package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.ListsOfCart;

import java.util.List;

public interface ListsOfCartRepository extends CrudRepository<ListsOfCart, Integer> {
    boolean existsByBookAndCart(Book book, Cart cart);
    ListsOfCart getByBookAndCart(Book book, Cart cart);
    void deleteByCart(Cart cart);
    void deleteByBookAndCart(Book book, Cart cart);
    List<ListsOfCart> findAllByCart(Cart cart);
}
