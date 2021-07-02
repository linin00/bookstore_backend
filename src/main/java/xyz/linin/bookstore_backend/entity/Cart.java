package xyz.linin.bookstore_backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private User user;

    @OneToMany
    private List<CartItem> cartItems;
}
