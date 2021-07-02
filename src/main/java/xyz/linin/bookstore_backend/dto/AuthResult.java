package xyz.linin.bookstore_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.linin.bookstore_backend.entity.User;

@Data
@AllArgsConstructor
public class AuthResult {
    private User user;

    private String authorization;
}
