package xyz.linin.bookstore_backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel("登录信息")
public class LoginCredentials {
    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("密码")
    private String password;
}
