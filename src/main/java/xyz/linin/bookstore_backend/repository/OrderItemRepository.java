package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.OrderForm;
import xyz.linin.bookstore_backend.entity.OrderItem;

import java.util.Date;
import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
}
