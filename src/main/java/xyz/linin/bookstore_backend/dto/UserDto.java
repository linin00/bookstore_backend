package xyz.linin.bookstore_backend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.linin.bookstore_backend.constants.Role;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    @ApiModelProperty("id")
    private Integer id;

    @NotBlank
    @ApiModelProperty("用户名")
    private String name;

    @NotBlank
    @ApiModelProperty("地址")
    private String address;

    @NotBlank
    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("角色")
    private Role role;
}
