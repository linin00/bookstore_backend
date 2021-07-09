package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.OrderForm;
import xyz.linin.bookstore_backend.entity.User;

import java.util.Date;
import java.util.List;

public interface OrderFormRepository extends CrudRepository<OrderForm, Integer> {
    List<OrderForm> findAllByUser(User user);
    @Override
    List<OrderForm> findAll();
    List<OrderForm> findAllByTimeBetween(Date time0, Date time2);
}
