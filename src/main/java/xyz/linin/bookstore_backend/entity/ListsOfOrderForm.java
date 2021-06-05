package xyz.linin.bookstore_backend.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class ListsOfOrderForm {
    @Id
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("订单号")
    @OneToOne
    private OrderForm orderForm;

    @ApiModelProperty("商品")
    @OneToOne
    private Book book;

    @ApiModelProperty("数量")
    private Integer amount;
}
