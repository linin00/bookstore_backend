package xyz.linin.bookstore_backend.service;

import org.hibernate.criterion.Order;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.OrderForm;
import xyz.linin.bookstore_backend.entity.OrderItem;

import java.util.List;

public interface OrderService {
    void createOrder(Book book);

    List<OrderItem> getOrderItems(Integer orderId);

    List<OrderForm> getOrder();

    void payForOrder(Integer orderId);

    void editItem(OrderItem orderItem);

    void delOrder(Integer orderId);

    void delItem(Integer itemId);

    void handleOrder(Integer orderId);

    void completeOrder(Integer orderId);

    void cancelOrder(Integer orderId);

    OrderForm getOrderById(Integer orderId);

    List<OrderForm> getOrders();
}
