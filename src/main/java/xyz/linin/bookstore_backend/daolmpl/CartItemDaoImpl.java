package xyz.linin.bookstore_backend.daolmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.CartItemDao;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.CartItem;
import xyz.linin.bookstore_backend.repository.CartItemRepository;

import java.util.List;

@Repository
public class CartItemDaoImpl implements CartItemDao {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public boolean existsByCartAndBook(Cart cart, Book book) {
        return cartItemRepository.existsByCartAndBook(cart, book);
    }

    @Override
    public CartItem findByCartAndBook(Cart cart, Book book) {
        return cartItemRepository.findByCartAndBook(cart, book);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findById(Integer id) {
        return cartItemRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer itemId) {
        cartItemRepository.deleteById(itemId);
    }

    @Override
    public List<CartItem> findAllByIdIn(List<Integer> itemIds) {
        return cartItemRepository.findAllByIdIn(itemIds);
    }

    @Override
    public void deleteAllByIdIn(List<Integer> itemIds) {
        cartItemRepository.deleteAllByIdIn(itemIds);
    }
}
