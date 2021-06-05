package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer> {
}
