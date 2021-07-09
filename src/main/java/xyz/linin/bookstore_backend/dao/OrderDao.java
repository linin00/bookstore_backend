package xyz.linin.bookstore_backend.dao;


import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.OrderForm;
import xyz.linin.bookstore_backend.entity.OrderItem;
import xyz.linin.bookstore_backend.entity.User;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    void create(User user, Book book);

    List<OrderItem> getItems(User user, Integer orderId);

    void pay(User user, Integer orderId);

    void editItem(User user, OrderItem orderItem);

    void delOrder(User user, Integer orderId);

    void delItem(User user, Integer itemId);

    void handleOrder(Integer orderId);

    void completeOrder(Integer orderId);

    void cancel(User user, Integer orderId);

    List<OrderForm> getOrder(User user);

    OrderForm getOrderById(User user, Integer orderId);

    List<OrderForm> getOrders();

    List<OrderForm> getOrdersBetween(Date time1, Date time2);
}
