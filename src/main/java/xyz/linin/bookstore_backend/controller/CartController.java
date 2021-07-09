package xyz.linin.bookstore_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.CartItem;
import xyz.linin.bookstore_backend.service.CartService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public DataResponse<List<CartItem>> getCart() {
        return new DataResponse<>(cartService.getCart());
    }

    @PostMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> addItem(@Valid @RequestBody Book book) {
        cartService.addItem(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{itemId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> delItem(@PathVariable Integer itemId) {
        cartService.delItem(itemId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> editItem(@Valid @RequestBody CartItem cartItem) {
        cartService.editItem(cartItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/placeOrder")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> createOrder(@Valid @RequestBody List<Integer> itemIds) {
        cartService.createOrder(itemIds);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
