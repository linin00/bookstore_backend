package xyz.linin.bookstore_backend.dao;

import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.entity.User;

import java.util.List;

public interface UserDao {
    User find(Integer user_id);
    boolean delete(Integer user_id);
    boolean edit(Integer user_id, UserDto userDto);
    boolean changePassword(Integer user_id,String newPassword);
    List<User> all();
    boolean add(UserDto userdto);
    boolean checkPassword(Integer user_id, String password);
}
