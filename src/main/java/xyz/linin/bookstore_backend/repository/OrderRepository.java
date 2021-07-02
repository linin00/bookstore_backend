package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
