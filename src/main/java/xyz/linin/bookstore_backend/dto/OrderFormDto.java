package xyz.linin.bookstore_backend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderFormDto {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户")
    private Integer userId;

    @ApiModelProperty("下单时间")
    private String time;

    @ApiModelProperty("订单状态")
    private String state;

    @ApiModelProperty("收货地址")
    private String address;
}
