package xyz.linin.bookstore_backend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import xyz.linin.bookstore_backend.constants.OrderState;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date time;

    private OrderState state = OrderState.UNPAID;

    private String address;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
