package xyz.linin.bookstore_backend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.linin.bookstore_backend.constants.SecurityConstants;
import xyz.linin.bookstore_backend.dto.AuthResult;
import xyz.linin.bookstore_backend.dto.LoginCredentials;
import xyz.linin.bookstore_backend.dto.api.DataResponse;
import xyz.linin.bookstore_backend.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {
    private final AuthService authService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseEntity<DataResponse<AuthResult>> login(@RequestBody LoginCredentials loginCredentials) {
        System.out.println("login");
        AuthResult result = authService.login(loginCredentials);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(SecurityConstants.TOKEN_HEADER, result.getAuthorization());
        return new ResponseEntity<>(new DataResponse<>(result), httpHeaders, HttpStatus.ACCEPTED);
    }
}
