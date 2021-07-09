package xyz.linin.bookstore_backend.controller;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.OrderForm;
import xyz.linin.bookstore_backend.entity.OrderItem;
import xyz.linin.bookstore_backend.service.OrderService;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> createOrder(@Valid @RequestBody Book book) {
        orderService.createOrder(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteItem/{itemId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> delItem(@PathVariable Integer itemId) {
        orderService.delItem(itemId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public DataResponse<OrderForm> getOrderItems(@PathVariable Integer orderId) {
        return new DataResponse<>(orderService.getOrderById(orderId));
    }

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public DataResponse<List<OrderForm>> getOrder() {
        return new DataResponse<>(orderService.getOrder());
    }

    @PostMapping("/allByTime")
    @Secured("ROLE_ADMIN")
    public DataResponse<List<OrderForm>> getOrderBetween(@Valid @RequestBody List<Long> times) {
        if (times.size() != 2) return new DataResponse<>(orderService.getOrders());
        else return new DataResponse<>(orderService.getOrdersBetween(new Date(times.get(0)), new Date(times.get(1))));
    }

    @GetMapping("/all")
    @Secured({"ROLE_ADMIN"})
    public DataResponse<List<OrderForm>> getOrders() {
        return new DataResponse<>(orderService.getOrders());
    }

    @PutMapping("/pay/{orderId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> payForOrder(@PathVariable Integer orderId) {
        orderService.payForOrder(orderId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> editItem(@Valid @RequestBody OrderItem orderItem) {
        orderService.editItem(orderItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{orderId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> delOrder(@PathVariable Integer orderId) {
        orderService.delOrder(orderId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/handle/{orderId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> handleOrder(@PathVariable Integer orderId) {
        orderService.handleOrder(orderId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/complete/{orderId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> completeOrder(@PathVariable Integer orderId) {
        orderService.completeOrder(orderId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/cancel/{orderId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> cancelOrder(@PathVariable Integer orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
