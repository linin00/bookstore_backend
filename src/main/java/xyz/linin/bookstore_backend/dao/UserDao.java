package xyz.linin.bookstore_backend.dao;

import xyz.linin.bookstore_backend.entity.User;

import java.util.List;

public interface UserDao {

    User findById(Integer user_id);

    User findByName(String name);

    void deleteById(Integer user_id);

    User save(User user);

    List<User> findAll();

    boolean existByName(String name);

    boolean existsById(Integer userId);

    boolean existsByName(String name);
}
