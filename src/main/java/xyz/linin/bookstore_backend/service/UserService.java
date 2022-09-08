package xyz.linin.bookstore_backend.service;

import xyz.linin.bookstore_backend.dto.NewUser;
import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.entity.User;

import java.util.List;

public interface UserService {
    void delete(Integer user_id);

    void edit(Integer user_id, UserDto userDto);

    void changePassword(Integer user_id, String newPassword);

    List<User> getAll();

    void register(NewUser newUser);

    void turn(Integer userId);

    boolean checkPassword(String password, String name);
}
