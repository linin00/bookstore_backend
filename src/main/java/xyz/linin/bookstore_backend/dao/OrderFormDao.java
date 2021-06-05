package xyz.linin.bookstore_backend.dao;


import xyz.linin.bookstore_backend.entity.OrderForm;

import java.util.List;

public interface OrderFormDao {
    List<OrderForm> getOrder(Integer userId);
    List<OrderForm> getUnpaidOrder(Integer userId);
    List<OrderForm> getPaidOrder(Integer userId);
    boolean confirmOrder(Integer orderId);
    boolean cancelOrder(Integer orderId);
    boolean directPurchase(Integer userId, Integer bookId);
}
