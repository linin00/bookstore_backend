package xyz.linin.bookstore_backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Cart cart;

    @OneToOne
    private Book book;

    private Integer amount = 1;
}
