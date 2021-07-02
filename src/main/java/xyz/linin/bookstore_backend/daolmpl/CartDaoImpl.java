package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.CartDao;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.CartItem;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.repository.CartItemRepository;
import xyz.linin.bookstore_backend.repository.CartRepository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository listsOfCartRepository;

    @Override
    public List<CartItem> getByUser(User user) {
        Cart cart = cartRepository.findByUser(user);
        return listsOfCartRepository.findAllByCart(cart);
    }

    @Override
    public void edit(User user, Integer itemId, CartItem cartItem) {

    }

    @Override
    public void del(User user, Integer itemId) {

    }

    @Override
    public void add(User user, CartItem cartItem) {

    }

    @Override
    public void createOrder(User user, List<Integer> itemIds) {

    }
}
