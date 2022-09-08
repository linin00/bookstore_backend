package xyz.linin.bookstore_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginCredentials {
    private String name;

    private String password;
}
