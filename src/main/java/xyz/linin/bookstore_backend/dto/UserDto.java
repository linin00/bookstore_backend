package xyz.linin.bookstore_backend.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class UserDto {
    @NotBlank
    @ApiModelProperty("id")
    private Integer user_id;

    @NotBlank
    @ApiModelProperty("昵称")
    private String nickname;

    @NotBlank
    @ApiModelProperty("用户名")
    private String name;

    @NotBlank
    @ApiModelProperty("地址")
    private String address;

    @NotBlank
    @ApiModelProperty("电话")
    private String tel;
}
