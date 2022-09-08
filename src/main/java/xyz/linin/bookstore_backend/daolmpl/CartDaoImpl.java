package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.CartDao;
import xyz.linin.bookstore_backend.entity.*;
import xyz.linin.bookstore_backend.repository.*;


@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart findByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public void deleteById(Integer cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
