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
        User user = new User();
        user.setId(1);
        user.setName("admin");
        user.setAddress("上海市闵行区");
        user.setPhone("18206685581");
        user.setPassword("202252LIANjie*");
        user.setRole(Role.admin);
        userRepository.save(user);
    }
}