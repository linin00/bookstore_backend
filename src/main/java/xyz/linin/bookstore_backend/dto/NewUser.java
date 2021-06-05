package xyz.linin.bookstore_backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.linin.bookstore_backend.validation.PasswordsEqualConstraint;

import javax.validation.constraints.NotBlank;

@Data
@PasswordsEqualConstraint
@ApiModel(value = "新用户注册信息")
public class NewUser {
    @NotBlank(message = "用户名为必选项")
    @ApiModelProperty("用户名")
    private String name;

    @NotBlank(message = "密码为必选项")
    @ApiModelProperty("密码")
    private String password;

    @NotBlank(message = "确认密码为必填项")
    @ApiModelProperty("确认密码")
    private String confirmPassword;

    @NotBlank(message = "收货地址为必填项")
    @ApiModelProperty("收货地址")
    private String address;

    @NotBlank(message = "手机号码为必填项")
    @ApiModelProperty("手机号码")
    private String phone;

}
