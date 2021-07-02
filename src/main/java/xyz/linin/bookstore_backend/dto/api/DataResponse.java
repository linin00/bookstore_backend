package xyz.linin.bookstore_backend.dto.api;

import lombok.Data;

@Data
public class DataResponse<T> {
    private T data;
    public DataResponse(T data) {
        this.data = data;
    }
}
