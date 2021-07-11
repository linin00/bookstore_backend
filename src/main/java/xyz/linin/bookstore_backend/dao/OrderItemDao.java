package xyz.linin.bookstore_backend.dao;

import xyz.linin.bookstore_backend.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {

    void saveAll(List<OrderItem> orderItems);

    OrderItem save(OrderItem orderItem);

    OrderItem findById(Integer id);

    void deleteAll(List<OrderItem> orderItems);

    void deleteById(Integer itemId);
}
