package xyz.linin.bookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date time;

    @ManyToOne
    @JsonIgnore
    private Ledger ledger;
}
