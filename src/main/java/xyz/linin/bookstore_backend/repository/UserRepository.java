package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Override
    List<User> findAll();
    User findByName(String name);
    boolean existsByName(String name);
}
