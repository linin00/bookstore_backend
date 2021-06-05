package xyz.linin.bookstore_backend.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Cart {
    @Id
    @ApiModelProperty("id")
    @GeneratedValue
    private Integer id;

    @ApiModelProperty("用户")
    @OneToOne
    private User user;

}
