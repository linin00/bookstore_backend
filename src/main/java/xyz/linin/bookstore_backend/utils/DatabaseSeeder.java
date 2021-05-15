package xyz.linin.bookstore_backend.utils;

import ch.qos.logback.core.util.SystemInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.entity.UserAuth;
import xyz.linin.bookstore_backend.repository.UserAuthRepository;
import xyz.linin.bookstore_backend.repository.UserRepository;

import javax.jws.soap.SOAPBinding;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DatabaseSeeder {
    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        User user = new User();
        user.setUser_id(0);
        user.setName("系统管理员");
        user.setNickname("系统管理员");
        user.setAddress("上海市闵行区");
        user.setTel("18206685581");
        UserAuth userAuth = new UserAuth();
        userAuth.setPassword("202252LIANjie*");
        userAuth.setCount("admin");
        userAuth.setUser_id(0);
        userRepository.save(user);
    }
}