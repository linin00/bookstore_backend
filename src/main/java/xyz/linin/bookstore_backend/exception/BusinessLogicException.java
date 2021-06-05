package xyz.linin.bookstore_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BusinessLogicException extends ResponseStatusException {
    public BusinessLogicException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
