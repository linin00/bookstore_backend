package xyz.linin.bookstore_backend.dto;

import lombok.Data;
import xyz.linin.bookstore_backend.validation.PasswordsEqualConstraint;

import javax.validation.constraints.NotBlank;

@Data
@PasswordsEqualConstraint
public class NewUser {
    @NotBlank(message = "用户名为必选项")
    private String name;

    @NotBlank(message = "密码为必选项")
    private String password;

    @NotBlank(message = "确认密码为必填项")
    private String confirmPassword;

    @NotBlank(message = "收货地址为必填项")
    private String address;

    @NotBlank(message = "手机号码为必填项")
    private String phone;

}
