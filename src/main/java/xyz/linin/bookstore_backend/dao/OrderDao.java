package xyz.linin.bookstore_backend.dao;


import xyz.linin.bookstore_backend.entity.OrderForm;
import xyz.linin.bookstore_backend.entity.User;

import java.util.Date;
import java.util.List;

public interface OrderDao {

    List<OrderForm> findAllByUser(User user);

    OrderForm save(OrderForm orderForm);

    OrderForm findById(Integer orderId);

    void delete(OrderForm orderForm);

    List<OrderForm> findAll();

    List<OrderForm> findAllByTimeBetween(Date time1, Date time2);

    List<OrderForm> findAllByTimeBetweenAndUser(Date time1, Date time2, User user);
}
