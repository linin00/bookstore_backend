package xyz.linin.bookstore_backend.service;

import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.OrderItem;

import java.util.List;

public interface OrderService {
    void createOrder(Book book);

    List<OrderItem> getOrderItems(Integer orderId);

    void payForOrder(Integer orderId);

    void editItem(Integer itemId, OrderItem orderItem);

    void delOrder(Integer orderId);

    void delItem(Integer orderId, Integer itemId);

    void handleOrder(Integer orderId);

    void completeOrder(Integer orderId);
}
