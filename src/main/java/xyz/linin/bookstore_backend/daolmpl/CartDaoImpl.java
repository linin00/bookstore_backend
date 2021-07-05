package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.dao.CartDao;
import xyz.linin.bookstore_backend.entity.*;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.repository.CartItemRepository;
import xyz.linin.bookstore_backend.repository.CartRepository;
import xyz.linin.bookstore_backend.repository.OrderItemRepository;
import xyz.linin.bookstore_backend.repository.OrderFormRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderFormRepository orderFormRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<CartItem> getByUser(User user) {
        Cart cart = cartRepository.findByUser(user);
        return cart.getCartItems();
    }

    @Override
    public void edit(User user, CartItem cartItem) {
        CartItem cartItem1 = cartItemRepository.findById(cartItem.getId()).get();
        Cart cart = cartItem1.getCart();
        if (cart.getUser() != user || user.getRole() != Role.admin) throw new BusinessLogicException("无权限");
        cartItemRepository.save(cartItem);
    }

    @Override
    public void del(User user, Integer itemId) {
        CartItem cartItem1 = cartItemRepository.findById(itemId).get();
        Cart cart = cartItem1.getCart();
        if (cart.getUser() != user || user.getRole() != Role.admin) throw new BusinessLogicException("无权限");
        cartItemRepository.deleteById(itemId);
    }

    @Override
    public void add(User user, Book book) {
        Cart cart = user.getCart();
        if (cartItemRepository.existsByCartAndBook(cart, book)) {
            CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book);
            cartItem.setAmount(cartItem.getAmount() + 1);
            cartItemRepository.save(cartItem);
        }
        else {
            CartItem cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    public void createOrder(User user, List<Integer> itemIds) {
        List<CartItem> cartItems = cartItemRepository.findAllByIdIn(itemIds);
        OrderForm orderForm = new OrderForm();
        orderFormRepository.save(orderForm);
        orderForm.setUser(user);
        orderForm.setAddress(user.getAddress());
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            if (user != cartItem.getCart().getUser()) throw new BusinessLogicException("无权限");
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderForm(orderForm);
            orderItem.setBook(cartItem.getBook());
            orderItem.setAmount(cartItem.getAmount());
            orderItems.add(orderItem);
        }
        cartItemRepository.deleteAllByIdIn(itemIds);
        orderItemRepository.saveAll(orderItems);
        orderForm.setOrderItems(orderItems);
        orderFormRepository.save(orderForm);
    }
}
