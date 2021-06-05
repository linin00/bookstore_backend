package xyz.linin.bookstore_backend.service;

import xyz.linin.bookstore_backend.dto.NewUser;
import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.entity.User;

import java.util.List;

public interface UserService {
    User find(Integer user_id);
    boolean delete(Integer user_id);
    boolean edit(Integer user_id, UserDto userDto);
    boolean changePassword(Integer user_id,String newPassword);
    List<User> all();
    void register(NewUser newUser);
}
