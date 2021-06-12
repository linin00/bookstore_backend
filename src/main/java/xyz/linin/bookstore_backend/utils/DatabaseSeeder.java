package xyz.linin.bookstore_backend.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.repository.UserRepository;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DatabaseSeeder {
    private final UserRepository userRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        User user1 = new User();
        user1.setId(1);
        user1.setName("admin");
        user1.setAddress("上海市闵行区");
        user1.setPhone("18206685581");
        user1.setPassword("202252LIANjie*");
        user1.setRole(Role.admin);
        User user2 = new User();
        user2.setId(2);
        user2.setName("user");
        user2.setAddress("上海市闵行区");
        user2.setPhone("18206685581");
        user2.setPassword("202252LIANjie*");
        user2.setRole(Role.user);
        userRepository.save(user1);
        userRepository.save(user2);
    }
}