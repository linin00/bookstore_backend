package xyz.linin.bookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private OrderForm orderForm;

    @OneToOne
    private Book book;

    private Integer amount = 1;

    @OneToOne
    @JsonIgnore
    private Ledger ledger;
}
