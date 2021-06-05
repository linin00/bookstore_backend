package xyz.linin.bookstore_backend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class CartDto {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("userId")
    private Integer userId;
}
