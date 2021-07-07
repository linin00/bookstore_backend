package xyz.linin.bookstore_backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Ledger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Book book;

    private Integer amount = 0;

    @OneToMany(mappedBy = "ledger")
    private List<OrderItem> orderItems;
}
