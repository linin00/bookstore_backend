package xyz.linin.bookstore_backend.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    @ApiModelProperty("id")
    private Integer user_id;

    @Column(nullable = false)
    @ApiModelProperty("用户名")
    private String name;

    @Column(nullable = false)
    @ApiModelProperty("昵称")
    private String nickname;

    @Column(nullable = false)
    @ApiModelProperty("电话")
    private String tel;

    @Column(nullable = false)
    @ApiModelProperty("地址")
    private String address;
}
