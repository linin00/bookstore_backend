package xyz.linin.bookstore_backend.serviceImpl;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.dao.OrderDao;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.OrderForm;
import xyz.linin.bookstore_backend.entity.OrderItem;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.service.AuthService;
import xyz.linin.bookstore_backend.service.OrderService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AuthService authService;

    @Override
    public void createOrder(Book book) {
        User user = authService.getCurrentUser();
        orderDao.create(user, book);
    }

    @Override
    public List<OrderItem> getOrderItems(Integer orderId) {
        User user = authService.getCurrentUser();
        return orderDao.getItems(user, orderId);
    }

    @Override
    public List<OrderForm> getOrder() {
        User user = authService.getCurrentUser();
        return orderDao.getOrder(user);
    }

    @Override
    public void payForOrder(Integer orderId) {
        User user = authService.getCurrentUser();
        orderDao.pay(user, orderId);
    }

    @Override
    @Transactional
    public void editItem(OrderItem orderItem) {
        User user = authService.getCurrentUser();
        orderDao.editItem(user, orderItem);
    }

    @Override
    public void delOrder(Integer orderId) {
        User user = authService.getCurrentUser();
        orderDao.delOrder(user, orderId);
    }

    @Override
    public void delItem(Integer itemId) {
        User user = authService.getCurrentUser();
        orderDao.delItem(user, itemId);
    }

    @Override
    public void handleOrder(Integer orderId) {
        User user = authService.getCurrentUser();
        if (user.getRole() != Role.admin) throw new BusinessLogicException("只能由管理员完成该操作");
        orderDao.handleOrder(orderId);
    }

    @Override
    public void completeOrder(Integer orderId) {
        User user = authService.getCurrentUser();
        if (user.getRole() != Role.admin) throw new BusinessLogicException("只能由管理员完成该操作");
        orderDao.completeOrder(orderId);
    }

    @Override
    public void cancelOrder(Integer orderId) {
        User user = authService.getCurrentUser();
        orderDao.cancel(user, orderId);
    }

    @Override
    public OrderForm getOrderById(Integer orderId) {
        User user = authService.getCurrentUser();
        return orderDao.getOrderById(user, orderId);
    }
}
