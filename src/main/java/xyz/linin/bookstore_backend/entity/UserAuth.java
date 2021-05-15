package xyz.linin.bookstore_backend.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserAuth {
    @Id
    @ApiModelProperty("id")
    private Integer user_id;

    @ApiModelProperty("账户")
    @Column(nullable = false)
    private String count;

    @ApiModelProperty("密码")
    @Column(nullable = false)
    private String password;
}
