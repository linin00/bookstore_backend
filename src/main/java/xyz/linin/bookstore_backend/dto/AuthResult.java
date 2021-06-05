package xyz.linin.bookstore_backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.linin.bookstore_backend.entity.User;

@Data
@AllArgsConstructor
@ApiModel("身份验证结果")
public class AuthResult {
    @ApiModelProperty("用户信息")
    private User user;

    @ApiModelProperty("Header Authorization信息")
    private String authorization;
}
