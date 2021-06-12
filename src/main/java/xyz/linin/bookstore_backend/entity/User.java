package xyz.linin.bookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.linin.bookstore_backend.constants.Role;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @ApiModelProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("用户名")
    private String name;

    @Column(nullable = false)
    @ApiModelProperty("电话")
    private String phone;

    @Column(nullable = false)
    @ApiModelProperty("地址")
    private String address;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ApiModelProperty("角色")
    private Role role = Role.user;
}
