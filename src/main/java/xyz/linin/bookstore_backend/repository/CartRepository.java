package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.User;

public interface CartRepository extends CrudRepository<Cart, Integer> {
    Cart findByUser(User user);
}
