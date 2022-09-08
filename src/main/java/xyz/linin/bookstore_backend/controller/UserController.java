package xyz.linin.bookstore_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xyz.linin.bookstore_backend.dto.NewUser;
import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.service.AuthService;
import xyz.linin.bookstore_backend.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody NewUser newUser) {
        userService.register(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public DataResponse<List<User>> getAllUser() {
        return new DataResponse<>(userService.getAll());
    }

    @GetMapping("/user")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public DataResponse<User> getInfo() {
        return new DataResponse<>(authService.getCurrentUser());
    }

    @PutMapping("/{userId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> editUser(@PathVariable Integer userId, @Valid @RequestBody UserDto userDto) {
        userService.edit(userId, userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/turn/{userId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> turnUser(@PathVariable Integer userId) {
        userService.turn(userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> delUser(@PathVariable Integer userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
