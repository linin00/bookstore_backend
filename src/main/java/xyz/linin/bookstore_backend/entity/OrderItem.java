package xyz.linin.bookstore_backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class OrderItem {
    @Id
    private Integer id;

    @OneToOne
    private Order order;

    @OneToOne
    private Book book;

    private Integer amount;
}
