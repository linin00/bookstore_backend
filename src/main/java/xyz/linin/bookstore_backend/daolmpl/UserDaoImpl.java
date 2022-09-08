package xyz.linin.bookstore_backend.daolmpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.dao.UserDao;
import xyz.linin.bookstore_backend.dto.NewUser;
import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.repository.CartRepository;
import xyz.linin.bookstore_backend.repository.UserRepository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    public User findById(Integer user_id) {
        return userRepository.findById(user_id).get();
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public void deleteById(Integer user_id) {
        userRepository.deleteById(user_id);
    }

    public boolean existsById(Integer userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean existByName(String name) {
        return userRepository.existsByName(name);
    }
}
