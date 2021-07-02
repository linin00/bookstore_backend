package xyz.linin.bookstore_backend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Book {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer inventory;

    @Column(nullable = false)
    private String image;
}
