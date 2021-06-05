package xyz.linin.bookstore_backend.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class OrderForm {
    @Id
    @ApiModelProperty("id")
    @GeneratedValue
    private Integer id;

    @ApiModelProperty("用户")
    @ManyToOne
    private User user;

    @ApiModelProperty("下单时间")
    private String time;

    @ApiModelProperty("订单状态")
    private String state;

    @ApiModelProperty("收货地址")
    private String address;

    @ApiModelProperty("列表")
    @OneToMany
    private List<ListsOfOrderForm> list;
}
