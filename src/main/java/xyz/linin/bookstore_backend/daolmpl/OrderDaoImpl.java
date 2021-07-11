package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.constants.OrderState;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.dao.OrderDao;
import xyz.linin.bookstore_backend.entity.*;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.repository.BookRepository;
import xyz.linin.bookstore_backend.repository.LedgerRepository;
import xyz.linin.bookstore_backend.repository.OrderFormRepository;
import xyz.linin.bookstore_backend.repository.OrderItemRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderFormRepository orderFormRepository;

    @Override
    public List<OrderForm> findAllByUser(User user) {
        return orderFormRepository.findAllByUser(user);
    }

    @Override
    public OrderForm save(OrderForm orderForm) {
        return orderFormRepository.save(orderForm);
    }

    @Override
    public OrderForm findById(Integer orderId) {
        return orderFormRepository.findById(orderId).get();
    }

    @Override
    public void delete(OrderForm orderForm) {
        orderFormRepository.delete(orderForm);
    }

    @Override
    public List<OrderForm> findAll() {
        return orderFormRepository.findAll();
    }

    @Override
    public List<OrderForm> findAllByTimeBetween(Date time1, Date time2) {
        return orderFormRepository.findAllByTimeBetween(time1, time2);
    }

    @Override
    public List<OrderForm> findAllByTimeBetweenAndUser(Date time1, Date time2, User user) {
        return orderFormRepository.findAllByTimeBetweenAndUser(time1, time2, user);
    }
}
