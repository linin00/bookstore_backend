package xyz.linin.bookstore_backend.controller;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.OrderForm;
import xyz.linin.bookstore_backend.entity.OrderItem;
import xyz.linin.bookstore_backend.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody Book book) {
        orderService.createOrder(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteItem/{itemId}")
    public ResponseEntity<?> delItem(@PathVariable Integer itemId) {
        orderService.delItem(itemId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public DataResponse<OrderForm> getOrderItems(@PathVariable Integer orderId) {
        return new DataResponse<>(orderService.getOrderById(orderId));
    }

    @GetMapping
    public DataResponse<List<OrderForm>> getOrder() {
        return new DataResponse<>(orderService.getOrder());
    }

    @PutMapping("/pay/{orderId}")
    public ResponseEntity<?> payForOrder(@PathVariable Integer orderId) {
        orderService.payForOrder(orderId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> editItem(@Valid @RequestBody OrderItem orderItem) {
        orderService.editItem(orderItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<?> delOrder(@PathVariable Integer orderId) {
        orderService.delOrder(orderId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/handle/{orderId}")
    public ResponseEntity<?> handleOrder(@PathVariable Integer orderId) {
        orderService.handleOrder(orderId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/complete/{orderId}")
    public ResponseEntity<?> completeOrder(@PathVariable Integer orderId) {
        orderService.completeOrder(orderId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable Integer orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
