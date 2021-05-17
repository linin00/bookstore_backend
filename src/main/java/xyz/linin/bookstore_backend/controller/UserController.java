package xyz.linin.bookstore_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.entity.Book;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.service.BookService;
import xyz.linin.bookstore_backend.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000", maxAge = 3600)
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    @GetMapping("/test01")
    public String test01() {
        return "user testing";
    }

    @GetMapping("/test02")
    public DataResponse<User> test02() {
        System.out.println("userService.all()");
        return new DataResponse<>(userService.find(1));
    }

    @PostMapping("/login")
    public ResponseEntity<DataResponse<AuthResult>> login(@RequestBody LoginCredentials loginCredentials) {
        AuthResult result = authService.login(loginCredentials);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(SecurityConstants.TOKEN_HEADER, result.getAuthorization());
        return new ResponseEntity<>(new DataResponse<>(result), httpHeaders, HttpStatus.ACCEPTED);
    }


}
