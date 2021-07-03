package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.constants.OrderState;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.dao.OrderDao;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.Order;
import xyz.linin.bookstore_backend.entity.OrderItem;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.repository.OrderItemRepository;
import xyz.linin.bookstore_backend.repository.OrderRepository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public void create(User user, Book book) {
        Order order = new Order();
        order.setUser(user);
        order.setAddress(user.getAddress());
        OrderItem orderItem = new OrderItem();
        orderItem.setBook(book);
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }

    @Override
    public List<OrderItem> getItems(User user, Integer orderId) {
        Order order = orderRepository.findById(orderId).get();
        if (user.getRole() != Role.admin || user != order.getUser()) throw new BusinessLogicException("无权限");
        return order.getOrderItems();
    }

    @Override
    public void pay(User user, Integer orderId) {
        Order order = orderRepository.findById(orderId).get();
        if (user.getRole() != Role.admin || user != order.getUser()) throw new BusinessLogicException("无权限");
        order.setState(OrderState.PAID);
        orderRepository.save(order);
    }

    @Override
    public void editItem(User user, OrderItem orderItem) {
        Order order = orderItem.getOrder();
        if (user.getRole() != Role.admin || user != order.getUser()) throw new BusinessLogicException("无权限");
        orderItemRepository.save(orderItem);
    }

    @Override
    public void delOrder(User user, Integer orderId) {
        Order order = orderRepository.findById(orderId).get();
        if (user.getRole() != Role.admin || user != order.getUser()) throw new BusinessLogicException("无权限");
        orderRepository.deleteById(orderId);
    }

    @Override
    public void delItem(User user, Integer itemId) {
        OrderItem orderItem = orderItemRepository.findById(itemId).get();
        if (user.getRole() != Role.admin || user != orderItem.getOrder().getUser()) throw new BusinessLogicException("无权限");
        orderItemRepository.deleteById(itemId);
    }

    @Override
    public void handleOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setState(OrderState.HANDLING);
        orderRepository.save(order);
    }

    @Override
    public void completeOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setState(OrderState.COMPLETE);
        orderRepository.save(order);
    }

    @Override
    public void cancel(User user, Integer orderId) {
        Order order = orderRepository.findById(orderId).get();
        if (user.getRole() != Role.admin || user != order.getUser()) throw new BusinessLogicException("无权限");
        order.setState(OrderState.CANCELLED);
        orderRepository.save(order);
    }
}
