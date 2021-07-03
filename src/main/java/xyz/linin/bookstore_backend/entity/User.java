package xyz.linin.bookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import xyz.linin.bookstore_backend.constants.Role;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.user;

    @OneToOne
    @JsonIgnore
    private Cart cart;
}
