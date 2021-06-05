package xyz.linin.bookstore_backend.daolmpl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.linin.bookstore_backend.dao.CartDao;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.Cart;
import xyz.linin.bookstore_backend.entity.ListsOfCart;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.repository.BookRepository;
import xyz.linin.bookstore_backend.repository.CartRepository;
import xyz.linin.bookstore_backend.repository.ListsOfCartRepository;
import xyz.linin.bookstore_backend.repository.UserRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CartDaoImpl implements CartDao {
    private final CartRepository cartRepository;
    private final ListsOfCartRepository listsOfCartRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

    @Override
    public List<ListsOfCart> getCart(Integer cartId) {
        return listsOfCartRepository.findAllByCart(cartRepository.findById(cartId).get());
    }

    @Override
    public boolean addToCart(Integer cartId, Integer bookId) {
        if (bookRepository.existsById(bookId)) return false;
        if (cartRepository.existsById(cartId)) return false;
        if (listsOfCartRepository.existsByBookAndCart(bookRepository.findById(bookId).get(), cartRepository.findById(cartId).get())) {
            ListsOfCart listsOfCart = listsOfCartRepository.getByBookAndCart(bookRepository.findById(bookId).get(), cartRepository.findById(cartId).get());
            listsOfCart.setAmount(listsOfCart.getAmount() + 1);
            listsOfCartRepository.save(listsOfCart);
            return true;
        }
        ListsOfCart listsOfCart = new ListsOfCart();
        listsOfCart.setCart(cartRepository.findById(cartId).get());
        listsOfCart.setBook(bookRepository.findById(bookId).get());
        listsOfCart.setAmount(1);
        listsOfCartRepository.save(listsOfCart);
        return true;
    }

    @Override
    public boolean createCart(Integer userId) {
        if (!userRepository.existsById(userId)) return false;
        Cart cart = new Cart();
        User user = userRepository.findById(userId).get();
        cart.setUser(user);
        cartRepository.save(cart);
        return true;
    }

    @Override
    public boolean deleteCart(Integer cartId) {
        if (!cartRepository.existsById(cartId)) return false;
        listsOfCartRepository.deleteByCart(cartRepository.findById(cartId).get());
        cartRepository.deleteById(cartId);
        return true;
    }

    @Override
    public boolean rmFromCart(Integer cartId, Integer bookId) {
        if (!listsOfCartRepository.existsByBookAndCart(bookRepository.findById(bookId).get(), cartRepository.findById(cartId).get())) return false;
        listsOfCartRepository.deleteByBookAndCart(bookRepository.findById(bookId).get(), cartRepository.findById(cartId).get());
        return true;
    }
}
