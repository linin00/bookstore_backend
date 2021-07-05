package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.OrderForm;

public interface OrderFormRepository extends CrudRepository<OrderForm, Integer> {
}
