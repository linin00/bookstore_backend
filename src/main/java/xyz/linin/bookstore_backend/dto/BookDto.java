package xyz.linin.bookstore_backend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookDto {

    private Integer id;

    @NotBlank
    private String isbn;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String author;

    private Integer price;

    @NotBlank
    private String description;

    private Integer inventory;

    @NotBlank
    private String image;
}
