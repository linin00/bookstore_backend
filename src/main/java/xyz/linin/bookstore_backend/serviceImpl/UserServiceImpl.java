package xyz.linin.bookstore_backend.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.dao.UserDao;
import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.service.UserService;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public User find(Integer user_id){
        return userDao.find(user_id);
    }

    public boolean delete(Integer user_id) {
        return userDao.delete(user_id);
    }

    public boolean edit(Integer user_id, UserDto userDto) {
        return userDao.edit(user_id, userDto);
    }

    public boolean changePassword(Integer user_id, String newPassword) {
        return userDao.changePassword(user_id, newPassword);
    }

    public List<User> all() {
        return userDao.all();
    }

    public boolean add(UserDto userDto) {
        return userDao.add(userDto);
    }

    public boolean checkPassword(Integer user_id, String password) {
        return userDao.checkPassword(user_id, password);
    }
}
