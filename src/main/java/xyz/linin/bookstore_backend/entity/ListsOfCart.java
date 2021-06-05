package xyz.linin.bookstore_backend.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ListsOfCart {
    @Id
    @ApiModelProperty("id")
    @GeneratedValue
    private Integer id;

    @ApiModelProperty("订单号")
    @ManyToOne
    private Cart cart;

    @ApiModelProperty("商品")
    @OneToOne
    private Book book;

    @ApiModelProperty("数量")
    private Integer amount;
}
