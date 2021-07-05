package xyz.linin.bookstore_backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Ledger {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private Book book;

    private Integer amount = 0;

    @OneToOne
    private OrderItem orderItem;
}
