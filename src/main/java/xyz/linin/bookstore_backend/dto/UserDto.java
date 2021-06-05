package xyz.linin.bookstore_backend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    @NotBlank
    @ApiModelProperty("id")
    private Integer user_id;

    @NotBlank
    @ApiModelProperty("用户名")
    private String name;

    @NotBlank
    @ApiModelProperty("地址")
    private String address;

    @NotBlank
    @ApiModelProperty("电话")
    private String phone;

    @NotBlank
    @ApiModelProperty("购物车")
    private Integer cartId;
}
