package xyz.linin.bookstore_backend.daolmpl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.UserDao;
import xyz.linin.bookstore_backend.dto.NewUser;
import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.repository.UserRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public User find(Integer user_id) {
        return userRepository.findById(user_id).get();
    }
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public boolean delete(Integer user_id) {
        if (!userRepository.existsById(user_id)) return false;
        userRepository.deleteById(user_id);
        return true;
    }

    public boolean edit(Integer user_id, UserDto userDto) {
        if (!userRepository.existsById(user_id)) return false;
        User user = userRepository.findById(user_id).get();
        modelMapper.map(userDto, user);
        userRepository.save(user);
        return true;
    }

    public boolean changePassword(Integer user_id, String newPassword) {
        return true;
    }

    public List<User> all() {
        return userRepository.findAll();
    }

    public boolean add (NewUser newUser) {
        User user = modelMapper.map(newUser, User.class);
        userRepository.save(user);
        return true;
    }

    public boolean checkPassword(String password, String name) {
        if(!userRepository.existsByName(name)) return false;
        User user = userRepository.findById(userRepository.findByName(name).getId()).get();
        return user.getPassword().equals(password);
    }

    @Override
    public boolean existByName(String name) {
        return userRepository.existsByName(name);
    }
}
