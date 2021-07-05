package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.constants.OrderState;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.dao.OrderDao;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.OrderForm;
import xyz.linin.bookstore_backend.entity.OrderItem;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.repository.BookRepository;
import xyz.linin.bookstore_backend.repository.OrderItemRepository;
import xyz.linin.bookstore_backend.repository.OrderFormRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderFormRepository orderFormRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public void create(User user, Book book) {
        book = bookRepository.findById(book.getId()).get();
        OrderForm orderForm = new OrderForm();
        orderFormRepository.save(orderForm);

        OrderItem orderItem = new OrderItem();
        orderItem.setBook(book);
        orderItem.setOrderForm(orderForm);
        orderItemRepository.save(orderItem);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        orderForm.setOrderItems(orderItems);
        orderForm.setUser(user);
        orderForm.setAddress(user.getAddress());
        orderFormRepository.save(orderForm);
    }

    @Override
    public List<OrderItem> getItems(User user, Integer orderId) {
        OrderForm orderForm = orderFormRepository.findById(orderId).get();
        if (user.getRole() != Role.admin || user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        return orderForm.getOrderItems();
    }

    @Override
    public void pay(User user, Integer orderId) {
        OrderForm orderForm = orderFormRepository.findById(orderId).get();
        if (user.getRole() != Role.admin || user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        orderForm.setState(OrderState.PAID);
        orderFormRepository.save(orderForm);
    }

    @Override
    public void editItem(User user, OrderItem orderItem) {
        OrderForm orderForm = orderItem.getOrderForm();
        if (user.getRole() != Role.admin || user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        orderItemRepository.save(orderItem);
    }

    @Override
    public void delOrder(User user, Integer orderId) {
        OrderForm orderForm = orderFormRepository.findById(orderId).get();
        if (user.getRole() != Role.admin || user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        orderFormRepository.deleteById(orderId);
    }

    @Override
    public void delItem(User user, Integer itemId) {
        OrderItem orderItem = orderItemRepository.findById(itemId).get();
        if (user.getRole() != Role.admin || user != orderItem.getOrderForm().getUser()) throw new BusinessLogicException("无权限");
        orderItemRepository.deleteById(itemId);
    }

    @Override
    public void handleOrder(Integer orderId) {
        OrderForm orderForm = orderFormRepository.findById(orderId).get();
        orderForm.setState(OrderState.HANDLING);
        orderFormRepository.save(orderForm);
    }

    @Override
    public void completeOrder(Integer orderId) {
        OrderForm orderForm = orderFormRepository.findById(orderId).get();
        orderForm.setState(OrderState.COMPLETE);
        orderFormRepository.save(orderForm);
    }

    @Override
    public void cancel(User user, Integer orderId) {
        OrderForm orderForm = orderFormRepository.findById(orderId).get();
        if (user.getRole() != Role.admin || user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        orderForm.setState(OrderState.CANCELLED);
        orderFormRepository.save(orderForm);
    }
}