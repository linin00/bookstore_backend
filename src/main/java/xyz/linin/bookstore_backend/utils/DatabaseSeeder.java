package xyz.linin.bookstore_backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.repository.CartRepository;
import xyz.linin.bookstore_backend.repository.UserRepository;

@Component
public class DatabaseSeeder {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        User user1 = new User();
        user1.setId(1);
        user1.setName("admin");
        user1.setAddress("上海市闵行区");
        user1.setPhone("18206685581");
        user1.setPassword("123");
        user1.setRole(Role.admin);
        Cart cart = new Cart();
        cart.setId(1);
        cart.setUser(user1);
        user1.setCart(cart);
        cartRepository.save(cart);
        userRepository.save(user1);
    }
}