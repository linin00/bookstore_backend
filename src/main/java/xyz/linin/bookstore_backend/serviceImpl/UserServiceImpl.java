package xyz.linin.bookstore_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.dao.UserDao;
import xyz.linin.bookstore_backend.dto.NewUser;
import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.service.AuthService;
import xyz.linin.bookstore_backend.service.UserService;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthService authService;


    @Transactional
    public boolean delete(Integer user_id) {
        return userDao.delete(user_id);
    }


    @Transactional
    public boolean edit(Integer user_id, UserDto userDto) {
        return userDao.edit(user_id, userDto);
    }

    public boolean changePassword(Integer user_id, String newPassword) {
        return userDao.changePassword(user_id, newPassword);
    }

    public List<User> getAll() {
        return userDao.all();
    }


    @Transactional
    public void register(NewUser newUser) {
        if (userDao.existByName(newUser.getName())) {
            throw new BusinessLogicException("用户名已存在");
        }
        userDao.add(newUser);
    }


    @Transactional
    public User getInfo() {
        return authService.getCurrentUser();
    }

    @Override
    @Transactional
    public void turn(Integer userId) {
        userDao.turn(userId);
    }
}
