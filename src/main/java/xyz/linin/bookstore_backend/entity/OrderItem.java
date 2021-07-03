package xyz.linin.bookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
public class OrderItem {
    @Id
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private Order order;

    @OneToOne
    private Book book;

    private Integer amount = 1;

    @ManyToOne
    @JsonIgnore
    private Ledger ledger;
}
