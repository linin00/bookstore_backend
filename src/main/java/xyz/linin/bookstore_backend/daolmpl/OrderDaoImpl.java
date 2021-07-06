package xyz.linin.bookstore_backend.daolmpl;

import org.hibernate.criterion.Order;
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
        if (orderForm.getState() == OrderState.PAID || orderForm.getState() == OrderState.HANDLING) throw new BusinessLogicException("已支付，请勿重复支付");
        if (orderForm.getState() == OrderState.CANCELLED) throw new BusinessLogicException("支付失败，订单已取消");
        if (orderForm.getState() == OrderState.COMPLETE) throw new BusinessLogicException("支付失败，订单已完成");
        orderForm.setState(OrderState.PAID);
        orderFormRepository.save(orderForm);
    }

    @Override
    public void editItem(User user, OrderItem orderItem) {
        OrderItem orderItem1 = orderItemRepository.findById(orderItem.getId()).get();
        OrderForm orderForm = orderItem1.getOrderForm();
        if (user.getRole() != Role.admin || user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        orderItem.setOrderForm(orderItem1.getOrderForm());
        orderItem.setLedger(orderItem1.getLedger());
        orderItemRepository.save(orderItem);
    }

    @Override
    public void delOrder(User user, Integer orderId) {
        OrderForm orderForm = orderFormRepository.findById(orderId).get();
        if (user.getRole() != Role.admin || user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        orderItemRepository.deleteAll(orderForm.getOrderItems());
        orderFormRepository.delete(orderForm);
    }

    @Override
    public void delItem(User user, Integer itemId) {
        OrderItem orderItem = orderItemRepository.findById(itemId).get();
        if (user.getRole() != Role.admin || user != orderItem.getOrderForm().getUser()) throw new BusinessLogicException("无权限");
        orderItemRepository.deleteById(itemId);
        OrderForm orderForm = orderItem.getOrderForm();
        if (orderForm.getOrderItems().size() == 0) orderFormRepository.delete(orderForm);
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

    @Override
    public List<OrderForm> getOrder(User user) {
        System.out.println(orderFormRepository.findAllByUser(user).size());
        return orderFormRepository.findAllByUser(user);
    }

    @Override
    public OrderForm getOrderById(User user, Integer orderId) {
        OrderForm orderForm = orderFormRepository.findById(orderId).get();
        if (user.getRole() != Role.admin || user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        return orderForm;
    }
}
