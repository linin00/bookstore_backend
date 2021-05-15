package xyz.linin.bookstore_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.service.BookService;
import xyz.linin.bookstore_backend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final BookService bookService;
    @GetMapping("/test01")
    public String test01() {
        return "user testing";
    }

    @GetMapping("/test02")
    public DataResponse<User> test02() {
        System.out.println("userService.all()");
        return new DataResponse<>(userService.find(1));
    }

    @GetMapping("/test03")
    public DataResponse<List<Book>> test03() {

        return new DataResponse<>(bookService.all());
    }
}
