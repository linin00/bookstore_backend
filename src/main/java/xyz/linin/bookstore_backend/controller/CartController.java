package xyz.linin.bookstore_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
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
    public DataResponse<List<CartItem>> getCart() {
        return new DataResponse<>(cartService.getCart());
    }

    @PostMapping
    public ResponseEntity<?> addItem(@Valid @RequestBody CartItem cartItem) {
        cartService.addItem(cartItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<?> delItem(@PathVariable Integer itemId) {
        cartService.delItem(itemId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<?> editItem(@PathVariable Integer itemId, @Valid @RequestBody CartItem cartItem) {
        cartService.editItem(itemId, cartItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/placeOrder")
    public ResponseEntity<?> createOrder(@Valid @RequestBody List<Integer> itemIds) {
        cartService.createOrder(itemIds);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
