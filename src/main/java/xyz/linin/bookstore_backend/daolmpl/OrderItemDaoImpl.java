package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.OrderItemDao;
import xyz.linin.bookstore_backend.entity.OrderItem;
import xyz.linin.bookstore_backend.repository.OrderItemRepository;

import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public void saveAll(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem findById(Integer id) {
        return orderItemRepository.findById(id).get();
    }

    @Override
    public void deleteAll(List<OrderItem> orderItems) {
        orderItemRepository.deleteAll(orderItems);
    }

    @Override
    public void deleteById(Integer itemId) {
        orderItemRepository.deleteById(itemId);
    }
}
