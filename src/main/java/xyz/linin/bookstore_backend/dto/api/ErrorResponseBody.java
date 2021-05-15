package xyz.linin.bookstore_backend.dto.api;

import lombok.Data;

@Data
public class ErrorResponseBody {
    private String message = "";

    public ErrorResponseBody(String message) {
        this.message = message;
    }
}
