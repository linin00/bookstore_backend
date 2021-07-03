package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.dao.CartDao;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.CartItem;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.repository.CartItemRepository;
import xyz.linin.bookstore_backend.repository.CartRepository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getByUser(User user) {
        Cart cart = cartRepository.findByUser(user);
        return cart.getCartItems();
    }

    @Override
    public void edit(User user, CartItem cartItem) {
        CartItem cartItem1 = cartItemRepository.findById(cartItem.getId()).get();
        Cart cart = cartItem1.getCart();
        if (cart.getUser() != user || user.getRole() != Role.admin) throw new BusinessLogicException("无权限");
        cartItemRepository.save(cartItem);
    }

    @Override
    public void del(User user, Integer itemId) {
        CartItem cartItem1 = cartItemRepository.findById(itemId).get();
        Cart cart = cartItem1.getCart();
        if (cart.getUser() != user || user.getRole() != Role.admin) throw new BusinessLogicException("无权限");
        cartItemRepository.deleteById(itemId);
    }

    @Override
    public void add(User user, Book book) {
        Cart cart = user.getCart();
        if (cartItemRepository.existsByCartAndBook(cart, book)) {
            CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book);
            cartItem.setAmount(cartItem.getAmount() + 1);
            cartItemRepository.save(cartItem);
        }
        else {
            CartItem cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    public void createOrder(User user, List<Integer> itemIds) {

    }
}
