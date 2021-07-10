package xyz.linin.bookstore_backend.dao;

import xyz.linin.bookstore_backend.dto.NewUser;
import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.entity.User;

import java.util.List;

public interface UserDao {
    User find(Integer user_id);
    User findByName(String name);
    boolean delete(Integer user_id);
    boolean edit(Integer user_id, UserDto userDto);
    boolean changePassword(Integer user_id,String newPassword);
    List<User> all();
    boolean add(NewUser newUser);
    boolean existByName(String name);
    boolean checkPassword(String password, String name);

    void turn(Integer userId);
}
