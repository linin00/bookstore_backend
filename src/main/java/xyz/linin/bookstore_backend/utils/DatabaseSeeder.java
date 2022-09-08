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
        User user = new User();
        user.setId(1);
        user.setName("admin");
        user.setAddress("上海市闵行区");
        user.setPhone("18206685581");
        user.setPassword("123");
        user.setRole(Role.admin);
        Cart cart = new Cart();
        cart.setId(1);
        userRepository.save(user);
        cart.setUser(user);
        cartRepository.save(cart);
    }
}