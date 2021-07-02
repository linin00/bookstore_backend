package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.OrderDao;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.OrderItem;
import xyz.linin.bookstore_backend.entity.User;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Override
    public void create(User user, Book book) {

    }

    @Override
    public List<OrderItem> getItems(User user, Integer orderId) {
        return null;
    }

    @Override
    public void pay(User user, Integer orderId) {

    }

    @Override
    public void editItem(User user, Integer itemId, OrderItem orderItem) {

    }

    @Override
    public void delOrder(User user, Integer orderId) {

    }

    @Override
    public void delItem(User user, Integer itemId) {

    }

    @Override
    public void handleOrder(Integer orderId) {

    }

    @Override
    public void completeOrder(Integer orderId) {

    }
}
