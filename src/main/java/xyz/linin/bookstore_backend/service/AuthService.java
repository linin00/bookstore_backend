package xyz.linin.bookstore_backend.service;

import xyz.linin.bookstore_backend.dto.AuthResult;
import xyz.linin.bookstore_backend.dto.LoginCredentials;
import xyz.linin.bookstore_backend.entity.User;

public interface AuthService {
    AuthResult login(LoginCredentials loginRequest);

    User getCurrentUser();
}
