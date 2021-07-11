package xyz.linin.bookstore_backend.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.dao.CartDao;
import xyz.linin.bookstore_backend.dao.CartItemDao;
import xyz.linin.bookstore_backend.dao.OrderDao;
import xyz.linin.bookstore_backend.dao.UserDao;
import xyz.linin.bookstore_backend.dto.NewUser;
import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.OrderForm;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.service.AuthService;
import xyz.linin.bookstore_backend.service.OrderService;
import xyz.linin.bookstore_backend.service.UserService;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderService orderService;


    @Transactional
    public void delete(Integer userId) {
        if (userDao.existsById(userId)) {
            User user = userDao.findById(userId);
            Cart cart = user.getCart();
            cartItemDao.deleteAll(cart.getCartItems());
            cartDao.deleteById(cart.getId());
            List<OrderForm> orderForms = user.getOrderForm();
            for (OrderForm orderForm : orderForms) {
                orderService.delOrder(orderForm.getId());
            }
            userDao.deleteById(userId);
        }
        else throw new BusinessLogicException("用户不存在");
    }


    @Transactional
    public void edit(Integer userId, UserDto userDto) {
        if (!userDao.existsById(userId)) throw new BusinessLogicException("用户不存在");
        User user = userDao.findById(userId);
        modelMapper.map(userDto, user);
        userDao.save(user);
    }

    public void changePassword(Integer userId, String newPassword) {
        if (!userDao.existsById(userId)) throw new BusinessLogicException("用户不存在");
        User user = userDao.findById(userId);
        userDao.save(user);
    }

    public List<User> getAll() {
        return userDao.findAll();
    }


    @Transactional
    public void register(NewUser newUser) {
        if (userDao.existByName(newUser.getName())) {
            throw new BusinessLogicException("用户名已存在");
        }
        User user = modelMapper.map(newUser, User.class);
        Cart cart = new Cart();
        userDao.save(user);
        cart.setUser(user);
        cartDao.save(cart);
        user.setCart(cart);
        userDao.save(user);
    }


    @Override
    @Transactional
    public void turn(Integer userId) {
        if(!userDao.existsById(userId)) throw new BusinessLogicException("用户不存在");
        User user = userDao.findById(userId);
        if (user.getRole() == Role.admin) throw new BusinessLogicException("不能禁用管理员");
        user.setRole(user.getRole() == Role.user? Role.disable : Role.user);
        userDao.save(user);
    }

    @Override
    public boolean checkPassword(String password, String name) {
        if(!userDao.existsByName(name)) throw new BusinessLogicException("用户不存在");
        User user = userDao.findByName(name);
        return user.getPassword().equals(password);
    }
}
