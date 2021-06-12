package xyz.linin.bookstore_backend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.linin.bookstore_backend.dto.NewUser;
import xyz.linin.bookstore_backend.dto.UserDto;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.service.BookService;
import xyz.linin.bookstore_backend.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody NewUser newUser) {
        userService.register(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("获得所有用户")
    @GetMapping
    public DataResponse<List<User>> getAll() {
        return new DataResponse<>(userService.all());
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/{userId}")
    public ResponseEntity<?> edit(@PathVariable Integer userId, @Valid @RequestBody UserDto userDto) {
        userService.edit(userId, userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> del(@PathVariable Integer userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
