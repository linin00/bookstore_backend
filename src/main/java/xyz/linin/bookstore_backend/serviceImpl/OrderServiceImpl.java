package xyz.linin.bookstore_backend.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.constants.OrderState;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.dao.BookDao;
import xyz.linin.bookstore_backend.dao.LedgerDao;
import xyz.linin.bookstore_backend.dao.OrderDao;
import xyz.linin.bookstore_backend.dao.OrderItemDao;
import xyz.linin.bookstore_backend.entity.*;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.service.AuthService;
import xyz.linin.bookstore_backend.service.OrderService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private LedgerDao ledgerDao;

    @Autowired
    private AuthService authService;

    @Override
    @Transactional
    public void createOrder(Book book) {
        User user = authService.getCurrentUser();
        book = bookDao.findById(book.getId());
        if (book.getInventory() == 0) throw new BusinessLogicException("库存不足");
        book.setInventory(book.getInventory() -1);
        bookDao.save(book);
        OrderForm orderForm = new OrderForm();
        orderDao.save(orderForm);

        OrderItem orderItem = new OrderItem();
        orderItem.setBook(book);
        orderItem.setOrderForm(orderForm);
        orderItemDao.save(orderItem);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        orderForm.setOrderItems(orderItems);
        orderForm.setUser(user);
        orderForm.setAddress(user.getAddress());
        orderDao.save(orderForm);
    }

    @Override
    @Transactional
    public List<OrderItem> getOrderItems(Integer orderId) {
        User user = authService.getCurrentUser();
        OrderForm orderForm = orderDao.findById(orderId);
        if (user.getRole() != Role.admin && user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        return orderForm.getOrderItems();
    }

    @Override
    @Transactional
    public List<OrderForm> getOrder() {
        User user = authService.getCurrentUser();
        return orderDao.findAllByUser(user);
    }

    @Override
    @Transactional
    public void payForOrder(Integer orderId) {
        User user = authService.getCurrentUser();
        OrderForm orderForm = orderDao.findById(orderId);
        if (user.getRole() != Role.admin && user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        if (orderForm.getState() == OrderState.PAID && orderForm.getState() == OrderState.HANDLING)
            throw new BusinessLogicException("已支付，请勿重复支付");
        if (orderForm.getState() == OrderState.CANCELLED) throw new BusinessLogicException("支付失败，订单已取消");
        if (orderForm.getState() == OrderState.COMPLETED) throw new BusinessLogicException("支付失败，订单已完成");
        orderForm.setState(OrderState.PAID);
        orderDao.save(orderForm);
        List<OrderItem> orderItems1 = orderForm.getOrderItems();
        for (OrderItem orderItem : orderItems1) {
            Book book = orderItem.getBook();
            List<OrderItem> orderItems;
            Ledger ledger;
            if (ledgerDao.existsByBook(book)) {
                ledger = ledgerDao.findByBook(book);
                orderItems = ledger.getOrderItems();
            }
            else {
                ledger = new Ledger();
                ledger.setBook(book);
                ledgerDao.save(ledger);
                bookDao.save(book);
                orderItems = new ArrayList<>();
            }
            orderItem.setLedger(ledger);
            orderItemDao.save(orderItem);
            orderItems.add(orderItem);
            ledger.setOrderItems(orderItems);
            ledger.setAmount(ledger.getAmount() + orderItem.getAmount());
            ledgerDao.save(ledger);
        }
    }

    @Override
    @Transactional
    public void editItem(OrderItem orderItem) {
        User user = authService.getCurrentUser();
        OrderItem orderItem1 = orderItemDao.findById(orderItem.getId());
        OrderForm orderForm = orderItem1.getOrderForm();
        if (user.getRole() != Role.admin && user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        orderItem.setOrderForm(orderItem1.getOrderForm());
        orderItem.setLedger(orderItem1.getLedger());
        orderItemDao.save(orderItem);
    }

    @Override
    @Transactional
    public void delOrder(Integer orderId) {
        User user = authService.getCurrentUser();
        OrderForm orderForm = orderDao.findById(orderId);
        if (user.getRole() != Role.admin && user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        orderItemDao.deleteAll(orderForm.getOrderItems());
        orderDao.delete(orderForm);
    }

    @Override
    @Transactional
    public void delItem(Integer itemId) {
        User user = authService.getCurrentUser();
        OrderItem orderItem = orderItemDao.findById(itemId);
        if (user.getRole() != Role.admin && user != orderItem.getOrderForm().getUser())
            throw new BusinessLogicException("无权限");
        orderItemDao.deleteById(itemId);
        OrderForm orderForm = orderItem.getOrderForm();
        if (orderDao.findById(orderForm.getId()).getOrderItems().size() == 1) orderDao.delete(orderForm);
    }

    @Override
    @Transactional
    public void handleOrder(Integer orderId) {
        User user = authService.getCurrentUser();
        if (user.getRole() != Role.admin) throw new BusinessLogicException("只能由管理员完成该操作");
        OrderForm orderForm = orderDao.findById(orderId);
        orderForm.setState(OrderState.HANDLING);
        orderDao.save(orderForm);
    }

    @Override
    @Transactional
    public void completeOrder(Integer orderId) {
        User user = authService.getCurrentUser();
        if (user.getRole() != Role.admin) throw new BusinessLogicException("只能由管理员完成该操作");
        OrderForm orderForm = orderDao.findById(orderId);
        orderForm.setState(OrderState.COMPLETED);
        orderDao.save(orderForm);
    }

    @Override
    @Transactional
    public void cancelOrder(Integer orderId) {
        User user = authService.getCurrentUser();
        OrderForm orderForm = orderDao.findById(orderId);
        if (user.getRole() != Role.admin && user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        orderForm.setState(OrderState.CANCELLED);
        orderDao.save(orderForm);
    }

    @Override
    @Transactional
    public OrderForm getOrderById(Integer orderId) {
        User user = authService.getCurrentUser();
        OrderForm orderForm = orderDao.findById(orderId);
        if (user.getRole() != Role.admin && user != orderForm.getUser()) throw new BusinessLogicException("无权限");
        return orderForm;
    }

    @Override
    @Transactional
    public List<OrderForm> getOrders() {
        return orderDao.findAll();
    }

    @Override
    public List<OrderForm> getOrdersBetween(Date time1, Date time2) {
        User user = authService.getCurrentUser();
        if (user.getRole() != Role.admin) throw new BusinessLogicException("无权限");
        return orderDao.findAllByTimeBetween(time1, time2);
    }

    @Override
    public List<OrderForm> getOrdersBetweenAndUser(Date time1, Date time2) {
        User user = authService.getCurrentUser();
        if (user.getRole() != Role.admin) throw new BusinessLogicException("无权限");
        return orderDao.findAllByTimeBetweenAndUser(time1, time2, user);
    }
}
