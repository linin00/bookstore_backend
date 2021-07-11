package xyz.linin.bookstore_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.dao.*;
import xyz.linin.bookstore_backend.entity.*;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.repository.BookRepository;
import xyz.linin.bookstore_backend.service.AuthService;
import xyz.linin.bookstore_backend.service.CartService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    @Autowired
    private OrderDao orderDao;


    @Autowired
    private BookDao bookDao;

    @Autowired
    private CartItemDao cartItemDao;


    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private AuthService authService;

    @Override
    @Transactional
    public List<CartItem> getCart() {
        User user = authService.getCurrentUser();
        Cart cart = cartDao.findByUser(user);
        return cart.getCartItems();
    }

    @Override
    @Transactional
    public void addItem(Book book) {
        User user = authService.getCurrentUser();
        Cart cart = user.getCart();
        if (cartItemDao.existsByCartAndBook(cart, book)) {
            CartItem cartItem = cartItemDao.findByCartAndBook(cart, book);
            cartItem.setAmount(cartItem.getAmount() + 1);
            cartItemDao.save(cartItem);
        }
        else {
            CartItem cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setCart(cart);
            cartItemDao.save(cartItem);
        }
    }

    @Override
    @Transactional
    public void editItem(CartItem cartItem) {
        User user = authService.getCurrentUser();
        cartItem.setCart(user.getCart());
        CartItem cartItem1 = cartItemDao.findById(cartItem.getId());
        Cart cart = cartItem1.getCart();
        if (cart.getUser() != user && user.getRole() != Role.admin) throw new BusinessLogicException("无权限");
        cartItemDao.save(cartItem);
    }

    @Override
    @Transactional
    public void delItem(Integer itemId) {
        User user = authService.getCurrentUser();
        CartItem cartItem1 = cartItemDao.findById(itemId);
        Cart cart = cartItem1.getCart();
        if (cart.getUser() != user && user.getRole() != Role.admin) throw new BusinessLogicException("无权限");
        cartItemDao.deleteById(itemId);
    }

    @Override
    @Transactional
    public void createOrder(List<Integer> itemIds) {
        User user = authService.getCurrentUser();
        List<CartItem> cartItems = cartItemDao.findAllByIdIn(itemIds);
        OrderForm orderForm = new OrderForm();
        orderDao.save(orderForm);
        orderForm.setUser(user);
        orderForm.setAddress(user.getAddress());
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            if (user != cartItem.getCart().getUser()) throw new BusinessLogicException("无权限");
            Book book = bookDao.findById(cartItem.getBook().getId());
            if (cartItem.getAmount() > book.getInventory()) throw new BusinessLogicException("库存不足");
            book.setInventory(book.getInventory() - cartItem.getAmount());
            bookDao.save(book);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderForm(orderForm);
            orderItem.setBook(book);
            orderItem.setAmount(cartItem.getAmount());
            orderItems.add(orderItem);
        }
        cartItemDao.deleteAllByIdIn(itemIds);
        orderItemDao.saveAll(orderItems);
        orderForm.setOrderItems(orderItems);
        orderDao.save(orderForm);
    }

}
