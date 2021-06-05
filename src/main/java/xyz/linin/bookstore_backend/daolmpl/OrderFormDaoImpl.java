package xyz.linin.bookstore_backend.daolmpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.OrderFormDao;
import xyz.linin.bookstore_backend.entity.ListsOfOrderForm;
import xyz.linin.bookstore_backend.entity.OrderForm;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.repository.BookRepository;
import xyz.linin.bookstore_backend.repository.ListsOfOrderFormRepository;
import xyz.linin.bookstore_backend.repository.OrderFormRepository;
import xyz.linin.bookstore_backend.repository.UserRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderFormDaoImpl implements OrderFormDao {
    private final UserRepository userRepository;
    private final OrderFormRepository orderFormRepository;
    private final ListsOfOrderFormRepository listsOfOrderFormRepository;
    private final BookRepository bookRepository;

    @Override
    public List<OrderForm> getUnpaidOrder(Integer userId) {
        User user = userRepository.findById(userId).get();
        return orderFormRepository.findAllByUserAndStateEquals(user, "unpaid");
    }

    @Override
    public List<OrderForm> getPaidOrder(Integer userId) {
        User user = userRepository.findById(userId).get();
        return orderFormRepository.findAllByUserAndStateEquals(user, "paid");
    }

    @Override
    public List<OrderForm> getOrder(Integer userId) {
        User user = userRepository.findById(userId).get();
        return orderFormRepository.findAllByUser(user);
    }

    @Override
    public boolean cancelOrder(Integer orderId) {
        if (!orderFormRepository.existsById(orderId)) return false;
        orderFormRepository.deleteById(orderId);
        return true;
    }

    @Override
    public boolean confirmOrder(Integer orderId) {
        if (!orderFormRepository.existsById(orderId)) return false;
        OrderForm orderForm = orderFormRepository.findById(orderId).get();
        orderForm.setState("paid");
        return true;
    }

    @Override
    public boolean directPurchase(Integer userId, Integer bookId) {
        if (!userRepository.existsById(userId) || bookRepository.existsById(bookId)) return false;
        OrderForm orderForm = new OrderForm();
        User user = userRepository.findById(userId).get();
        ListsOfOrderForm listsOfOrderForm = new ListsOfOrderForm();
        listsOfOrderForm.setBook(bookRepository.findById(bookId).get());
        List<ListsOfOrderForm> list = orderForm.getList();
        listsOfOrderForm.setOrderForm(orderForm);
        listsOfOrderForm.setAmount(1);
        list.add(listsOfOrderForm);
        orderForm.setUser(user);
        orderForm.setList(list);
        orderForm.setAddress(user.getAddress());
        orderForm.setTime("");
        orderFormRepository.save(orderForm);
        return true;
    }
}
